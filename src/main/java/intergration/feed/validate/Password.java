package intergration.feed.validate;

import intergration.feed.common.error.wanted.ErrorCode;
import intergration.feed.common.error.wanted.WantedException;
import java.util.regex.Pattern;

public class Password {

    public static void validate(String password) {
        if(Pattern.matches("^(?=.*[a-zA-Z!@#\\$%^&*()-+=._]).+$", password)) {
            throw new WantedException(ErrorCode.MUST_NOT_BE_ALL_DIGITS);
        }

        if (Pattern.matches("^(?=(.*[A-Za-z]){1,})(?=(.*[!@#\\$%^&*()-+=._]){1,})(?!.*(.)\\1{2,})(?!.*\\s).+$", password)) {
            throw new WantedException(ErrorCode.NO_REPEAT_CHARACTERS_AND_MULTIPLE_CRITERIA);
        }
    }

}
