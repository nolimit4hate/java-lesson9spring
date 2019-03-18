package com.tmg.lesson9.service.user;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.user.UserDao;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.validator.user.UserServiceValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceImplTest {

    @Mock
    private UserDao userDao;
    @Mock
    private UserServiceValidator userServiceValidator;
// tested class
    @InjectMocks
    private UserService userService = new DefaultUserServiceImpl();

    private UserModel userValid;
    private UserModel userInvalid;
    private String userNameValid;
    private String userNameInvalid;
    private String userNameNull;
    private String userNameEmpty;

    @Before
    public void setUp() throws Exception {
        userValid = new UserModel(
                1,
                "vasia",
                "vasia@vas.ia",
                "123123",
                "USA",
                "Male",
                "2019-10-10 20:20:20"
        );
        userInvalid = userValid;
        userInvalid.setUserName("v asia");
        userNameValid = "vasia";
        userNameInvalid = "va sia";
        userNameNull = null;
        userNameEmpty = "";
    }

//////////
//  testing DefaultUserServiceImpl.getUserModelByName()
//////////

    @Test
    public void testGetUserByNameWithValidUserNameAndUserModel() {
        // given
        String userName = this.userNameValid;
        UserModel user = this.userValid;
        Mockito.when(userServiceValidator.isUserNameValid(userName)).thenReturn(true);
        Mockito.when(userDao.selectUserByNameFromUsers(userName)).thenReturn(user);
        Mockito.when(userServiceValidator.isUserModelValid(user)).thenReturn(true);
        // when
        userService.getUserModelByName(userName);
        boolean isNameValid = userServiceValidator.isUserNameValid(userName);
        UserModel userModel = userDao.selectUserByNameFromUsers(userName);
        boolean isUserValid = userServiceValidator.isUserModelValid(userModel);
        // then
        Assert.assertTrue("validNameResult must be true", isNameValid);
        Assert.assertEquals(user, userModel);
        Assert.assertTrue("validNameResult must be true", isUserValid);
    }

    @Test(expected = CustomServiceException.class)
    public void testGetUserByNameWithValidUserNameAndInvalidUserModel() {
        // given
        String userName = this.userNameValid;
        UserModel user = this.userInvalid;
        Mockito.when(userServiceValidator.isUserNameValid(userName)).thenReturn(true);
        Mockito.when(userDao.selectUserByNameFromUsers(userName)).thenReturn(user);
        Mockito.when(userServiceValidator.isUserModelValid(user)).thenThrow(CustomServiceException.class);
        // when
        userService.getUserModelByName(userName);
        boolean isNameValid = userServiceValidator.isUserNameValid(userName);
        UserModel userModel = userDao.selectUserByNameFromUsers(userName);
        boolean isUserValid = userServiceValidator.isUserModelValid(userModel);
        // then
        Assert.assertTrue("validNameResult must be true", isNameValid);
        Assert.assertEquals(user, userModel);
        Assert.assertEquals(userName, userModel.getUserName());
    }

    @Test(expected = CustomDaoException.class)
    public void testGetUserByNameWithDaoException(){
        // given
        String userName = this.userNameValid;
        Mockito.when(userServiceValidator.isUserNameValid(userName)).thenReturn(true);
        Mockito.when(userDao.selectUserByNameFromUsers(userName)).thenThrow(CustomDaoException.class);
        // when
        userService.getUserModelByName(userName);
        boolean isNameValid = userServiceValidator.isUserNameValid(userName);
        UserModel userModel = userDao.selectUserByNameFromUsers(userName);
        // then
        Assert.assertTrue("validNameResult must be true", isNameValid);
    }

    @Test(expected = CustomServiceException.class)
    public void testGetUserByNameWithInvalidName(){
        // given
        String userName = this.userNameInvalid;
        Mockito.when(userServiceValidator.isUserNameValid(userName)).thenThrow(CustomServiceException.class);
        // when
        userService.getUserModelByName(userName);
        // then
    }

//////////
//  testing DefaultUserServiceImpl.isUserExistByNamePassword()
//////////

    @Test
    public void testIsUserExistByNamePasswordWithHappyEnd() {
        // given
        String name = this.userNameValid;
        String password = "123123";
        Mockito.when(userServiceValidator.isUserNameValid(name)).thenReturn(true);
        Mockito.when(userServiceValidator.isUserPasswordValid(password)).thenReturn(true);
        Mockito.when(userDao.selectUserByNamePasswordFromUsers(name, password)).thenReturn(true);

        //when
        userService.isUserExistByNamePassword(name, password);
        boolean isNameValid = userServiceValidator.isUserNameValid(name);
        boolean isPasswordValid = userServiceValidator.isUserPasswordValid(password);
        boolean isUserExist = userDao.selectUserByNamePasswordFromUsers(name, password);

        // then
        Assert.assertTrue(isNameValid);
        Assert.assertTrue(isPasswordValid);
        Assert.assertTrue(isUserExist);
    }

    @Test(expected = CustomDaoException.class)
    public void testIsUserExistByNamePasswordWithDaoException() {
        // given
        String name = this.userNameValid;
        String password = "123123";
        Mockito.when(userServiceValidator.isUserNameValid(name)).thenReturn(true);
        Mockito.when(userServiceValidator.isUserPasswordValid(password)).thenReturn(true);
        Mockito.when(userDao.selectUserByNamePasswordFromUsers(name, password)).thenThrow(CustomDaoException.class);

        //when
        userService.isUserExistByNamePassword(name, password);
        boolean isNameValid = userServiceValidator.isUserNameValid(name);
        boolean isPasswordValid = userServiceValidator.isUserPasswordValid(password);
        boolean isUserExist = userDao.selectUserByNamePasswordFromUsers(name, password);

        // then
        Assert.assertTrue(isNameValid);
        Assert.assertTrue(isPasswordValid);
    }

    @Test(expected = CustomServiceException.class)
    public void testIsUserExistByNamePasswordWithInvalidPassword() {
        // given
        String name = this.userNameValid;
        String password = "1";
        Mockito.when(userServiceValidator.isUserNameValid(name)).thenReturn(true);
        Mockito.when(userServiceValidator.isUserPasswordValid(password)).thenThrow(CustomServiceException.class);

        //when
        userService.isUserExistByNamePassword(name, password);
        boolean isNameValid = userServiceValidator.isUserNameValid(name);
        boolean isPasswordValid = userServiceValidator.isUserPasswordValid(password);

        // then
        Assert.assertTrue(isNameValid);
    }

    @Test(expected = CustomServiceException.class)
    public void testIsUserExistByNamePasswordWithInvalidName() {
        // given
        String name = this.userNameInvalid;
        String password = "123123";
        Mockito.when(userServiceValidator.isUserNameValid(name)).thenThrow(CustomServiceException.class);

        //when
        userService.isUserExistByNamePassword(name, password);
        // then
    }

//////////
//  testing DefaultUserServiceImpl.addUser()
//////////

    @Test
    public void testAddUserWithHappyEnd() {
        // given
        UserModel user = this.userValid;
        Mockito.when(userServiceValidator.isUserModelValid(user)).thenReturn(true);
        Mockito.when(userDao.insertIntoUsers(user)).thenReturn(true);

        // when
        userService.addUser(user);
        boolean isUserValid = userServiceValidator.isUserModelValid(user);
        boolean isUserAddedToDb = userDao.insertIntoUsers(user);
        // then
        Assert.assertTrue(isUserValid);
        Assert.assertTrue(isUserAddedToDb);
    }

    @Test(expected = CustomDaoException.class)
    public void testAddUserWithDaoException() {
        // given
        UserModel user = this.userValid;
        Mockito.when(userServiceValidator.isUserModelValid(user)).thenReturn(true);
        Mockito.when(userDao.insertIntoUsers(user)).thenThrow(CustomDaoException.class);

        // when
        userService.addUser(user);
        boolean isUserValid = userServiceValidator.isUserModelValid(user);
        boolean isUserAddedToDb = userDao.insertIntoUsers(user);
        // then
        Assert.assertTrue(isUserValid);
    }

    @Test(expected = CustomServiceException.class)
    public void testAddUserWithInvalidUser() {
        // given
        UserModel user = this.userValid;
        Mockito.when(userServiceValidator.isUserModelValid(user)).thenThrow(CustomServiceException.class);

        // when
        userService.addUser(user);
        // then
    }
}