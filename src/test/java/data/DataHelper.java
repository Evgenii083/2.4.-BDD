package data;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Value;

import java.util.HashMap;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$$;

public class DataHelper {
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }


    @Value
    public static class CardInfo {
        private String cardNumber;
    }


    public static String getDebitCard(String cardNumber) {
        HashMap<String, String> cards = new HashMap<>();
        cards.put("first", "5559 0000 0000 0001");
        cards.put("second", "5559 0000 0000 0002");
        return cards.get(cardNumber);
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static VerificationCode getInvalidVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("54325");
    }

    public static int getValidTransferSum(int balance) {
        return new Random().nextInt(balance) + 1;
    }

    public static int getInvalidTransferSum(int balance) {
        return Math.abs(balance) + new Random().nextInt(10000);
    }
}
