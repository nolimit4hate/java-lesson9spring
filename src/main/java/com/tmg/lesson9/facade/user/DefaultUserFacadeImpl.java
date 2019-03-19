package com.tmg.lesson9.facade.user;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.facade.converter.user.UserConverter;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.facade.util.DateTimeGetter;
import com.tmg.lesson9.facade.validator.user.UserFacadeValidator;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.user.UserService;
import com.tmg.lesson9.web.form.LoginForm;
import com.tmg.lesson9.web.form.ProfileForm;
import com.tmg.lesson9.web.form.RegistrationForm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Class have methods that processing input from controller dao.dao data then validate and convert it to needed format for service layer
 * and send it to service layer.
 * Then validate gotten data from service layer and send it to controller that call facade method.
 * If any input data is invalidate then throw CustomFacadeException.
 */

@Component("userFacade")
public class DefaultUserFacadeImpl implements UserFacade {

    @Resource
    private UserService userService;

    @Resource
    private UserConverter userConverter;

    @Resource
    private UserFacadeValidator userFacadeValidator;

    /**
     * Method check input data for valid. Then call service method userService.getUserModelByName() with input @param userName
     * parameter for getting UserModel object. Then check getting UserModel object for valid and convert it into ProfileForm object
     *
     * @param userName string with dao.dao name information
     * @return ProfileForm object that contains information about dao.dao with dao.dao name= @param userName
     * @throws CustomFacadeException  if userName is invalid or if ProfileForm object is invalid
     * @throws CustomServiceException if service layer throw exception
     * @throws CustomDaoException     if dao layer throw exception
     */

    @Override
    public ProfileForm getProfile(String userName) throws CustomFacadeException, CustomServiceException, CustomDaoException {
        userFacadeValidator.isUserNameValid(userName);
        UserModel userModel = userService.getUserModelByName(userName);
        userFacadeValidator.isUserModelValid(userModel);
        ProfileForm profileForm = userConverter.convertUserModelToProfileForm(userModel);
        return profileForm;
    }

    /**
     * Method check LoginForm object for valid. Then call service userService.isUserExistByNamePassword() method
     * and return result of this calling.
     *
     * @param userNamePassword LoginForm object that contain information about dao.dao name and dao.dao password
     * @return true if userService.isUserExistByNamePassword() return true; exactly if dao.dao with this dao.dao name and password
     * exists in database
     * @throws CustomFacadeException  if LoginForm object is invalid
     * @throws CustomServiceException if service layer throw exception
     * @throws CustomDaoException     if dao layer throw exception
     */

    @Override
    public boolean doLogin(LoginForm userNamePassword) throws CustomFacadeException, CustomServiceException, CustomDaoException {
        String name = userNamePassword.getName();
        String password = userNamePassword.getPassword();
        userFacadeValidator.isUserNameValid(name);
        userFacadeValidator.isUserPasswordValid(password);
        return userService.isUserExistByNamePassword(name, password);
    }

    /**
     * Method validate RegistrationForm object then convert it into UserModel object and add date-time data to UserModel object.
     * After all call service method userService.addUser() and return result of service method.
     *
     * @param registrationForm RegistrationForm object with information about dao.dao
     * @return true if data from RegistrationForm object was edded successfully
     * @throws CustomFacadeException  if RegistrationForm object is invalid
     * @throws CustomServiceException if service layer throw exception
     * @throws CustomDaoException     if dao layer throw exception
     */

    @Override
    public boolean addUser(RegistrationForm registrationForm) throws CustomFacadeException, CustomServiceException, CustomDaoException {
        userFacadeValidator.isRegistrationFormValid(registrationForm);
        UserModel userModel = userConverter.convertRegistrationFormToUserModel(registrationForm);
        userModel.setCreationDateTime(DateTimeGetter.getCurrentDateTime());
        return userService.addUser(userModel);
    }
}
