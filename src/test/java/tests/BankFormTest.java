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
    public void happyTransactionFromFirstCardToSecond() {
        var LoginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var initialFirstCardBalance = dashboardPage.getBalanceForCard(0);
        var initialSecondCardBalance = dashboardPage.getBalanceForCard(1);
        dashboardPage.topUpCard(1);
        var replenishPage = new ReplenishPage();
        var transferSum = DataHelper.getValidTransferSum(initialFirstCardBalance);
        var writeOffCard = DataHelper.getDebitCard("first");
        replenishPage.moneyTransfer(writeOffCard, transferSum);
        dashboardPage.visibleCheck();

        Assertions.assertAll(
                () -> assertTrue(initialFirstCardBalance > dashboardPage.getBalanceForCard(0)),
                () -> assertTrue(initialSecondCardBalance < dashboardPage.getBalanceForCard(1))
        );
    }

    @Test
    public void happyTransactionFromSecondCardToFirst() {
        var LoginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var initialFirstCardBalance = dashboardPage.getBalanceForCard(0);
        var initialSecondCardBalance = dashboardPage.getBalanceForCard(1);
        dashboardPage.topUpCard(0);
        var replenishPage = new ReplenishPage();
        var transferSum = DataHelper.getValidTransferSum(initialSecondCardBalance);
        var writeOffCard = DataHelper.getDebitCard("second");
        replenishPage.moneyTransfer(writeOffCard, transferSum);
        dashboardPage.visibleCheck();

        Assertions.assertAll(
                () -> assertTrue(initialFirstCardBalance < dashboardPage.getBalanceForCard(0)),
                () -> assertTrue(initialSecondCardBalance > dashboardPage.getBalanceForCard(1))
        );
    }

    @Test
    public void shouldNotMakeManyTransferInCaseTransferSumIsTooMuch() {
        var LoginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var initialFirstCardBalance = dashboardPage.getBalanceForCard(0);
        var initialSecondCardBalance = dashboardPage.getBalanceForCard(1);
        dashboardPage.topUpCard(0);
        var replenishPage = new ReplenishPage();
        var transferSum = DataHelper.getInvalidTransferSum(initialSecondCardBalance);
        var writeOffCard = DataHelper.getDebitCard("second");
        replenishPage.moneyTransfer(writeOffCard, transferSum);
        dashboardPage.visibleCheck();

        Assertions.assertAll(
                () -> assertTrue(dashboardPage.getBalanceForCard(1) > 0),
                () -> assertTrue(dashboardPage.getBalanceForCard(0) == initialFirstCardBalance)
        );
    }
}
