package data;

import lombok.Value;

import java.util.HashMap;

public class DataHelper {
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }


    public static String getDebitCard(int cardNumber) {
        HashMap<Integer, String> cards = new HashMap<>();
        cards.put(0, "5559 0000 0000 0001");
        cards.put(1, "5559 0000 0000 0002");
        return cards.get(cardNumber);
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
}
