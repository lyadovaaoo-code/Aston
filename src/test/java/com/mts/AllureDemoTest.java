package com.mts;

import io.qameta.allure.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@Epic("MTS Online Payments")
@Feature("Демонстрация Allure отчета")
@Owner("Студент")
public class AllureDemoTest {

    @Test
    @Story("Успешный тест с шагами")
    @Description("Этот тест демонстрирует возможности Allure репортинга")
    @Severity(SeverityLevel.CRITICAL)
    public void successfulTestWithSteps() {
        Allure.step("Открытие главной страницы");
        Allure.addAttachment("URL", "text/plain", "https://www.mts.by");

        Allure.step("Проверка заголовка страницы");
        Allure.addAttachment("Заголовок", "text/plain", "МТС - телекоммуникационная компания");

        Allure.step("Проверка наличия элементов");
        assertTrue("Проверка прошла успешно", true);

        Allure.step("Завершение теста");
        Allure.addAttachment("Результат", "text/plain", "Тест выполнен успешно!");
    }

    @Test
    @Story("Тест с проверкой данных")
    @Description("Тест демонстрирует прикрепление данных и скриншотов")
    @Severity(SeverityLevel.NORMAL)
    public void testWithDataAttachments() {
        Allure.step("Подготовка тестовых данных");
        String testData = "Телефон: 297777777\nСумма: 10 руб";
        Allure.addAttachment("Тестовые данные", "text/plain", testData);

        Allure.step("Выполнение проверок");
        assertTrue("Проверка формата номера", testData.contains("297777777"));
        assertTrue("Проверка суммы", testData.contains("10 руб"));

        Allure.step("Финализация теста");
    }
}