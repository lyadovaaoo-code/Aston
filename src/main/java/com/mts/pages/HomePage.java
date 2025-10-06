package com.mts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "cookie-agree")
    private WebElement cookieAgreeButton;

    @FindBy(className = "select__header")
    private WebElement paymentSelect;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://www.mts.by");
        acceptCookies();
        waitForPageLoad();
    }

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieAgreeButton)).click();
            System.out.println("Куки приняты");
        } catch (Exception e) {
            System.out.println("Куки не найдены или уже приняты");
        }
    }

    private void waitForPageLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[contains(text(), 'Онлайн пополнение')]")));
        } catch (Exception e) {
            System.out.println("Страница не загрузилась полностью");
        }
    }

    public void selectPaymentOption(String option) {
        // Кликаем на select чтобы открыть dropdown
        paymentSelect.click();

        // Ждем появления опций
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Выбираем опцию по тексту
        String optionXpath = String.format("//option[text()='%s']", option);
        WebElement optionElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
        optionElement.click();

        // Даем время на переключение формы
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getPhoneFieldPlaceholder() {
        try {
            WebElement phoneInput = driver.findElement(By.id("connection-phone"));
            return phoneInput.getAttribute("placeholder");
        } catch (Exception e) {
            return "Поле не найдено";
        }
    }

    public String getContractFieldPlaceholder(String option) {
        try {
            String fieldId = "";
            switch (option) {
                case "Домашний интернет":
                    fieldId = "internet-phone";
                    break;
                case "Рассрочка":
                    fieldId = "score-instalment";
                    break;
                case "Задолженность":
                    fieldId = "score-arrears";
                    break;
                default:
                    return "Неизвестный вариант";
            }

            WebElement contractInput = driver.findElement(By.id(fieldId));
            return contractInput.getAttribute("placeholder");
        } catch (Exception e) {
            return "Поле не найдено для " + option;
        }
    }

    public String getAmountFieldPlaceholder() {
        try {
            WebElement amountInput = driver.findElement(By.xpath("//input[contains(@class, 'total_rub')]"));
            return amountInput.getAttribute("placeholder");
        } catch (Exception e) {
            return "Поле не найдено";
        }
    }

    public void enterPhoneNumber(String phone) {
        WebElement phoneInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("connection-phone")));
        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public void enterAmount(String amount) {
        WebElement amountInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[contains(@class, 'total_rub')]")));
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public void clickContinue() {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Продолжить')]")));
        continueButton.click();
    }

    public boolean isPageLoaded() {
        try {
            return driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}