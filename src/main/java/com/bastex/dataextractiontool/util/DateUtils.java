package com.bastex.dataextractiontool.util;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Objects;

@Service
public final class DateUtils {
    private DateTimeFormatter yearToLocalDateFormatter;

    public DateUtils() {
        yearToLocalDateFormatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
    }

    public LocalDate parseYearToLocalDate(String yearAsText) {
        Objects.requireNonNull(yearAsText, "Year for parsing cannot be null.");
        LocalDate localDate = LocalDate.parse(yearAsText, yearToLocalDateFormatter);
        return localDate;
    }
}
