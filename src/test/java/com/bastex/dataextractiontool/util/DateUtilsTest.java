package com.bastex.dataextractiontool.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateUtilsTest {
    private static final DateUtils DATE_UTILS = new DateUtils();

    @Test
    public void parseYearToLocalDate_ProperYearIsSpecified_ShouldReturnParsedLocalDate() {
        LocalDate localDate = DATE_UTILS.parseYearToLocalDate("1989");
        int year = localDate.getYear();

        Assert.assertNotNull(localDate);
        Assert.assertEquals(1989, year);
    }

    @Test(expected = DateTimeParseException.class)
    public void parseYearToLocalDate_YearIsRandomString_ShouldThrowDateTimeParseException() {
        DATE_UTILS.parseYearToLocalDate("asdasda");
    }

    @Test(expected = NullPointerException.class)
    public void parseYearToLocalDate_YearIsNull_ShouldThrowNPE() {
        DATE_UTILS.parseYearToLocalDate(null);
    }
}