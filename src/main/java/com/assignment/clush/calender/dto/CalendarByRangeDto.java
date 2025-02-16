package com.assignment.clush.calender.dto;

import com.assignment.clush.common.enums.Repeat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Alias("CalendarByRangeDto")
public class CalendarByRangeDto {
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    private Repeat repeat;
}
