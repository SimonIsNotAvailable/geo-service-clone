package ru.netology.geo;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void byMoscowIp() {
        String ip = "172.0.32.11";
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location actualLocation = geoService.byIp(ip);
        String actualCity = actualLocation.getCity();
        Country actualCountry = actualLocation.getCountry();
        String actualStreet = actualLocation.getStreet();

        Country expectedCountry = Country.RUSSIA;
        String expectedCity = "Moscow";
        String expectedStreet = "Lenina";
        assertEquals(expectedCity, actualCity);
        assertEquals(expectedCountry, actualCountry);
        assertEquals(expectedStreet, actualStreet);
    }

    @Test
    void byNYIp() {
        String ip = "96.44.183.149";
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location actualLocation = geoService.byIp(ip);
        String actualCity = actualLocation.getCity();
        Country actualCountry = actualLocation.getCountry();
        String actualStreet = actualLocation.getStreet();

        Country expectedCountry = Country.USA;
        String expectedCity = "New York";
        String expectedStreet = "10th Avenue";
        assertEquals(expectedCity, actualCity);
        assertEquals(expectedCountry, actualCountry);
        assertEquals(expectedStreet, actualStreet);
    }

    @Test
    void byLocalHostIp() {
        String ip = "127.0.0.1";
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location actualLocation = geoService.byIp(ip);
        String actualCity = actualLocation.getCity();
        Country actualCountry = actualLocation.getCountry();

        assertNull(actualCity);
        assertNull(actualCountry);
    }

    @ParameterizedTest
    @ValueSource(strings = {"172.178.8.0", "172.112.232.123", "172.0.0.1", "172.321.231.122"})
    void startsWithMoscow(String ip) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp(ip);
        String actualCity = actualLocation.getCity();
        Country actualCountry = actualLocation.getCountry();
        String actualStreet = actualLocation.getStreet();

        Country expectedCountry = Country.RUSSIA;
        String expectedCity = "Moscow";

        assertEquals(expectedCity, actualCity);
        assertEquals(expectedCountry, actualCountry);
        assertNull(actualStreet);
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.178.8.0", "96.112.232.123", "96.0.0.1", "96.321.231.122"})
    void startsWithNY(String ip) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp(ip);
        String actualCity = actualLocation.getCity();
        Country actualCountry = actualLocation.getCountry();
        String actualStreet = actualLocation.getStreet();

        Country expectedCountry = Country.USA;
        String expectedCity = "New York";

        assertEquals(expectedCity, actualCity);
        assertEquals(expectedCountry, actualCountry);
        assertNull(actualStreet);
    }

    @ParameterizedTest
    @ValueSource(strings = {"11.178.8.0", "12.112.232.123", "55.0.0.1", "0.321.231.122"})
    void otherIp(String ip) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp(ip);

        assertNull(actualLocation);
    }

}