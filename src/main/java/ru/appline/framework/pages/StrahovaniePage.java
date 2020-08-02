package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку страхование путешественников
 */
public class StrahovaniePage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'kit-col_xs_12')]/h1")
    WebElement pageTitle;

    @FindBy(xpath = "//b[text()='Оформить онлайн']")
    WebElement checkoutOnlineButton;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return StrahovaniePage - т.е. остаемся на этой странице
     */
    @Step("Проверка открытия страницы StrahovaniePage")
    public StrahovaniePage checkOpenStrahovaniePage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Страхование путешественников", pageTitle.getText());
        return this;
    }


    /**
     * Кликаем на кнопку "Оформить онлайн"
     *
     * @return TarifPage - т.е. переходим на страницу {@link ru.appline.framework.pages.TarifPage}
     */
    @Step("Кликаем по кнопке 'Оформить онлайн'")
    public TarifPage clickBtnOformitOnline() {
        scrollToElementJs(checkoutOnlineButton);
        elementToBeClickable(checkoutOnlineButton).click();
        return app.getTarifPage();
    }


}
