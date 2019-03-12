package com.tmg.lesson9.dao.message;

import com.tmg.lesson9.model.message.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
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

    @Autowired
    public DefaultMessageDaoImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public List<MessageModel> getAllMessages() {
        List<MessageModel> result;
        String querySelectAllMessages =
                "SELECT " + dbMessageSelectParametersDividedByComma +
                " FROM " + dbMessageTable;

        MessageMapper messageMapper = new MessageMapper();
        result = namedJdbcTemplate.query(querySelectAllMessages, messageMapper);
        return result;
    }

    @Override
    public List<MessageModel> getMessageByCreator(String creatorName) {
        List<MessageModel> result;
//        String creatorNameParam = "creatorName";
        String querySelectMessagesByCreator =
                "SELECT " + dbMessageSelectParametersDividedByComma +
                " FROM " + dbMessageTable +
                " WHERE " + dbMessageCreatorParam + "= :creator;";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("creator", creatorName);
        MessageMapper messageMapper = new MessageMapper();
        result = namedJdbcTemplate.query(querySelectMessagesByCreator, mapSqlParameterSource, messageMapper);
        return result;
    }

    @Override
    public boolean insertIntoMessages(MessageModel messageModel) {
        String queryInputMessage =
                "INSERT INTO " +  dbMessageTable +
                " (" + dbMessageInsertParametersDividedByComma + ") " +
                "values (:messageTopic, :messageBody, :messageCreator, :messageDateTime)";

        Map<String, String> messageParamMap = getMessageParamMap(messageModel);
        namedJdbcTemplate.update(queryInputMessage, messageParamMap);
//        TODO ?? where is exceptions
        return true;
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