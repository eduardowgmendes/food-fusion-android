package br.com.edu.jet.foodfusion.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeUtils {

    public static LocalDateTime getLocalDateTime(String value) {
        if (value == null) return null;
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static String toStringLocalDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.toString();
    }

    public static String toFriendlyLocalDateTime(String localDateTime) {
        if (localDateTime != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy - HH:mm", Locale.getDefault());
            LocalDateTime dateTime = LocalDateTime.parse(localDateTime);
            return dateTime.format(dateTimeFormatter);
        }
        return null;
    }
}
