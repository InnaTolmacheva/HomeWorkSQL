package ru.netology.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    @BeforeEach
    void cleanUp() {
        var DataHelper = new DataHelper();
        DataHelper.cleanCode();
    }

    @BeforeAll
    static void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void successfulAuthorization() {
        var loginPage = new LoginPage();

        var authInfo = DataHelper.getAuthInfo(); //авторизация
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.validVerify();
    }
}
