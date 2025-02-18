package com.assignment.clush.calender.dto;

import com.assignment.clush.common.enums.Repeats;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Alias("CalendarUpdateForm")
@Schema(description = "일정 수정 폼")
public class CalendarUpdateForm {
    @Schema(description = "일정 테이블 pk", example = "5")
    private Integer no;

    @NotBlank(message = "제목을 입력하세요.")
    @Schema(description = "제목", example = "clush title1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    @Schema(description = "내용", example = "clush content1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @NotNull(message = "시작일을 입력하세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "지나간 날짜를 입력할 수 없습니다.")
    @Schema(description = "일정 시작일", example = "2025-02-21 14:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime startDate;

    @NotNull(message = "종료일을 입력하세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "지나간 날짜를 입력할 수 없습니다.")
    @Schema(description = "일정 종료일", example = "2025-02-22 14:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "생성일", example = "2025-02-19 14:00:00")
    private LocalDateTime createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "수정일", example = "2025-02-20 14:00:00")
    private LocalDateTime modifiedDate;

    @Schema(description = "반복 주기", example = "WEEK", allowableValues = "DAY, WEEK, MONTH, YEAR")
    private Repeats repeats;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CalendarUpdateForm other = (CalendarUpdateForm) obj;
        return title.equals(other.title)
                && content.equals(other.content)
                && startDate.equals(other.startDate)
                && endDate.equals(other.endDate)
                && Objects.equals(repeats, other.repeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, startDate, endDate, repeats);
    }
}
