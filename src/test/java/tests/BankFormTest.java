package tests;

import data.DataHelper;
import page.DashboardPage;
import page.LoginPage;
import page.ReplenishPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankFormTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldTransferMoneyFromCardToCardAndCompereBalance() {
        var LoginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var balanceFirstCard = dashboardPage.getBalanceForCard(0);
        var balanceSecondCard = dashboardPage.getBalanceForCard(1);
        dashboardPage.addTo(0);
        var replenishPage = new ReplenishPage();
        replenishPage.moneyTransfer(2500, 1);
        dashboardPage.addTo(1);
        replenishPage.cleanInput();
        replenishPage.moneyTransfer(2500, 0);

        Assertions.assertAll(
                () -> assertTrue(balanceFirstCard == dashboardPage.getBalanceForCard(0)),
                () -> assertTrue(balanceSecondCard == dashboardPage.getBalanceForCard(1))
        );
    }

    @Test
    public void shouldNotMakeMoneyTransferIfTheTransferAmountIsTooMuch() {
        var LoginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        dashboardPage.addTo(1);
        var replenishPage = new ReplenishPage();
        replenishPage.moneyTransfer(500_000, 0);
        System.out.println(dashboardPage.getBalanceForCard(0));

        Assertions.assertAll(
                () -> assertTrue(dashboardPage.getBalanceForCard(0) >= 0),
                () -> assertTrue(dashboardPage.getBalanceForCard(1) >= 0)
        );
    }
}
