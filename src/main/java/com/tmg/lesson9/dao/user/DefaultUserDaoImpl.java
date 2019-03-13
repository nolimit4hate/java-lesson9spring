package com.tmg.lesson9.dao.user;


import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.dao.validator.user.UserDaoValidator;
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

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.Validate.isTrue;

@Repository("userDao")
public class DefaultUserDaoImpl implements UserDao {

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Resource
    UserDaoValidator userDaoValidator;

    private final static String USERS_TABLE = "users";
    private final static String USERS_INSERT_PARAMS_SEPARATED_BY_COMMA =
            "user_name, user_email, user_password, user_gender, user_country, creation_date_time";
    private final static String USERS_SELECT_PARAMS_SEPARATED_BY_COMMA =
            "user_id, " + USERS_INSERT_PARAMS_SEPARATED_BY_COMMA;

    @Autowired
    public DefaultUserDaoImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

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
        try{
            userModel = namedJdbcTemplate.queryForObject(querySelectUserByName, mapSqlParameterSource, userMapper);
            return userModel;
        } catch (DataIntegrityViolationException e) {
            throw new CustomDaoException("DAO Error: entering name={" + userName + "} is not found in database ", e);
        }
    }

    @Override
    public boolean selectUserByNamePasswordFromUsers(String userName, String userPassword) throws CustomDaoException {
        isTrue(isNotBlank(userName), "User name is blank");

        userDaoValidator.isUserNameValid(userName);
        userDaoValidator.isUserPasswordValid(userPassword);
        UserModel userModel;
        String querySelectUserByNamePass =
                "SELECT " + USERS_SELECT_PARAMS_SEPARATED_BY_COMMA +
                " FROM " + USERS_TABLE + " WHERE user_name= :name AND user_password= :password";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", userName);
        mapSqlParameterSource.addValue("password", userPassword);
        UserMapper userMapper = new UserMapper();
        try {
            userModel = namedJdbcTemplate.queryForObject(querySelectUserByNamePass, mapSqlParameterSource, userMapper);
            userDaoValidator.isUserModelValid(userModel);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new CustomDaoException("DAO Error: entering name={" + userName +
                    "} and password={" + userPassword + "} ", e);
        }
    }

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

    private Map<String, String> getUserParamMap(UserModel user){
        Map<String, String> userParam = new HashMap();
        userParam.put("name", user.getUserName());
        userParam.put("email", user.getEmail());
        userParam.put("password", user.getPassword());
        userParam.put("gender", user.getGender());
        userParam.put("country", user.getCountry());
        userParam.put("dateTime", user.getCreationDateTime());
        return userParam;
    }

    private static final class UserMapper implements RowMapper<UserModel> {
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
