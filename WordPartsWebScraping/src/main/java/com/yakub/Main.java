package com.yakub;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.yakub.pages.Gorokh;

public class Main {
    public static void main(String[] args) {
        String baseUrl = "https://goroh.pp.ua/Словозміна/";
        String adjUrl = baseUrl + "[прикметник]";
        String nounUrl = baseUrl + "[іменник]";
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        parseData(driver,adjUrl,"adj.txt");
        parseData(driver,nounUrl,"noun.txt");
    }

    private static void parseData(WebDriver driver, String url, String path) {
        Gorokh gorokh = new Gorokh(driver);
        gorokh.setUrl(url);
        gorokh.open();
        do {
            List<String> res = gorokh.getWordsOnPage();
            try (FileWriter writer = new FileWriter(path, true)) {
                for (String s : res) {
                    writer.write(s + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (gorokh.nextPage());
    }
}