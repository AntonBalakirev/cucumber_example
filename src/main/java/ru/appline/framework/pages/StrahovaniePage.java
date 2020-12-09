package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static ru.appline.framework.managers.DriverManager.getDriver;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку страхование путешественников
 */
public class StrahovaniePage extends BasePage {

    @FindBy(xpath = "//h1[contains(@class, 's-hero-banner')]")
    WebElement pageTitle;

    final String pageTitleLocator = "//h1[contains(@class, 's-hero-banner')]";

    @FindBy(xpath = "//a[text() = 'Оформить онлайн' and contains(@class, 's-hero-banner')]")
    WebElement checkoutOnlineButton;

    final String insuranceProgram = "//div[normalize-space()='%s']/div";
    final String checkOutInsuranceProgram = "../following::div/a[text()='Оформить онлайн'][1]";

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return StrahovaniePage - т.е. остаемся на этой странице
     */
    public StrahovaniePage checkOpenPage(String title) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageTitleLocator)));
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                title, pageTitle.getText());
        return this;
    }


    public StrahovaniePage selectInsuranceProgram(String insuranceProgram) {
        WebElement program = getDriver().findElement(
                By.xpath(String.format(this.insuranceProgram, insuranceProgram.replaceAll(" ", ""))));
        scrollToElementJs(program);
        elementToBeClickable(program.findElement(By.xpath(checkOutInsuranceProgram))).click();
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
