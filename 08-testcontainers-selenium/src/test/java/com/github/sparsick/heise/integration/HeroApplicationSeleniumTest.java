package com.github.sparsick.heise.integration;


import com.github.sparsick.heise.integration.testcontainers.HeroApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@SpringBootTest(classes = HeroApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HeroApplicationSeleniumTest {

    @Container
    private final BrowserWebDriverContainer<?> seleniumContainer = new BrowserWebDriverContainer<>().withAccessToHost(true);

    @LocalServerPort
    private int appPort;

    private RemoteWebDriver browser;

    @BeforeEach
    void setup(){
        org.testcontainers.Testcontainers.exposeHostPorts(appPort);
//        seleniumContainer.withRecordingMode(VncRecordingMode.RECORD_ALL, new File("target"));
        browser = new RemoteWebDriver(seleniumContainer.getSeleniumAddress(), new ChromeOptions());
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterEach
    void cleanUp() {
        browser.quit();
    }

    @Test
    void searchHero(){
        browser.get("http://host.testcontainers.internal:" + appPort + "/hero");

        WebElement searchField = browser.findElement(By.id("search"));
        searchField.sendKeys("Batman");

        WebElement searchButton = browser.findElement(By.id("button-search"));
        searchButton.click();

        WebElement title = browser.findElement(By.tagName("h1"));
        assertThat(title.getText().trim()).isEqualTo("Hero List");
    }
}
