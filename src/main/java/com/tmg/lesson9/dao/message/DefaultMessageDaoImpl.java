package com.tmg.lesson9.dao.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.validator.message.MessageDaoValidator;
import com.tmg.lesson9.model.message.MessageModel;
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

/**
 * Default implementation of MessageDao interface. Class make query and send request with query to database then take
 * response from database and process it. After processing send output processing data or throw CustomDaoException.
 */

@Repository("messageDao")
public class DefaultMessageDaoImpl implements MessageDao {

    private final static String DB_MESSAGE_TABLE = "messages";
    private final static String DB_MESSAGE_ID = "message_id";
    private final static String DB_MESSAGE_TOPIC = "message_topic";
    private final static String DB_MESSAGE_BODY = "message_body";
    private final static String DB_MESSAGE_CREATOR = "message_creator";
    private final static String DB_MESSAGE_DATE_TIME = "message_date_time";
    private final static String DB_MESSAGE_INSERT_PARAMETERS_DIVIDED_BY_COMMA =
            DB_MESSAGE_TOPIC + ", " + DB_MESSAGE_BODY + ", " + DB_MESSAGE_CREATOR + ", " + DB_MESSAGE_DATE_TIME;
    private final static String DB_MESSAGE_SELECT_PARAMETERS_DIVIDED_BY_COMMA =
            DB_MESSAGE_ID + ", " + DB_MESSAGE_INSERT_PARAMETERS_DIVIDED_BY_COMMA;
    /**
     * injection default realisation of MessageDaoValidator interface
     *
     * @param messageDaoValidator validator for checking input parameters
     */

    @Resource
    MessageDaoValidator messageDaoValidator;
    /**
     * NamedParameterJdbcTemplate class with a basic set of JDBC operations, allowing the use of
     * named parameters rather than traditional '?' placeholders.
     *
     * @param namedJdbcTemplate  object of NamedParameterJdbcTemplate
     */

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    /**
     * init NamedParameterJdbcTemplate using constructor injection
     *
     * @param namedJdbcTemplate
     */

    @Autowired
    public DefaultMessageDaoImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    /**
     * Make 'select all messages' query. Create object of MessageMapper for getting right filled result.
     * Send request to database using NamedJdbcTemplate with query and MessageMapper.
     * If SQLException will be thrown wrap it with CustomDaoException with explain message and rethrow.
     *
     * @return list of MessageModel objects type
     * @throws CustomDaoException if SQLException will be thrown
     */

    @Override
    public List<MessageModel> getAllMessages() throws CustomDaoException {
        String querySelectAllMessages =
                "SELECT " + DB_MESSAGE_SELECT_PARAMETERS_DIVIDED_BY_COMMA +
                        " FROM " + DB_MESSAGE_TABLE;

        MessageMapper messageMapper = new MessageMapper();
        try {
            return namedJdbcTemplate.query(querySelectAllMessages, messageMapper);
        } catch (DataIntegrityViolationException e) {
            throw new CustomDaoException("DAO Error: something goes wrong in database during searching messages", e);
        }
    }

    /**
     * Check input @param creatorName for valid. If it is invalid then throw CustomDaoException
     * Make 'select all messages by message creator' query. Create object of MessageMapper for getting right filled result.
     * Create MapSqlParameterSource object for right query work. Send request to database using NamedJdbcTemplate with query
     * and MessageMapper and MapSqlParameterSource.
     * If SQLException will be thrown wrap it with CustomDaoException with explain message and rethrow.
     *
     * @param creatorName string with name of message creator
     * @return list of MessageModel objects type where messageCreator field is equals @param creatorName
     * @throws CustomDaoException if creatorName is not valid or if SQLException will be thrown
     */

    @Override
    public List<MessageModel> getMessagesByCreator(String creatorName) throws CustomDaoException {
        messageDaoValidator.isMessageCreatorValid(creatorName);
        String querySelectMessagesByCreator =
                "SELECT " + DB_MESSAGE_SELECT_PARAMETERS_DIVIDED_BY_COMMA +
                        " FROM " + DB_MESSAGE_TABLE +
                        " WHERE " + DB_MESSAGE_CREATOR + "= :creator;";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("creator", creatorName);
        MessageMapper messageMapper = new MessageMapper();
        try {
            return namedJdbcTemplate.query(querySelectMessagesByCreator, mapSqlParameterSource, messageMapper);
        } catch (DataIntegrityViolationException e) {
            throw new CustomDaoException("DAO Error: message creator name={" + creatorName + "} is not found in database", e);
        }
    }

    /**
     * Check for input @param messageModel for valid. If it is invalid then throw CustomDaoException.
     * Make 'insert message into messages table' query. Create object of MessageMapper for getting right filled result.
     * Create map(key=String, value=String) for mapping values that we will send to NamedJdbcTemplate for correctly working
     * query. Send request to database using NamedJdbcTemplate with query and mapping parameters
     * If SQLException will be thrown wrap it with CustomDaoException with explain message and rethrow.
     *
     * @param messageModel object that contain information about message
     * @return true if all operations in this method were successful
     * @throws CustomDaoException if messageModel is not valid or if SQLException will be thrown
     */

    @Override
    public boolean insertIntoMessages(MessageModel messageModel) throws CustomDaoException {
        messageDaoValidator.isMessageModelValid(messageModel);
        String queryInputMessage =
                "INSERT INTO " + DB_MESSAGE_TABLE +
                        " (" + DB_MESSAGE_INSERT_PARAMETERS_DIVIDED_BY_COMMA + ") " +
                        "values (:messageTopic, :messageBody, :messageCreator, :messageDateTime)";

        Map<String, String> messageParamMap = getMessageParamMap(messageModel);
        try {
            namedJdbcTemplate.update(queryInputMessage, messageParamMap);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new CustomDaoException("DAO Error: message model={" + messageModel.toString() + "} is incorrect", e);
        }
    }

    /**
     * Method create map of mapping string params to MessageModel fields for use in input NamedJdbcTemplate
     *
     * @param message object of MessageModel type
     * @return map of mapping string params to MessageModel fields
     */

    private Map<String, String> getMessageParamMap(MessageModel message) {
        Map<String, String> messageParam = new HashMap();
        messageParam.put("messageTopic", message.getMessageTopic());
        messageParam.put("messageBody", message.getMessageBody());
        messageParam.put("messageCreator", message.getMessageCreator());
        messageParam.put("messageDateTime", message.getDateTimeCreation());
        return messageParam;
    }

    /**
     * Nested class of DefaultMessageDaoImpl. Class must implements RowMapper for getting right output
     * MessageModel object from database response.
     */

    private static final class MessageMapper implements RowMapper<MessageModel> {

        /**
         * Method create filled MessageModel object from ResultSet
         *
         * @param resultSet object for getting result from database response
         * @param rowNumber number of row with data from which MessageModel will be filled
         * @return filled MessageModel with information from database
         * @throws SQLException anytime during database operations
         */
        public MessageModel mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            MessageModel result = new MessageModel();
            result.setId(resultSet.getInt(DB_MESSAGE_ID));
            result.setMessageTopic(resultSet.getString(DB_MESSAGE_TOPIC));
            result.setMessageBody(resultSet.getString(DB_MESSAGE_BODY));
            result.setMessageCreator(resultSet.getString(DB_MESSAGE_CREATOR));
            result.setDateTimeCreation(resultSet.getString(DB_MESSAGE_DATE_TIME));
            return result;
        }


    }
}
