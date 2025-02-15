package com.assignment.clush.common;

import java.time.LocalDateTime;

public class DateCalculator {

    private DateCalculator() {
        throw new IllegalStateException("유틸 클래스입니다.");
    }

    public static boolean dateCalculate(LocalDateTime startDate, LocalDateTime endDate) {
        return endDate.isAfter(startDate);
    }
}
