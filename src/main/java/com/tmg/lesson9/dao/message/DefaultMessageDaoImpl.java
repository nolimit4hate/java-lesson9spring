package com.tmg.lesson9.dao.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.dao.validator.message.MessageDaoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("messageDao")
public class DefaultMessageDaoImpl implements MessageDao{

    private final static String dbMessageTable = "messages";
    private final static String dbMessageIdParam = "message_id";
    private final static String dbMessageTopicParam = "message_topic";
    private final static String dbMessageBodyParam = "message_body";
    private final static String dbMessageCreatorParam = "message_creator";
    private final static String dbMessageDateTimeParam = "message_date_time";
    private final static String dbMessageInsertParametersDividedByComma =
            dbMessageTopicParam + ", " + dbMessageBodyParam + ", " + dbMessageCreatorParam + ", " + dbMessageDateTimeParam;
    private final static String dbMessageSelectParametersDividedByComma =
            dbMessageIdParam + ", " + dbMessageInsertParametersDividedByComma;

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Resource
    MessageDaoValidator messageDaoValidator;

    @Autowired
    public DefaultMessageDaoImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public List<MessageModel> getAllMessages() {
        String querySelectAllMessages =
                "SELECT " + dbMessageSelectParametersDividedByComma +
                " FROM " + dbMessageTable;

        MessageMapper messageMapper = new MessageMapper();
        try {
            return namedJdbcTemplate.query(querySelectAllMessages, messageMapper);
        } catch (DataIntegrityViolationException e) {
            throw new CustomDaoException("DAO Error: something goes wrong in database during searching messages", e);
        }
    }

    @Override
    public List<MessageModel> getMessagesByCreator(String creatorName) throws CustomDaoException {
        messageDaoValidator.isMessageCreatorValid(creatorName);
        String querySelectMessagesByCreator =
                "SELECT " + dbMessageSelectParametersDividedByComma +
                " FROM " + dbMessageTable +
                " WHERE " + dbMessageCreatorParam + "= :creator;";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("creator", creatorName);
        MessageMapper messageMapper = new MessageMapper();
        try {
            return namedJdbcTemplate.query(querySelectMessagesByCreator, mapSqlParameterSource, messageMapper);
        } catch (DataIntegrityViolationException e) {
            throw new CustomDaoException("DAO Error: message creator name={" + creatorName + "} is not found in database", e);
        }
    }

    @Override
    public boolean insertIntoMessages(MessageModel messageModel) throws CustomDaoException {
        messageDaoValidator.isMessageModelValid(messageModel);
        String queryInputMessage =
                "INSERT INTO " +  dbMessageTable +
                " (" + dbMessageInsertParametersDividedByComma + ") " +
                "values (:messageTopic, :messageBody, :messageCreator, :messageDateTime)";

        Map<String, String> messageParamMap = getMessageParamMap(messageModel);
        try {
            namedJdbcTemplate.update(queryInputMessage, messageParamMap);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new CustomDaoException("DAO Error: message model={" + messageModel.toString() + "} is incorrect", e);
        }
    }

    private Map<String, String> getMessageParamMap(MessageModel message){
        Map<String, String> messageParam = new HashMap();
        messageParam.put("messageTopic", message.getMessageTopic());
        messageParam.put("messageBody", message.getMessageBody());
        messageParam.put("messageCreator", message.getMessageCreator());
        messageParam.put("messageDateTime", message.getDateTimeCreation());
        return messageParam;
    }

    private static final class MessageMapper implements RowMapper<MessageModel> {
        public MessageModel mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            MessageModel result = new MessageModel();
            result.setId(resultSet.getInt(dbMessageIdParam));
            result.setMessageTopic(resultSet.getString(dbMessageTopicParam));
            result.setMessageBody(resultSet.getString(dbMessageBodyParam));
            result.setMessageCreator(resultSet.getString(dbMessageCreatorParam));
            result.setDateTimeCreation(resultSet.getString(dbMessageDateTimeParam));
            return result;
        }
    }
}
