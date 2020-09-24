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

    @Тогда("^Проверка открытия страницы Страхование$")
    public void checkOpenStrahovaniePage(){
        app.getStrahovaniePage().checkOpenStrahovaniePage();
    }

    @Когда("^Кликаем по кнопке 'Оформить онлайн'$")
    public void clickBtnOformitOnline(){
        app.getStrahovaniePage().clickBtnOformitOnline();
    }

    @Когда("^Выбираем тариф страхования 'Минимальный'$")
    public void selectTarifMin(){
        app.getTarifPage().selectTarifMin();
    }

    @Когда("^Кликаем по кнопке 'Оформить'$")
    public void clickBtnOformit(){
        app.getTarifPage().clickBtnOformit();
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
        app.getRegistrationFormPage().clickBtnContinue();
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
