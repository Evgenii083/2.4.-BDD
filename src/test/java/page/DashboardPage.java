package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement dashboardHeading = $x("//*[contains(text(),'Ваши карты')]");
    private ElementsCollection chooseCard = $$("[data-test-id=action-deposit]");

    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public void visibleCheck() {
        dashboardHeading.shouldBe(visible);
    }

    public ReplenishPage topUpCard(int card) {
        chooseCard.get(card).click();
        return new ReplenishPage();
    }

    public int getBalanceForCard(int index) {
        val text = cards.get(index).text();
        return extractBalance(text);
    }


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);

    }
}
