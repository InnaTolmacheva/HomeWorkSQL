package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {
    private SelenideElement code = $("[data-test-id='code'] input");
    private SelenideElement bottom = $x("//*[text()=\"Продолжить\"]");

    public DashboardPage validVerify() {
        code.setValue(DataHelper.getCode());
        bottom.click();
        return new DashboardPage();

    }

}
