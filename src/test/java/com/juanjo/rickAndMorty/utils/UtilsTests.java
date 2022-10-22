package com.juanjo.rickAndMorty.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Locale;

@SpringBootTest
public class UtilsTests {

    @Test
    void testDateFormatter() throws ParseException {
        String originalFormat =  "MMMMM dd',' yyyyy";
        String finalFormat = "d MMMMM yyyy";
        Locale finalLocale = new Locale("es");

        String date1= "December 14, 2015";
        String expected = "14 diciembre 2015";
        String obtained = Utils.transformFormatDate(date1, originalFormat, Locale.ENGLISH, finalFormat, finalLocale);
        Assertions.assertEquals(expected, obtained);

        String date2= "December 1, 2015";
        String expected2 = "1 diciembre 2015";
        String obtained2 = Utils.transformFormatDate(date2, originalFormat, Locale.ENGLISH, finalFormat, finalLocale);
        Assertions.assertEquals(expected2, obtained2);
    }
}
