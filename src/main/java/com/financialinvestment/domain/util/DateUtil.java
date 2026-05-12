package com.financialinvestment.domain.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String getTodayDateyyyyMMdd(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
        return today.format(formatter);
    }
}
