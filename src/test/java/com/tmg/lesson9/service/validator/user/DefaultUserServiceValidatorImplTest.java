package com.tmg.lesson9.service.validator.user;

import com.tmg.lesson9.commons.validator.base.user.BaseUserValidator;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceValidatorImplTest {

    @Mock
    BaseUserValidator baseUserValidator;
    @InjectMocks
    UserServiceValidator userServiceValidator = new DefaultUserServiceValidatorImpl();

    @Before
    public void setUp() throws Exception {
    }

//////////
//  testing DefaultUserServiceValidatorImpl.isUserNameValid()
//////////

    @Test
    public void testIsUserNameValidWithHappyEnd() {
        //given
        String nameValid = "vasia";
        Mockito.when(baseUserValidator.isUserNameValid(nameValid)).thenReturn(true);
        Mockito.when(userServiceValidator.isUserNameValid(nameValid)).thenReturn(true);
        //when
        boolean isNameValidByServiceValidator = userServiceValidator.isUserNameValid(nameValid);
        boolean isNameValidByBaseValidator = baseUserValidator.isUserNameValid(nameValid);
        //then
        Assert.assertTrue(isNameValidByBaseValidator);
        Assert.assertTrue(isNameValidByServiceValidator);
    }

    @Test(expected = CustomServiceException.class)
    public void testIsUserNameValidWithInvalidName() {
        //given
        String nameInvalid = "v";
        Mockito.when(baseUserValidator.isUserNameValid(nameInvalid)).thenThrow(IllegalArgumentException.class);
        Mockito.when(userServiceValidator.isUserNameValid(nameInvalid)).thenThrow(CustomServiceException.class);
        //when
        userServiceValidator.isUserNameValid(nameInvalid);
        baseUserValidator.isUserNameValid(nameInvalid);
        //then
    }

//////////
//  testing DefaultUserServiceValidatorImpl.isUserPasswordValid()
//////////

    @Test
    public void isUserPasswordValidWithHappyEnd() {
        //given
        String passwordValid = "123123";
        Mockito.when(baseUserValidator.isPasswordValid(passwordValid)).thenReturn(true);
        Mockito.when(userServiceValidator.isUserPasswordValid(passwordValid)).thenReturn(true);
        //when
        boolean isPasswordValidByBaseValidator = baseUserValidator.isPasswordValid(passwordValid);
        boolean isPasswordValidByServiceValidator = baseUserValidator.isPasswordValid(passwordValid);
        //then
        Assert.assertTrue(isPasswordValidByServiceValidator);
        Assert.assertTrue(isPasswordValidByBaseValidator);
    }

    @Test(expected = CustomServiceException.class)
    public void isUserPasswordValidWithInvalidPassword() {
        //given
        String passwordInvalid = "1";
        Mockito.when(baseUserValidator.isPasswordValid(passwordInvalid)).thenThrow(IllegalArgumentException.class);
        Mockito.when(userServiceValidator.isUserPasswordValid(passwordInvalid)).thenThrow(CustomServiceException.class);
        //when
        baseUserValidator.isPasswordValid(passwordInvalid);
        baseUserValidator.isPasswordValid(passwordInvalid);
        //then
    }

//////////
//  testing DefaultUserServiceValidatorImpl.isUserModelValid()
//////////

    @Test
    public void testIsUserModelValidWithHappyEnd() {
        //given
        UserModel userValid = new UserModel(
                1,
                "vasia",
                "vasia@vas.ia",
                "123123",
                "USA",
                "Male",
                "2019-10-10 20:20:20"
        );
        Mockito.when(userServiceValidator.isUserModelValid(userValid)).thenReturn(true);
        Mockito.when(baseUserValidator.isUserModelValid(userValid)).thenReturn(true);
        //when
        boolean isUserValidByServiceValidator = userServiceValidator.isUserModelValid(userValid);
        boolean isUserValidByBaseValidator = baseUserValidator.isUserModelValid(userValid);
        //then
        Assert.assertTrue(isUserValidByBaseValidator);
        Assert.assertTrue(isUserValidByServiceValidator);
    }

    @Test(expected = CustomServiceException.class)
    public void testIsUserModelValidWithInvalidUser() {
        //given
        UserModel userInvalid = new UserModel(
                1,
                "v",
                "v@v.v",
                "v",
                "v",
                "v",
                "v"
        );
        Mockito.when(userServiceValidator.isUserModelValid(userInvalid)).thenThrow(CustomServiceException.class);
        Mockito.when(baseUserValidator.isUserModelValid(userInvalid)).thenThrow(IllegalArgumentException.class);
        //when
        userServiceValidator.isUserModelValid(userInvalid);
        baseUserValidator.isUserModelValid(userInvalid);
        //then
    }


}