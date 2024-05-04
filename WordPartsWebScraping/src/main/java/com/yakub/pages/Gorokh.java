package com.yakub.pages;

import java.util.List;
import java.util.stream.Collectors;

import com.yakub.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Gorokh {

    private WebDriver driver;

    private String url;

    @FindBy(xpath = "//li[contains(@class,'content-list')]")
    private List<WebElement> words;

    @FindBy(xpath = "//a[contains(text(),'Наступна')]")
    private WebElement nextButton;

    public Gorokh(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void open() {
        driver.get(url);
    }

    public List<String> getWordsOnPage() {
        return words.stream().map(WebElement::getText).map(Utility::removeDiacritics).
                collect(Collectors.toList());
    }

    public boolean nextPage() {
        if (!nextButton.isDisplayed()) {
            return false;
        }
        nextButton.click();
        return true;
    }

}
