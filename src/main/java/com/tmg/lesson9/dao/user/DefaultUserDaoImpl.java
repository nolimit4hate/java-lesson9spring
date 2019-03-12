package com.tmg.lesson9.dao.user;


import com.tmg.lesson9.model.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DefaultUserDaoImpl implements UserDao {

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public DefaultUserDaoImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public UserModel selectUserNameFromUsers(String userName) {
        UserModel userModel;
        String querySelectUserByName = "SELECT" +
                " user_id, user_name, user_email, user_password, user_gender, user_country, creation_date_time" +
                " FROM users WHERE user_name= :name";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", userName);
        UserMapper userMapper = new UserMapper();
        userModel = namedJdbcTemplate.queryForObject(querySelectUserByName, mapSqlParameterSource, userMapper);
        return userModel;
    }

    @Override
    public boolean selectUserNamePasswordFromUsers(String userName, String userPassword) {
        UserModel userModel = null;
        String querySelectUserByNamePass = "SELECT" +
                " user_id, user_name, user_email, user_password, user_gender, user_country, creation_date_time" +
                " FROM users WHERE user_name= :name AND user_password= :password";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", userName);
        mapSqlParameterSource.addValue("password", userPassword);
        UserMapper userMapper = new UserMapper();
        userModel = namedJdbcTemplate.queryForObject(querySelectUserByNamePass, mapSqlParameterSource, userMapper);
        if(userModel == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean insertIntoUsers(UserModel user) {
        String queryInputUser = "INSERT INTO users " +
                "(user_name, user_email, user_password, user_gender, user_country, creation_date_time)" +
                " values(:name, :email, :password, :gender, :country, :dateTime);";
        Map<String, String> userParam = getUserParamMap(user);
        namedJdbcTemplate.update(queryInputUser, userParam);
        return true;
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
