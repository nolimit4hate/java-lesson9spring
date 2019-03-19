package com.tmg.lesson9.dao.user;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.user.UserModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appContextDBConfig.xml")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextBeforeModesTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup("/com.tmg.lesson9/dao/user/users-dataset.xml")
public class DefaultUserDaoImplTest {

    @Resource
    private UserDao userDao;

    private UserModel userHelloboy;



    @Before
    public void setUp() throws Exception {
        UserModel userHelloboy = new UserModel();
        userHelloboy.setId(1);
        userHelloboy.setUserName("helloboy");
        userHelloboy.setPassword("123123");
        userHelloboy.setEmail("helloboy@boy.ru");
        userHelloboy.setCountry("Ukraine");
        userHelloboy.setGender("Male");
        userHelloboy.setCreationDateTime("2019-03-11 22:32:56");
        this.userHelloboy = userHelloboy;
    }

    @After
    public void tearDown() throws Exception {
    }

//////////
//  test UserDao.selectUserByNameFromUsers(String userName)
//////////


    @Test
    public void testSelectUserByNameFromUsers_WithHappyEnd() {
        //given
        String userName = "helloboy";
        //when
        UserModel user = userDao.selectUserByNameFromUsers(userName);
        //then
        Assert.assertEquals(this.userHelloboy, user);
    }

    @Test(expected = CustomDaoException.class)
    public void testSelectUserByNameFromUsers_WithDaoException() {
        //given
        String userName = "hellboy";
        //when
        userDao.selectUserByNameFromUsers(userName);
        //then
        //throw CustomDaoException
    }

//////////
//  test UserDao.selectUserByNamePasswordFromUsers(String userName, String userPassword)
//////////

    @Test
    public void testSelectUserByNamePasswordFromUsers_WithHappyEnd() {
        //given
        String userName = "helloboy";
        String userPassword = "123123";
        //when
        boolean isUserExist = userDao.selectUserByNamePasswordFromUsers(userName, userPassword);
        //then
        Assert.assertTrue(isUserExist);
    }

    @Test(expected = CustomDaoException.class)
    public void testSelectUserByNamePasswordFromUsers_WithWrongPassword() {
        //given
        String userName = "helloboy";
        String userPassword = "1231233";
        //when
        userDao.selectUserByNamePasswordFromUsers(userName, userPassword);
        //then
    }

    @Test(expected = CustomDaoException.class)
    public void testSelectUserByNamePasswordFromUsers_WithWrongName() {
        //given
        String userName = "hellboy";
        String userPassword = "123123";
        //when
        userDao.selectUserByNamePasswordFromUsers(userName, userPassword);
        //then
    }

//////////
//  test UserDao.insertIntoUsers(UserModel dao)
//////////

    @Test
    public void testInsertIntoUsers_WithHappyEnd() {
        //given
        UserModel user = this.userHelloboy;
        String userName = "vasia";
        String userEmail = "vasia@vas.ia";
        user.setUserName(userName);
        user.setEmail(userEmail);
        //when
        boolean isUserInserted = userDao.insertIntoUsers(user);
        UserModel userGotFromDB = userDao.selectUserByNameFromUsers(userName);
        //then
        Assert.assertTrue(isUserInserted);
        Assert.assertEquals(user, userGotFromDB);
    }

    @Test(expected = CustomDaoException.class)
    public void testInsertIntoUsers_WithExistingEmail() {
        //given
        UserModel user = this.userHelloboy;
        String userName = "koliaa";
        user.setUserName(userName);
        //when
        userDao.insertIntoUsers(user);
        userDao.selectUserByNameFromUsers(userName);
        //then
    }

    @Test(expected = CustomDaoException.class)
    public void testInsertIntoUsers_WithExistingName() {
        //given
        UserModel user = this.userHelloboy;
        String userEmail = "vasia@vas.ia";
        user.setEmail(userEmail);
        //when
        userDao.insertIntoUsers(user);
        userDao.selectUserByNameFromUsers(user.getUserName());
        //then
    }


}