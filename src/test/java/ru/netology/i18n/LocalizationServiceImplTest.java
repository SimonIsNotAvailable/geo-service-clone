package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

class LocalizationServiceImplTest {
    LocalizationServiceImpl sut = new LocalizationServiceImpl();

    @Test
    void localeTest() {
        String actual = sut.locale(Country.RUSSIA);
        String expected = "Добро пожаловать";

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @EnumSource(Country.class)
    void localeTestNotNull(Country country) {
        String actual = sut.locale(country);

        assertNotNull(actual);
    }

    @ParameterizedTest
    @EnumSource(mode = EXCLUDE, names = {"RUSSIA"})
    void localeTestNotRussia(Country country) {
        String actual = sut.locale(country);
        String expected = "Welcome";

        assertEquals(expected, actual);
    }
}