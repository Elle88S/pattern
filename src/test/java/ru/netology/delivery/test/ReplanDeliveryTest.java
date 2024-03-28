package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        //var validUser = DataGenerator.Registration.generateUser("ru", user);
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=city] input").setValue(DataGenerator.generateCity("ru"));
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(firstMeetingDate);
        form.$("[data-test-id=name] input").setValue(DataGenerator.generateName("ru"));
        form.$("[name='phone']").setValue(DataGenerator.generatePhone("ru"));
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Встреча успешно запланирована на " + firstMeetingDate));
        form.$("button.button").click();
        $("[data-test-id=replan-notification]")
                .shouldBe(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(secondMeetingDate);
        form.$("[data-test-id=replan-notification] button").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Встреча успешно запланирована на " + secondMeetingDate));

        // TODO: добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.
        // Для заполнения полей формы можно использовать пользователя validUser и строки с датами в переменных
        // firstMeetingDate и secondMeetingDate. Можно также вызывать методы generateCity(locale),
        // generateName(locale), generatePhone(locale) для генерации и получения в тесте соответственно города,
        // имени и номера телефона без создания пользователя в методе generateUser(String locale) в датагенераторе


    }
}