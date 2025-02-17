package com.assignment.clush.todo.dto;

import com.assignment.clush.common.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("ToDoByConditionDto")
@Schema(description = "조건에 의해 보여줄 할 일")
public class ToDoByConditionDto {
    @Schema(description = "유저 ID", example = "clush5")
    private String userId;

    @Schema(description = "제목", example = "clush5 title")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "만료일", example = "2025-02-21 14:00:00")
    private LocalDateTime dueDate;

    @Schema(description = "상태", example = "PENDING", allowableValues = "PENDING, IN_PROGRESS, DONE")
    private Status status;

    @Schema(description = "우선 순위", example = "3")
    private int priority;
}
