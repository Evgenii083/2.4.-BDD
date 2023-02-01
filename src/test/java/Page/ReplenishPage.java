package Page;

import Data.DataHelper;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ReplenishPage {
    private SelenideElement replenishHeading = $x("//*[contains(text(),'Пополнение карты')]");

    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement to = $("[data-test-id=to] input");
    private SelenideElement submit = $("[data-test-id=action-transfer]");


    public ReplenishPage() {
    }
    public void moneyTransfer(int transferSum, int deductFrom){
        amount.setValue(String.valueOf(transferSum));
        from.setValue(DataHelper.getDebitCard(deductFrom));
        submit.click();
        new DashboardPage();
    }
}
