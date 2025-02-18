package com.assignment.clush.calender.dto;

import com.assignment.clush.common.enums.Repeats;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "범위에 따른 일정")
public class CalendarByRangeDto {
    @Schema(description = "제목", example = "clush5 title")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "시작일", example = "2025-02-20 14:00:00")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "종료일", example = "2025-02-21 14:00:00")
    private LocalDateTime endDate;

    @Schema(description = "반복 주기", example = "WEEK", allowableValues = "DAY, WEEK, MONTH, YEAR")
    private Repeats repeats;
}
