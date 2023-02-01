package Page;

import Data.DataHelper;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement entryField = $("[data-test-id=code] input");

    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        entryField.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        entryField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }

}
