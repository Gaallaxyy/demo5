package com.example.demo5;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("https://www.jetbrains.com/");
    }

    @Test
    public void search() {
        imClickingSearch();
        $("[data-test='search-input']").sendKeys("Selenium");
        $("button[data-test='full-search-button']").click();
        $("input[data-test='search-input']").shouldHave(attribute("value", "Selenium"));

    }
    public void imClickingSearch() {
        String text = "Молодец";
        mainPage.searchButton.click();
        System.out.println("Нажал на кнопку - "  +text);

    }
    public String getTextFromField() {
        String text;
        text = $("input[data-test='search-input']").attr("value");
        System.out.println("Значение value: " + text);
        return text;
    }


    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        $("div[data-test='main-submenu']").shouldBe(visible);
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        $("#products-page").shouldBe(visible);

        assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
    }
}
