package ru.kpfu.itis.dmitryivanov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.dmitryivanov.requests.RequestUserRegistrationJson;
import ru.kpfu.itis.dmitryivanov.response.ApiResponse;
import ru.kpfu.itis.dmitryivanov.response.ResponseCreator;
import ru.kpfu.itis.dmitryivanov.service.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class Validator extends ResponseCreator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String EMAIL_REGEX = "^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$";
    private final String USER_NAMES_REGEX = "^([а-яА-ЯёЁa-zA-Z\\n]\\s*)+$";
    private final String USERNAME = "^[a-zA-Z][a-zA-Z0-9-_\\.]{4,20}$";
    private final String PASSWORD_REGEX = ".{6,20}";
    private final String PHONE_REGEX = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";

    private Pattern pattern;
    private Matcher matcher;

    private UserService userService;

    @Autowired
    public Validator(UserService userService) {
        this.userService = userService;
    }


    public ResponseEntity<ApiResponse<String>> getRegistrationErrorResponse(RequestUserRegistrationJson requestUserRegistrationJson) {
        if (!this.isEmailCorrect(requestUserRegistrationJson.getEmail())) {
            logger.warn("Validator: Email " + requestUserRegistrationJson.getEmail() + " is incorrect!");
            return createBadResponse("Email is incorrect");
        }
        if (this.isEmailAlreadyRegistered(requestUserRegistrationJson.getEmail())) {
            logger.warn("Validator: User with this email " + requestUserRegistrationJson.getEmail() + " already registered!");
            return createBadResponse("User with this email already registered");
        }
        if (!this.isUsernameCorrect(requestUserRegistrationJson.getUsername())) {
            logger.warn("Validator: Username " + requestUserRegistrationJson.getUsername() + " is incorrect!");
            return createBadResponse("Username is incorrect");
        }
        if (this.isUsernameAlreadyRegistered(requestUserRegistrationJson.getUsername())) {
            logger.warn("Validator: User with this username " + requestUserRegistrationJson.getUsername() + " already registered!");
            return createBadResponse("User with this username already registered");
        }
        if (!this.isPhoneCorrect(requestUserRegistrationJson.getPhoneNumber())) {
            logger.warn("Validator: Phone " + requestUserRegistrationJson.getPhoneNumber() + " is incorrect!");
            return createBadResponse("Phone is incorrect");
        }
        if (this.isPhoneAlreadyRegistered(requestUserRegistrationJson.getPhoneNumber())) {
            logger.warn("Validator: User with this phone " + requestUserRegistrationJson.getPhoneNumber() + " already registered!");
            return createBadResponse("User with this phone already registered");
        }
        if (!this.isPasswordCorrect(requestUserRegistrationJson.getPassword())) {
            logger.warn("Validator: Password " + requestUserRegistrationJson.getPassword() + " is incorrect!");
            return createBadResponse("Password is incorrect");
        }
        if (!this.isNameCorrect(requestUserRegistrationJson.getFio())) {
            logger.warn("Validator: firstname " + requestUserRegistrationJson.getFio() + " is incorrect!");
            return createBadResponse("FirstName is incorrect");

        }
        return null;
    }


    public boolean isUsernameValid(String username) {
        if (!this.isUsernameCorrect(username)) {
            logger.warn("Validator: Username " + username + " is incorrect!");
            return false;
        }
        if (this.isUsernameAlreadyRegistered(username)) {
            logger.warn("Validator: User with this username " + username + " already registered!");
            return false;
        }
        return true;
    }

    private boolean isNameCorrect(String name) {
        pattern = Pattern.compile(USER_NAMES_REGEX);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isUsernameCorrect(String username) {
        pattern = Pattern.compile(USERNAME);
        matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean isUsernameAlreadyRegistered(String username) {
        return userService.findOneByUsername(username) != null;
    }


    public boolean isPasswordCorrect(String password) {
        pattern = Pattern.compile(PASSWORD_REGEX);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isEmailValid(String email) {
        if (!this.isEmailCorrect(email)) {
            logger.warn("Validator: Email " + email + " is incorrect!");
            return false;
        }
        if (this.isEmailAlreadyRegistered(email)) {
            logger.warn("Validator: User with this email " + email + " already registered!");
            return false;
        }
        return true;
    }

    private boolean isEmailCorrect(String email) {
        pattern = Pattern.compile(EMAIL_REGEX);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isEmailAlreadyRegistered(String email) {
        return userService.findOneByEmail(email) != null;
    }

    private boolean isPhoneCorrect(String phone) {
        pattern = Pattern.compile(PHONE_REGEX);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    private boolean isPhoneAlreadyRegistered(String phone) {
        return userService.findOneByPhone(phone) != null;
    }

}
