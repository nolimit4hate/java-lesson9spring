package com.tmg.lesson9.dao.user;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.validator.user.UserDaoValidator;
import com.tmg.lesson9.model.user.UserModel;
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
import java.util.Map;

/**
 * Default implementation of UserDao interface. Class make query and send request with query to database then take
 * response from database and process it. After processing send output processing data or throw CustomDaoException.
 */

@Repository("userDao")
public class DefaultUserDaoImpl implements UserDao {

    private final static String USERS_TABLE = "users";
    private final static String USERS_INSERT_PARAMS_SEPARATED_BY_COMMA =
            "user_name, user_email, user_password, user_gender, user_country, creation_date_time";
    private final static String USERS_SELECT_PARAMS_SEPARATED_BY_COMMA =
            "user_id, " + USERS_INSERT_PARAMS_SEPARATED_BY_COMMA;
    /**
     * injection default realisation of MessageDaoValidator interface
     *
     * @param userDaoValidator validator for checking input parameters
     */

    @Resource
    UserDaoValidator userDaoValidator;
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
    public DefaultUserDaoImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    /**
     * Check input @param userName for valid. If it is invalid then throw CustomDaoException
     * Make 'select user by userName' query. Create object of MessageMapper for getting right filled result.
     * Create MapSqlParameterSource object for right query work. Send request to database using NamedJdbcTemplate with query.
     * and MessageMapper and MapSqlParameterSource. Return filled UserName object.
     * If SQLException will be thrown wrap it with CustomDaoException with explain message and rethrow.
     *
     * @param userName string with user name value
     * @return filled UserModel object. it is a result of processing database response
     * @throws CustomDaoException if @param userName is invalid or if SQLException will be
     */

    @Override
    public UserModel selectUserByNameFromUsers(String userName) throws CustomDaoException {
        userDaoValidator.isUserNameValid(userName);
        UserModel userModel;
        String querySelectUserByName =
                "SELECT " + USERS_SELECT_PARAMS_SEPARATED_BY_COMMA +
                        " FROM " + USERS_TABLE + " WHERE user_name= :name";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", userName);
        UserMapper userMapper = new UserMapper();
        try {
            userModel = namedJdbcTemplate.queryForObject(querySelectUserByName, mapSqlParameterSource, userMapper);
            return userModel;
        } catch (DataIntegrityViolationException e) {
            throw new CustomDaoException("DAO Error: entering name={" + userName + "} is not found in database ", e);
        }
    }

    /**
     * Check input @param userName and @param userPassword for valid. If they are invalid then throw CustomDaoException
     * Make 'select user by userName and userPassword' query. Create object of MessageMapper for getting right filled result.
     * Create MapSqlParameterSource object for right query work. Send request to database using NamedJdbcTemplate with query.
     * and MessageMapper and MapSqlParameterSource. Check getting UserModel object for valid. If UserModel object is valid
     * then return true else throw CustomDaoException with explain message.
     * If SQLException will be thrown wrap it with CustomDaoException with explain message and rethrow.
     *
     * @param userPassword string with user password value
     * @return true if all operations in this method were successful. Exactly if user with input user name and password
     * exists in database.
     * @throws CustomDaoException if @param userName or @param userPassword is invalid or if getting UserModel result from
     *                            NamedJdbcTemplate is invalid or if SQLException will be thrown
     */

    @Override
    public boolean selectUserByNamePasswordFromUsers(String userName, String userPassword) throws CustomDaoException {
        userDaoValidator.isUserNameValid(userName);
        userDaoValidator.isUserPasswordValid(userPassword);
        String querySelectUserByNamePass =
                "SELECT " + USERS_SELECT_PARAMS_SEPARATED_BY_COMMA +
                        " FROM " + USERS_TABLE + " WHERE user_name= :name AND user_password= :password";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", userName);
        mapSqlParameterSource.addValue("password", userPassword);
        UserMapper userMapper = new UserMapper();
        try {
            UserModel userModel = namedJdbcTemplate.queryForObject(querySelectUserByNamePass, mapSqlParameterSource, userMapper);
            userDaoValidator.isUserModelValid(userModel);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new CustomDaoException("DAO Error: entering name={" + userName +
                    "} and password={" + userPassword + "} ", e);
        }
    }

    /**
     * Check for input @param user for valid. If it is invalid then throw CustomDaoException.
     * Make 'insert user into users table' query. Create object of MessageMapper for getting right filled result.
     * Create map(key=String, value=String) for mapping values that we will send to NamedJdbcTemplate for correctly working
     * query. Send request to database using NamedJdbcTemplate with query and mapping parameters
     * If SQLException will be thrown wrap it with CustomDaoException with explain message and rethrow.
     *
     * @param user UserModel object with information about user that will be added to database
     * @return true if all operations in this method were successful. Exactly if user data in @param user will be added to database
     * @throws CustomDaoException if @param user is invalid or if SQLException will be thrown
     */

    @Override
    public boolean insertIntoUsers(UserModel user) throws CustomDaoException {
        userDaoValidator.isUserModelValid(user);
        String queryInputUser =
                "INSERT INTO " + USERS_TABLE +
                        " (" + USERS_INSERT_PARAMS_SEPARATED_BY_COMMA + ") " +
                        " values(:name, :email, :password, :gender, :country, :dateTime);";
        Map<String, String> userParam = getUserParamMap(user);
        try {
            namedJdbcTemplate.update(queryInputUser, userParam);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new CustomDaoException("DAO Error: entering name={" + user.getUserName() +
                    "} or email={" + user.getEmail() + "} already exists", e);
        }
    }

    /**
     * Method create map of mapping string params to UserModel fields for use in input NamedJdbcTemplate
     *
     * @param user UserModel object with information about user
     * @return map of mapping string params to UserModel fields for use in input NamedJdbcTemplate
     */

    private Map<String, String> getUserParamMap(UserModel user) {
        Map<String, String> userParam = new HashMap();
        userParam.put("name", user.getUserName());
        userParam.put("email", user.getEmail());
        userParam.put("password", user.getPassword());
        userParam.put("gender", user.getGender());
        userParam.put("country", user.getCountry());
        userParam.put("dateTime", user.getCreationDateTime());
        return userParam;
    }

    /**
     * Nested class of DefaultUserDaoImpl. Class must implements RowMapper for getting right output
     * MessageModel object from database response.
     */

    private static final class UserMapper implements RowMapper<UserModel> {

        /**
         * Method create filled UserModel object from ResultSet
         *
         * @param resultSet object for getting result from database response
         * @param rowNumber number of row with data from which UserModel will be filled
         * @return filled MessageModel with information from database
         * @throws SQLException anytime during database operations
         */

        public UserModel mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            UserModel result = new UserModel();
            result.setId(resultSet.getInt("user_id"));
            result.setUserName(resultSet.getString("user_name"));
            result.setEmail(resultSet.getString("user_email"));
            result.setPassword(resultSet.getString("user_password"));
            result.setCountry(resultSet.getString("user_country"));
            result.setGender(resultSet.getString("user_gender"));
            result.setCreationDateTime(resultSet.getString("creation_date_time"));
            return result;
        }
    }
}
