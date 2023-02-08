package page;

import com.codeborne.selenide.Condition;
import data.DataHelper;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {
    private SelenideElement entryField = $("[data-test-id=code] input");

    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement errorMessage = $("[data-test-id=error-notification]");
    private SelenideElement notificationTitle = $x("//div[contains(@class,'notification__title')]");
    private SelenideElement notificationContent = $x("//div[contains(@class,'notification__content')]");

    public VerificationPage() {
        entryField.shouldBe(visible);
    }

    public void errorMessage() {
        errorMessage.shouldBe(visible).shouldHave().shouldHave(Condition.text("Ошибка" + "\n" + "Ошибка!"));
        notificationContent.shouldBe(visible).shouldHave(Condition.text("Неверно указан код! Попробуйте ещё раз."));
    }

    public void setErrorMessage2() {
        errorMessage.shouldBe(visible).shouldHave().shouldHave(Condition.text("Ошибка" + "\n" + "Ошибка!"));
        notificationContent.shouldBe(visible).shouldHave(Condition.text("Превышено количество попыток ввода кода!"));
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        entryField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
