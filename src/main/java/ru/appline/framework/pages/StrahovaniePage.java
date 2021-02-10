package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class StrahovaniePage extends BasePage {

    final String insuranceProgramXpath = "//h3[text()='%s']";

    WebElement insuranceProgramElement;

    final String checkoutOnlineButtonXpath = "../../following-sibling::div//b[text()='Оформить онлайн']/..";

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return StrahovaniePage - т.е. остаемся на этой странице
     */
    public StrahovaniePage checkOpenPage(String title) {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                title, getDriver().getTitle());
        return this;
    }

    /**
     * Переход к выбранной программе страхования
     *
     * @return StrahovaniePage - т.е. остаемся на этой странице
     */
    public StrahovaniePage selectInsuranceProgram(String insuranceProgram) {
        insuranceProgramElement = getDriver().findElement(
                By.xpath(String.format(insuranceProgramXpath, insuranceProgram))
        );
        scrollToElementJs(insuranceProgramElement);
        return this;
    }

    /**
     * Кликаем на кнопку "Оформить онлайн"
     *
     * @return TarifPage - т.е. переходим на страницу {@link TariffPage}
     */
    public TariffPage clickCheckoutButton() {
        WebElement checkoutOnlineButton = insuranceProgramElement.findElement(By.xpath(checkoutOnlineButtonXpath));
        elementToBeClickable(checkoutOnlineButton);
        checkoutOnlineButton.click();
        return app.getTariffPage();
    }

}
