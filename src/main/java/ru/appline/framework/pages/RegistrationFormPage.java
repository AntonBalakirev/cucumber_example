package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку оформления полиса страхования
 */
public class RegistrationFormPage extends BasePage {

    @FindBy(id = "surname_vzr_ins_0")
    WebElement insSurName;

    @FindBy(id = "name_vzr_ins_0")
    WebElement insName;

    @FindBy(id = "birthDate_vzr_ins_0")
    WebElement insDateOfBirth;

    @FindBy(id = "person_lastName")
    WebElement personLastName;

    @FindBy(id = "person_firstName")
    WebElement personFirstName;

    @FindBy(id = "person_middleName")
    WebElement personMiddleName;

    @FindBy(id = "person_birthDate")
    WebElement personBirthDate;

    @FindBy(id = "passportSeries")
    WebElement passportSeries;

    @FindBy(id = "passportNumber")
    WebElement passportNumber;

    @FindBy(id = "documentDate")
    WebElement passportDocumentDate;

    @FindBy(id = "documentIssue")
    WebElement passportDocumentIssue;

    @FindBy(id = "phone")
    WebElement phone;

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "repeatEmail")
    WebElement repeatEmail;

    @FindBy(xpath = "//button[contains(.,'Продолжить')]")
    WebElement btnContinue;

    @FindBy(xpath = "//div[@class='alert-form alert-form-error']")
    WebElement errorMessageAlert;

    /**
     * Метод заполнения полей
     *
     * @param nameField - имя веб элемента, поля ввода
     * @param value     - значение вводимое в поле
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    @Step("Заполняем поле '{nameField}' значение '{value}'")
    public RegistrationFormPage fillField(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Застрахованные - Фамилия":
                fillInputField(insSurName, value);
                element = insSurName;
                break;
            case "Застрахованные - Имя":
                fillInputField(insName, value);
                element = insName;
                break;
            case "Застрахованные - Дата рождения":
                fillDateField(insDateOfBirth, value);
                element = insDateOfBirth;
                break;
            case "Страхователь - Фамилия":
                fillInputField(personLastName, value);
                element = personLastName;
                break;
            case "Страхователь - Имя":
                fillInputField(personFirstName, value);
                element = personFirstName;
                break;
            case "Страхователь - Отчество":
                fillInputField(personMiddleName, value);
                element = personMiddleName;
                break;
            case "Страхователь - Дата рождения":
                fillDateField(personBirthDate, value);
                element = personBirthDate;
                break;
            case "Страхователь - Серия паспорта":
                fillInputField(passportSeries, value);
                element = passportSeries;
                break;
            case "Страхователь - Номер паспорта":
                fillInputField(passportNumber, value);
                element = passportNumber;
                break;
            case "Страхователь - Дата выдачи":
                fillDateField(passportDocumentDate, value);
                element = passportDocumentDate;
                break;
            case "Страхователь - Кем выдан":
                fillInputField(passportDocumentIssue, value);
                element = passportDocumentIssue;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Оформления страхования путешественников'");

        }
        Assert.assertEquals("Поле '" + nameField + "' было заполнено некорректно",
                value, element.getAttribute("value"));
        return this;
    }

    /**
     * Проверка ошибки относящаяся к конкретному полю на форме
     *
     * @param nameField  - имя веб элемента
     * @param errMassage - ошибка проверяемая которая отображается возле этого поля
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    @Step("Проверка что у поле '{nameField}' присутствует ошибка с текстом '{errMassage}'")
    public RegistrationFormPage checkErrorMessageAtField(String nameField, String errMassage) {
        WebElement element = null;
        switch (nameField) {
            case "Контакты - Мобильный телефон":
                element = phone;
                break;
            case "Контакты - Электронная почта":
                element = email;
                break;
            case "Контакты - Повтор электронной почты":
                element = repeatEmail;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Оформления страхования путешественников'");

        }
        element = element.findElement(By.xpath("./..//span"));
        Assert.assertEquals("Проверка ошибки у поля '" + nameField + "' была непройдена",
                errMassage, element.getText());
        return this;
    }

    /**
     * Клик по кнопке "Продолжить"
     *
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    @Step("Кликаем по кнопке 'Продолжить'")
    public RegistrationFormPage clickBtnContinue() {
        elementToBeClickable(btnContinue).click();
        return this;
    }

    /**
     * Проверка общей ошибки у Алерта
     *
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    @Step("Проверяем что на странице появилась ошибка с текстом '{errMessage}'")
    public RegistrationFormPage checkErrorMessageAlert(String errMessage) {
        Assert.assertEquals("Проверка ошибки у alert на странице " +
                        "'Оформления страхования путешественников' было не пройдено",
                errMessage, errorMessageAlert.getText());
        return this;
    }
}
