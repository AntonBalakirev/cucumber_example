package steps;

import io.cucumber.datatable.DataTable;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.appline.framework.managers.ManagerPages;

public class Steps {
    private static final Logger logger = LoggerFactory.getLogger(Steps.class);

    /**
     * Менеджер страничек
     *
     * @see ManagerPages#getManagerPages()
     */
    private ManagerPages app = ManagerPages.getManagerPages();

    @Когда("^Загружена стартовая страница$")
    public void getInitialPage() {
        app.getStartPage();
        logger.info("Загружена стартовая страница");
    }

    @Когда("^Переход в главное меню '(.*)'$")
    public void selectNameBaseMenu(String nameBaseMenu) {
        app.getStartPage().selectBaseMenu(nameBaseMenu);
        logger.info("Переход в главное меню: '{}'", nameBaseMenu);
    }

    @Когда("^Выбираем подменю '(.*)'$")
    public void selectNameSubMenu(String nameSubMenu) {
        app.getStartPage().selectSubMenu(nameSubMenu);
        logger.info("Выбираем подменю: '{}'", nameSubMenu);
    }

    @Тогда("^Проверка открытия страницы '(.*)'$")
    public void checkPageTitle(String title) {
        app.getStrahovaniePage().checkOpenPage(title);
        logger.info("Открыта страница: '{}'", title);
    }

    @Тогда("^Проверка перехода на страницу '(.*)'$")
    public void checkPage(String title) {
        app.getTariffPage().checkOpenPage(title);
        logger.info("Открыта страница: '{}'", title);
    }

    @Когда("^Выбрать страховую программу '(.*)'$")
    public void selectInsuranceProgram(String insuranceProgram) {
        app.getStrahovaniePage().selectInsuranceProgram(insuranceProgram);
        logger.info("Выбрана страховая программа: '{}'", insuranceProgram);
    }

    @Когда("^Кликаем по кнопке 'Оформить онлайн'$")
    public void clickCheckoutButtonOnline() {
        app.getStrahovaniePage().clickCheckoutButton();
        logger.info("Кликаем по кнопке: 'Оформить онлайн'");
    }

    @Когда("^Кликаем по кнопке 'Оформить на сайте'$")
    public void clickOrderButtonOnline() {
        app.getStrahovaniePage().clickOrderButton();
        logger.info("Кликаем по кнопке: 'Оформить на сайте'");
    }

    @Когда("^Выбираем сумму страховой защиты '(.*)'$")
    public void selectCoverage(String coverageSum) {
        app.getTariffPage().selectCoverage(coverageSum);
        logger.info("Выбираем сумму страховой защиты: '{}'", coverageSum);
    }

    @Когда("^Кликаем по кнопке 'Оформить'$")
    public void clickCheckoutButton() {
        app.getTariffPage().clickCheckoutButton();
        logger.info("Кликаем по кнопке: 'Оформить'");
    }

    @Когда("^Заполняем форму поле/значение$")
    public void fillFields(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getRegistrationFormPage().fillField(raw.get(0), raw.get(1));
                    logger.info("Заполняем форму: поле - '{}', значение - '{}'", raw.get(0), raw.get(1));
                }
        );
    }

    @Когда("^Кликаем по кнопке 'Продолжить'$")
    public void clickBtnContinue() {
        app.getRegistrationFormPage().clickContinueButton();
        logger.info("Кликаем по кнопке: 'Продолжить'");
    }

    @Тогда("^Проверка что у поля присутствует ошибка с текстом$")
    public void checkErrorMessageAtField(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getRegistrationFormPage().checkErrorMessageAtField(raw.get(0), raw.get(1));
                    logger.info("Проверка что у поля - '{}' присутствует ошибка с текстом - '{}'", raw.get(0), raw.get(1));
                }
        );
    }

    @Тогда("^Проверяем что на странице появилась ошибка с текстом '(.*)'$")
    public void checkErrorMessageAlert(String alertMessage) {
        app.getRegistrationFormPage().checkErrorMessageAlert(alertMessage);
        logger.info("Проверяем что на странице появилась ошибка с текстом: '{}'", alertMessage);
    }
}
