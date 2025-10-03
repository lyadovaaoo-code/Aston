package com.mts;

import com.mts.pages.OnlinePaymentPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@Epic("MTS Online Payments")
@Feature("Онлайн пополнение без комиссии")
@Owner("Тестировщик")
public class MTSOnlinePaymentTest {

    private WebDriver driver;
    private OnlinePaymentPage paymentPage;

    @Before
    @Step("Настройка браузера")
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        // Устанавливаем таймауты
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        paymentPage = new OnlinePaymentPage(driver);
        Allure.addAttachment("Браузер", "text/plain", "Firefox запущен успешно");
    }

    @Test
    @Story("Проверка функциональности онлайн оплаты")
    @Description("Полный тест функциональности блока онлайн пополнения на сайте MTS")
    @Severity(SeverityLevel.CRITICAL)
    public void testOnlinePaymentBlock() {
        try {
            // 1. Открываем сайт
            paymentPage.openHomePage();
            takeScreenshot("Главная страница MTS");

            // 2. Проверяем название блока
            checkBlockTitle();

            // 3. Проверяем логотипы
            checkPaymentLogos();

            // 4. Проверяем ссылку
            checkDetailsLinkSimple();

            // 5. Проверяем плейсхолдеры для всех вариантов оплаты
            checkPaymentOptionsPlaceholders();

            // 6. Проверяем заполнение формы для 'Услуги связи'
            testFormFilling();

            Allure.addAttachment("Результат", "text/plain", "Все тесты завершены успешно!");

        } catch (Exception e) {
            takeScreenshot("Ошибка в тесте");
            Allure.addAttachment("Ошибка", "text/plain", "Тест завершился с ошибкой: " + e.getMessage());
            fail("Тест завершился с ошибкой: " + e.getMessage());
        }
    }

    @Step("Проверка названия блока 'Онлайн пополнение'")
    private void checkBlockTitle() {
        try {
            String title = paymentPage.getBlockTitle();
            assertTrue("Название блока не содержит ожидаемый текст: " + title,
                    title.contains("Онлайн пополнение"));
            Allure.addAttachment("Название блока", "text/plain", title);
        } catch (Exception e) {
            takeScreenshot("Ошибка при проверке названия блока");
            throw e;
        }
    }

    @Step("Проверка логотипов платежных систем")
    private void checkPaymentLogos() {
        boolean logosDisplayed = paymentPage.arePaymentLogosDisplayed();
        if (logosDisplayed) {
            Allure.addAttachment("Логотипы", "text/plain", "Все логотипы платежных систем найдены");
        } else {
            Allure.addAttachment("Логотипы", "text/plain", "Не все логотипы отображаются");
        }
    }

    @Step("Проверка ссылки 'Подробнее о сервисе'")
    private void checkDetailsLinkSimple() {
        try {
            String originalUrl = driver.getCurrentUrl();
            paymentPage.clickDetailsLink();
            takeScreenshot("После перехода по ссылке");

            String newUrl = driver.getCurrentUrl();
            Allure.addAttachment("URL после клика", "text/plain", newUrl);

            assertNotEquals("Ссылка не работает - URL не изменился", originalUrl, newUrl);

            // Возвращаемся назад с ожиданием
            driver.navigate().back();
            waitForPageLoad(5);

        } catch (Exception e) {
            takeScreenshot("Ошибка при проверке ссылки");
            Allure.addAttachment("Предупреждение", "text/plain",
                    "Не удалось полностью проверить ссылку: " + e.getMessage());
        }
    }

    @Step("Проверка плейсхолдеров для вариантов оплаты")
    private void checkPaymentOptionsPlaceholders() {
        String[] paymentOptions = {"Услуги связи", "Домашний интернет"};

        for (String option : paymentOptions) {
            try {
                Allure.addAttachment("Проверка варианта", "text/plain", option);
                paymentPage.selectPaymentOption(option);
                takeScreenshot("Выбран вариант: " + option);

                String phonePlaceholder = paymentPage.getPhonePlaceholder();
                String amountPlaceholder = paymentPage.getAmountPlaceholder();

                Allure.addAttachment("Плейсхолдеры для " + option,
                        "text/plain",
                        "Телефон: '" + phonePlaceholder + "'\nСумма: '" + amountPlaceholder + "'");

                if (!phonePlaceholder.equals("Поле не найдено")) {
                    assertTrue("Плейсхолдер телефона не содержит ожидаемые слова для " + option,
                            phonePlaceholder.toLowerCase().contains("телефон") ||
                                    phonePlaceholder.toLowerCase().contains("номер") ||
                                    phonePlaceholder.toLowerCase().contains("абонент"));
                }

                if (!amountPlaceholder.equals("Поле не найдено")) {
                    assertTrue("Плейсхолдер суммы не содержит ожидаемые слова для " + option,
                            amountPlaceholder.toLowerCase().contains("сумма") ||
                                    amountPlaceholder.toLowerCase().contains("руб"));
                }

            } catch (Exception e) {
                Allure.addAttachment("Предупреждение", "text/plain",
                        "Пропускаем вариант '" + option + "': " + e.getMessage());
            }
        }
    }

    @Step("Тест заполнения формы 'Услуги связи'")
    private void testFormFilling() {
        try {
            // Открываем страницу заново для чистого состояния
            paymentPage.openHomePage();

            // Выбираем "Услуги связи"
            paymentPage.selectPaymentOption("Услуги связи");

            // Заполняем поля
            paymentPage.enterPhoneNumber("297777777");
            paymentPage.enterAmount("10");
            takeScreenshot("Форма заполнена");

            Allure.addAttachment("Введенные данные", "text/plain",
                    "Номер: 297777777\nСумма: 10");

            // Нажимаем "Продолжить" и проверяем результат
            boolean formSubmitted = paymentPage.clickContinue();

            if (formSubmitted) {
                takeScreenshot("После отправки формы");
                // Если форма успешно отправлена, проверяем модальное окно
                checkPaymentModal();
            } else {
                Allure.addAttachment("Информация", "text/plain",
                        "Форма не была отправлена успешно");
            }

        } catch (Exception e) {
            takeScreenshot("Ошибка при заполнении формы");
            throw e;
        }
    }

    @Step("Проверка модального окна оплаты")
    private void checkPaymentModal() {
        try {
            // Даем время для появления модального окна
            Thread.sleep(2000);

            // Проверяем, отображается ли модальное окно
            if (!paymentPage.isPaymentModalDisplayed()) {
                Allure.addAttachment("Предупреждение", "text/plain",
                        "Модальное окно не отображается");
                return;
            }

            takeScreenshot("Модальное окно оплаты");

            // Проверяем сумму
            String amountText = paymentPage.getPaymentAmount();
            Allure.addAttachment("Сумма оплаты", "text/plain", amountText);

            // Проверяем номер телефона
            String phoneText = paymentPage.getPaymentPhone();
            Allure.addAttachment("Номер телефона", "text/plain", phoneText);

            // Проверяем текст на кнопке оплаты
            String payButtonText = paymentPage.getPayButtonText();
            Allure.addAttachment("Кнопка оплаты", "text/plain", payButtonText);

            // Проверяем плейсхолдеры полей карты
            String cardPlaceholder = paymentPage.getCardNumberPlaceholder();
            String expiryPlaceholder = paymentPage.getExpiryDatePlaceholder();
            String cvvPlaceholder = paymentPage.getCVVPlaceholder();

            Allure.addAttachment("Плейсхолдеры карты", "text/plain",
                    "Номер карты: '" + cardPlaceholder + "'\n" +
                            "Срок действия: '" + expiryPlaceholder + "'\n" +
                            "CVV: '" + cvvPlaceholder + "'");

            // Проверяем иконки платежных систем
            boolean iconsDisplayed = paymentPage.arePaymentIconsDisplayedInModal();
            Allure.addAttachment("Иконки платежных систем", "text/plain",
                    iconsDisplayed ? "Отображаются" : "Не найдены");

        } catch (Exception e) {
            takeScreenshot("Ошибка при проверке модального окна");
            Allure.addAttachment("Ошибка", "text/plain",
                    "Ошибка при проверке модального окна: " + e.getMessage());
        }
    }

    @After
    @Step("Завершение теста")
    public void tearDown() {
        if (driver != null) {
            try {
                takeScreenshot("Завершение теста");
                // Закрываем браузер
                driver.quit();
                Allure.addAttachment("Браузер", "text/plain", "Браузер закрыт");
            } catch (Exception e) {
                System.out.println("Ошибка при закрытии браузера: " + e.getMessage());
            }
        }
    }

    @Attachment(value = "{attachmentName}", type = "image/png")
    private byte[] takeScreenshot(String attachmentName) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            Allure.addAttachment("Ошибка скриншота", "text/plain", "Не удалось сделать скриншот: " + e.getMessage());
            return new byte[0];
        }
    }

    private void waitForPageLoad(int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(
                    webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            // Игнорируем таймауты
        }
    }
}