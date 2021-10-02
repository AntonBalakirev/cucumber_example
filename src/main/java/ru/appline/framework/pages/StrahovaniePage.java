package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class StrahovaniePage extends BasePage {

    final String insuranceProgramXpath = "//h3[text()='%s']";

    WebElement insuranceProgramElement;

    final String checkoutOnlineButtonXpath = "//a[@data-test-id='PageTeaserDict_button']";

    final String orderOnWebSiteXpath = "//span[text()='Оформить на сайте']/parent::a";

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
        WebElement checkoutOnlineButton = getDriver().findElement(By.xpath(checkoutOnlineButtonXpath));
        elementToBeClickable(checkoutOnlineButton);
        checkoutOnlineButton.click();
        return app.getTariffPage();
    }

    /**
     * Кликаем на кнопку "Оформить онлайн"
     *
     * @return TarifPage - т.е. переходим на страницу {@link TariffPage}
     */
    public TariffPage clickOrderButton() {
        String parentHandle = getDriver().getWindowHandle();
        WebElement checkoutOnlineButton = getDriver().findElement(By.xpath(orderOnWebSiteXpath));
        scrollToElementJs(checkoutOnlineButton);
        elementToBeClickable(checkoutOnlineButton);
        checkoutOnlineButton.click();
        Set<String> windowHandles = getDriver().getWindowHandles();
        Iterator<String> windowIterator = windowHandles.iterator();
        while (windowIterator.hasNext()) {
            String newHandle = windowIterator.next();
            if(!newHandle.equals(parentHandle)) {
                getDriver().switchTo().window(newHandle);
            }
        }
        return app.getTariffPage();
    }

}
