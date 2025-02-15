package com.assignment.clush.calender.vo;

import com.assignment.clush.common.Repeat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Alias("Calendar")
public class Calendar {
    private Integer no;
    @NotBlank(message = "id를 입력하세요.")
    private String userId;
    @NotBlank(message = "제목을 입력하세요.")
    private String title;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;
    @NotNull(message = "시작일을 입력하세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "지나간 날짜를 입력할 수 없습니다.")
    private LocalDateTime startDate;
    @NotNull(message = "종료일을 입력하세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "지나간 날짜를 입력할 수 없습니다.")
    private LocalDateTime endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;
    private Repeat repeat;
}
