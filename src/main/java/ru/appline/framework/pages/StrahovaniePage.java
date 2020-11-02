package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.Set;

import static ru.appline.framework.managers.DriverManager.getDriver;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку страхование путешественников
 */
public class StrahovaniePage extends BasePage {

    @FindBy(xpath = "//h1[@class = 's-hero-banner__title']")
    WebElement pageTitle;

    @FindBy(xpath = "//a[text() = 'Оформить онлайн' and contains(@class, 's-hero-banner')]")
    WebElement checkoutOnlineButton;

    final String insuranceProgram = "//h2[contains(text(), '%s')]/following-sibling::div//span";

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return StrahovaniePage - т.е. остаемся на этой странице
     */
    public StrahovaniePage checkOpenStrahovaniePage(String title) {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                title, pageTitle.getText());
        return this;
    }


    /**
     * Кликаем на кнопку "Оформить онлайн" у выбранной страховой программы
     *
     * @return StrahovaniePage - т.е. остаемся на этой странице
     */
    public StrahovaniePage selectInsuranceProgram(String insuranceProgram) {
        String parentWindow = getDriver().getWindowHandle();

        WebElement program = getDriver().findElement(By.xpath(String.format(this.insuranceProgram, insuranceProgram)));
        scrollToElementJs(program);
        elementToBeClickable(program).click();

        Set<String> handeles = getDriver().getWindowHandles();
        Iterator<String> iteator = handeles.iterator();
        while(iteator.hasNext()){
            String childWindow = iteator.next();
            if (!parentWindow.equals(childWindow)){
                getDriver().switchTo().window(childWindow);
                break;
            }
        }
        return this;
    }

    /**
     * Кликаем на кнопку "Оформить онлайн"
     *
     * @return TarifPage - т.е. переходим на страницу {@link ru.appline.framework.pages.TarifPage}
     */
    public TarifPage clickBtnOformitOnline() {
        scrollToElementJs(checkoutOnlineButton);
        elementToBeClickable(checkoutOnlineButton).click();
        return app.getTarifPage();
    }

}
