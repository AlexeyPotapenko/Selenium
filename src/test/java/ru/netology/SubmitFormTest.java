package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

class CallbackTest {
    private WebDriver driver;



    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();


    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);



    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldTestV1() {
        driver.get("http://localhost:9999");
        List<WebElement> inputField = driver.findElements(By.className("input__control"));
        inputField.get(0).sendKeys("Алексей");
        inputField.get(1).sendKeys("+79174725035");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("Button")).click();

        String actualMessage = driver.findElement(By.className("paragraph")).getText();
        String expectedMessage = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage);

    }

}

