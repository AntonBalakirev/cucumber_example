package ru.appline.framework.managers;

import ru.appline.framework.pages.RegistrationFormPage;
import ru.appline.framework.pages.StartPage;
import ru.appline.framework.pages.StrahovaniePage;
import ru.appline.framework.pages.TariffPage;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для управления страничками
 */
public class ManagerPages {

    /**
     * Менеджер страничек
     */
    private static ManagerPages managerPages;

    /**
     * Стартовая страничка
     */
    StartPage startPage;

    /**
     * Страничка страхование путественников
     */
    StrahovaniePage strahovaniePage;

    /**
     * Страничка выбора полиса или тарифа
     */
    TariffPage tariffPage;

    /**
     * Страничка оформления полиса страхования
     */
    RegistrationFormPage registrationFormPage;

    /**
     * Конструктор специально запривейтили (синглтон)
     * @see ManagerPages#getManagerPages()
     */
    private ManagerPages() {
    }

    /**
     * Ленивая инициализация ManagerPages
     *
     * @return ManagerPages
     */
    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }

    /**
     * Сброс менеджера страничек
     */
    public static void disableManagerPages() {
        managerPages = null;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.StartPage}
     *
     * @return StartPage
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.StrahovaniePage}
     *
     * @return StrahovaniePage
     */
    public StrahovaniePage getStrahovaniePage() {
        if (strahovaniePage == null) {
            strahovaniePage = new StrahovaniePage();
        }
        return strahovaniePage;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.TariffPage}
     *
     * @return TarifPage
     */
    public TariffPage getTariffPage() {
        if (tariffPage == null) {
            tariffPage = new TariffPage();
        }
        return tariffPage;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.RegistrationFormPage}
     *
     * @return RegistrationFormPage
     */
    public RegistrationFormPage getRegistrationFormPage() {
        if (registrationFormPage == null) {
            registrationFormPage = new RegistrationFormPage();
        }
        return registrationFormPage;
    }
}
