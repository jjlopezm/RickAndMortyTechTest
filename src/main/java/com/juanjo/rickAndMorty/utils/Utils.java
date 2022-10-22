package com.juanjo.rickAndMorty.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String transformFormatDate(String date1, String format1, Locale locale1,
                                             String format2, Locale locale2) throws ParseException {

        Date dateParsed = new SimpleDateFormat(format1, locale1).parse(date1);
        return new SimpleDateFormat(format2, locale2).format(dateParsed);
    }
}
