package com.mts;

import com.mts.pages.HomePage;
import com.mts.pages.PaymentModal;
import com.mts.utils.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class MTSOnlinePaymentTest {

    private WebDriver driver;
    private HomePage homePage;
    private PaymentModal paymentModal;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        homePage = new HomePage(driver);
        paymentModal = new PaymentModal(driver);
        homePage.open();
    }

    @Test
    public void testEmptyFieldLabelsForAllPaymentOptions() {
        // Проверяем надписи для "Услуги связи"
        homePage.selectPaymentOption("Услуги связи");
        assertEquals("Номер телефона", homePage.getPhoneFieldPlaceholder(),
                "Неверная надпись поля телефона для Услуг связи");

        // Проверяем надписи для "Домашний интернет"
        homePage.selectPaymentOption("Домашний интернет");
        assertEquals("Номер абонента", homePage.getContractFieldPlaceholder("Домашний интернет"),
                "Неверная надпись поля для Домашнего интернета");

        // Проверяем надписи для "Рассрочка"
        homePage.selectPaymentOption("Рассрочка");
        assertEquals("Номер счета на 44", homePage.getContractFieldPlaceholder("Рассрочка"),
                "Неверная надпись поля для Рассрочки");

        // Проверяем надписи для "Задолженность"
        homePage.selectPaymentOption("Задолженность");
        assertEquals("Номер счета на 2073", homePage.getContractFieldPlaceholder("Задолженность"),
                "Неверная надпись поля для Задолженности");

        System.out.println("✓ Все проверки плейсхолдеров пройдены успешно");
    }

    @Test
    public void testCommunicationServicesPaymentFlow() {
        // Выбираем "Услуги связи"
        homePage.selectPaymentOption("Услуги связи");

        // Заполняем поле телефона
        homePage.enterPhoneNumber("297777777");

        // Заполняем поле суммы
        homePage.enterAmount("5");

        // Нажимаем "Продолжить"
        homePage.clickContinue();

        // Ждем появления модального окна
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Переключаемся на iframe оплаты
        paymentModal.switchToPaymentIframe();

        // Проверяем окно оплаты
        assertTrue(paymentModal.isPaymentModalDisplayed(),
                "Окно оплаты не отображается");

        // Проверяем корректность отображения номера телефона
        String displayedPhone = paymentModal.getDisplayedPhoneNumber();
        assertTrue(displayedPhone.contains("297777777") || displayedPhone.contains("375297777777"),
                "Номер телефона отображается некорректно: " + displayedPhone);

        // Проверяем корректность отображения суммы
        String displayedAmount = paymentModal.getDisplayedAmount();
        assertNotNull(displayedAmount, "Сумма не отображается");
        assertTrue(displayedAmount.contains("5") && displayedAmount.contains("BYN"),
                "Сумма отображается некорректно: " + displayedAmount);

        // Проверяем сумму на кнопке
        String amountOnButton = paymentModal.getAmountOnPayButton();
        assertNotNull(amountOnButton, "Сумма на кнопке не отображается");
        assertTrue(amountOnButton.contains("5") && amountOnButton.contains("BYN"),
                "Сумма на кнопке отображается некорректно: " + amountOnButton);

        // Проверяем надписи в незаполненных полях для ввода реквизитов карты
        assertEquals("Номер карты", paymentModal.getCardNumberPlaceholder(),
                "Неверная надпись поля номера карты");

        assertEquals("Срок действия", paymentModal.getExpiryDatePlaceholder(),
                "Неверная надпись поля срока действия");

        assertEquals("CVC", paymentModal.getCvvPlaceholder(),
                "Неверная надпись поля CVV/CVC");

        assertEquals("Имя и фамилия на карте", paymentModal.getCardHolderPlaceholder(),
                "Неверная надпись поля имени держателя карты");

        // Проверяем наличие иконок платежных систем
        assertTrue(paymentModal.arePaymentIconsDisplayed(),
                "Не все иконки платежных систем отображаются");

        // Возвращаемся к основному контенту
        paymentModal.switchToDefaultContent();

        System.out.println("✓ Все проверки потока оплаты пройдены успешно");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}