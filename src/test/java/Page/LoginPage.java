package Page;

import Data.DataHelper;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement validLogin = $("[data-test-id=login] input");
    private SelenideElement validPassword = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");


    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        validLogin.setValue(info.getLogin());
        validPassword.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}

