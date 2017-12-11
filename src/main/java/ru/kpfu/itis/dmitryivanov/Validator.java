package ru.kpfu.itis.dmitryivanov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.dmitryivanov.service.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class Validator extends ResponseCreator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String USER_NAMES_REGEX = "^([а-яА-ЯёЁa-zA-Z]\\s*)+$";
    private final String EMAIL_REGEX = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private final String PASSWORD_REGEX = ".{6,20}";

    private Pattern pattern;
    private Matcher matcher;

    private UserService userService;

    @Autowired
    public Validator(UserService userService) {
        this.userService = userService;
    }


    public ResponseEntity<ApiResponse<String>> getRegistrationErrorResponse(RequestUserJson requestUserJson
    ) {
        if (!this.isUsernameCorrect(requestUserJson.getUsername())) {
            logger.warn("Validator: Email " + requestUserJson.getUsername() + " is incorrect!");
            return createBadResponse("Email is incorrect");
        }
        if (this.isUsernameAlreadyRegistered(requestUserJson.getUsername())) {
            logger.warn("Validator: User with this email " + requestUserJson.getUsername() + " already registered!");
            return createBadResponse("User with this email already registered");
        }
        if (!this.isPasswordCorrect(requestUserJson.getPassword())) {
            logger.warn("Validator: Password " + requestUserJson.getPassword() + " is incorrect!");
            return createBadResponse("Password is incorrect");
        }
        return null;
    }


    public boolean isUsernameValid(String username) {
        if (!this.isUsernameCorrect(username)) {
            logger.warn("Validator: Email " + username + " is incorrect!");
            return false;
        }
        if (this.isUsernameAlreadyRegistered(username)) {
            logger.warn("Validator: User with this email " + username + " already registered!");
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
        pattern = Pattern.compile(EMAIL_REGEX);
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
}
