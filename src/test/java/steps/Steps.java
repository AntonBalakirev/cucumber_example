package steps;

import io.cucumber.datatable.DataTable;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.appline.framework.managers.ManagerPages;

public class Steps {
    /**
     * Менеджер страничек
     * @see ManagerPages#getManagerPages()
     */
    private ManagerPages app = ManagerPages.getManagerPages();

    @Когда("^Загружена стартовая страница$")
    public void getInitialPage(){
        app.getStartPage();
    }

    @Когда("^Переход в главное меню '(.*)'$")
    public void selectNameBaseMenu(String nameBaseMenu){
        app.getStartPage().selectBaseMenu(nameBaseMenu);
    }

    @Когда("^Выбираем подменю '(.*)'$")
    public void selectNameSubMenu(String nameSubMenu){
        app.getStartPage().selectSubMenu(nameSubMenu);
    }

    @Тогда("^Проверка открытия страницы '(.*)'$")
    public void checkPageTitle(String title) {
        app.getStrahovaniePage().checkOpenPage(title);
    }

    @Тогда("^Проверка перехода на страницу '(.*)'$")
    public void checkPage(String title) {
        app.getTariffPage().checkOpenPage(title);
    }

    @Когда("^Выбрать страховую программу '(.*)'$")
    public void selectInsuranceProgram(String insuranceProgram){
        app.getStrahovaniePage().selectInsuranceProgram(insuranceProgram);
    }

    @Когда("^Кликаем по кнопке 'Оформить онлайн'$")
    public void clickCheckoutButtonOnline() {
        app.getStrahovaniePage().clickCheckoutButton();
    }

    @Когда("^Выбираем тариф страхования '(.*)'$")
    public void selectTarifMin(String coverageSum){
        app.getTariffPage().selectCoverage(coverageSum);
    }

    @Когда("^Кликаем по кнопке 'Оформить'$")
    public void clickCheckoutButton(){
        app.getTariffPage().clickCheckoutButton();
    }

    @Когда("^Заполняем форму поле/значение$")
    public void fillFields(DataTable dataTable){
        dataTable.cells().forEach(
                raw -> {
                    app.getRegistrationFormPage().fillField(raw.get(0), raw.get(1));
                }
        );
    }

    @Когда("^Кликаем по кнопке 'Продолжить'$")
    public void clickBtnContinue(){
        app.getRegistrationFormPage().clickContinueButton();
    }

    @Тогда("^Проверка что у поля присутствует ошибка с текстом$")
    public void checkErrorMessageAtField(DataTable dataTable){
        dataTable.cells().forEach(
                raw -> {
                    app.getRegistrationFormPage().checkErrorMessageAtField(raw.get(0), raw.get(1));
                }
        );
    }

    @Тогда("^Проверяем что на странице появилась ошибка с текстом '(.*)'$")
    public void checkErrorMessageAlert(String alertMessage){
        app.getRegistrationFormPage().checkErrorMessageAlert(alertMessage);
    }
}
