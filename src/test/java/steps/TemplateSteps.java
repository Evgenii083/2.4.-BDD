package steps;


import data.DataHelper;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import page.DashboardPage;
import page.LoginPage;
import page.ReplenishPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static ReplenishPage replenishPage;
    private static VerificationPage verificationPage;


    @Пусть("пользователь залогинен с именем «vasya» и паролем «qwerty123»")
    public void loginUser (){
        open("http://localhost:9999");
       LoginPage loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        var verificationCode = DataHelper.getVerificationCode(DataHelper.getAuthInfo());
        verificationPage.validVerify(verificationCode);
    }

    @Когда("пользователь переводит 5000 рублей с карты с номером {int} на свою  карту {int} с главной страницы")
    public void transferMoney(int int2 , int int3) {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.addTo(int3);
        ReplenishPage replenishPage = new ReplenishPage();
        replenishPage.moneyTransfer(5000,int2);
    }
    @Тогда("баланс его 1 карты из списка на главной странице должен стать 15 000 рублей")
    public void checkBalance(){
//        DashboardPage dashboardPage = new DashboardPage();
//        dashboardPage.getBalanceForCard(0);
        Assertions.assertEquals(15_000, dashboardPage.getBalanceForCard(0));
    }


}

