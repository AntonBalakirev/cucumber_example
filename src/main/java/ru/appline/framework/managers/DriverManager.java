package ru.appline.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static ru.appline.framework.utils.PropConst.*;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для управления веб драйвером
 */
public class DriverManager {

    /**
     * Переменна для хранения объекта веб дравера
     *
     * @see WebDriver
     */
    private static WebDriver driver;

    /**
     * Менеджер пропертей
     *
     * @see TestPropManager#getTestPropManager()
     */
    private static TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * Конструктор специально запривейтили (синглтон)
     *
     * @see DriverManager#getDriver()
     */
    private DriverManager() {
    }

    /**
     * Метод инициализирующий веб драйвер
     */
    private static void initDriver() {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", props.getProperty(PATH_GEKO_DRIVER));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty(PATH_CHROME_DRIVER));
                driver = new ChromeDriver();
                break;
            case "remote":
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("73.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://selenoid.appline.ru:4445/wd/hub/").toURL(),
                            capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Метод ленивой инициализации веб драйвера
     *
     * @return WebDriver - возвращает веб драйвер
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    /**
     * Метод для закрытия сессии драйвера и браузера
     *
     * @see WebDriver#quit()
     */
    public static void quitDriver() {
        driver.quit();
        driver = null;
    }
}
