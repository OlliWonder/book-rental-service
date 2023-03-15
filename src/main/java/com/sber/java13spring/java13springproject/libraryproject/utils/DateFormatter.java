package com.sber.java13spring.java13springproject.libraryproject.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    private DateFormatter() {
    }
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static LocalDate formatStringToDate(final String dateToFormat) {
        return LocalDate.parse(dateToFormat, FORMATTER);
    }
}
