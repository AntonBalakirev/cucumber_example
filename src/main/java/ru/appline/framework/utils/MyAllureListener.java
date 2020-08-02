package ru.appline.framework.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.AllureJunit4;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.framework.managers.DriverManager;

public class MyAllureListener extends AllureJunit4 {


//  Здесь должен быть переопределённые методы или метод который позволит прикреплять скриншоты к шагу аллюра
//  Если не получается переопредилить то скопировать библиотеку AllureJunit4 себе в пакет listener
//  И прописать в конкретном месте в скопированной библиотеке строчку Allure.addAttachment();
//  Или там же прописать Allure.getLifecycle().addAttachment();



    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] addScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
