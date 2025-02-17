package com.assignment.clush.todo.dto;

import com.assignment.clush.common.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("ConditionDto")
@Schema(description = "조건")
public class ConditionDto {
    @Schema(description = "유저 ID", example = "clush5")
    private String userId;

    @Schema(description = "제목", example = "clush5 title")
    private String title;

    @Schema(description = "내용", example = "clush5 content")
    private String content;

    @Schema(description = "상태", example = "PENDING")
    private Status status;

    @Schema(description = "우선 순위", example = "3")
    private int priority;

    @Schema(description = "정렬 기준", example = "dueDate", defaultValue = "createdDate" ,allowableValues = "priority, dueDate")
    private String sort;
}
