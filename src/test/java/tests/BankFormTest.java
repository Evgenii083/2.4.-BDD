package tests;

import Data.DataHelper;
import Page.DashboardPage;
import Page.LoginPage;
import Page.ReplenishPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class BankFormTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldTransferMoneyFromCardToCard() {
        var LoginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var DashboardPage = new DashboardPage();
        var ReplenishPage = new ReplenishPage();
        DashboardPage.addTo(0);
        ReplenishPage.moneyTransfer(500, 1);

    }

    @Test
    public void shouldGetBalance() {
        var LoginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var DashboardPage = new DashboardPage();
        var balanceFirstCard = DashboardPage.getBalanceForCard(0);
        var balanceSecondCard = DashboardPage.getBalanceForCard(1);
        System.out.println(balanceFirstCard + "\n" + balanceSecondCard);
    }

    @Test
    public void shouldTransferMoneyFromCardToCardAndCompereBalance() {
        var LoginPage = new LoginPage();
        var DashboardPage = new DashboardPage();
        var ReplenishPage = new ReplenishPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var balance = DashboardPage.getBalanceForCard(0);
        DashboardPage.addTo(0);
        ReplenishPage.moneyTransfer(2500, 1);

        DashboardPage.addTo(1);
        ReplenishPage.moneyTransfer(2500, 0);
        var secondBalance = DashboardPage.getBalanceForCard(0);

        System.out.println(balance + "\n" + secondBalance);
        Assertions.assertEquals(balance, secondBalance);
    }

    @Test
    public void shouldNotMakeMoneyTransferIfTheTransferAmountIsTooMuch() {
        var LoginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var DashboardPage = new DashboardPage();
        var ReplenishPage = new ReplenishPage();
        DashboardPage.addTo(1);
        ReplenishPage.moneyTransfer(500_000, 0);
        System.out.println(DashboardPage.getBalanceForCard(0));
        Assertions.assertTrue(DashboardPage.getBalanceForCard(0) >= 0);
    }

}
