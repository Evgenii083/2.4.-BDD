package page;

import data.DataHelper;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class ReplenishPage {
    private SelenideElement replenishHeading = $x("//*[contains(text(),'Пополнение карты')]");

    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement to = $("[data-test-id=to] input");
    private SelenideElement submit = $("[data-test-id=action-transfer]");


    public ReplenishPage() {
    }

    public void moneyTransfer(String card, int sum) {
        amount.setValue(String.valueOf(sum));
        from.setValue(card);
        submit.click();
    }

}
