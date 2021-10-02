package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class TariffPage extends BasePage {

    @FindBy(xpath = "//h2")
    WebElement pageTitle;

    final String insuranceCoverageAmountXpath = "//h3[text()='%s']";

    @FindBy(xpath = "//button[text()='Оформить']")
    WebElement checkoutButton;

    /**
     * Проверка открытия страницы
     *
     * @return TariffPage - т.е. остаемся на этой странице
     */
    public TariffPage checkOpenPage(String expectedTitle) {

        elementToBeVisible(pageTitle);
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                expectedTitle, pageTitle.getText());
        return this;
    }

    /**
     * Выбор тарифа страхования
     *
     * @return TariffPage - т.е. остаемся на этой странице
     */
    public TariffPage selectCoverage(String coverageSum) {
        WebElement insuranceCoverageAmount = getDriver().findElement(
                By.xpath(String.format(insuranceCoverageAmountXpath, coverageSum))
        );
        scrollToElementJs(insuranceCoverageAmount);
        insuranceCoverageAmount.click();
        return this;
    }

    /**
     * Клик по кнопке "Оформить"
     *
     * @return RegistrationFormPage - т.е. переходим на страницу {@link ru.appline.framework.pages.RegistrationFormPage}
     */
    public RegistrationFormPage clickCheckoutButton() {
        scrollToElementJs(checkoutButton);
        elementToBeClickable(checkoutButton).click();
        return app.getRegistrationFormPage();
    }

}
