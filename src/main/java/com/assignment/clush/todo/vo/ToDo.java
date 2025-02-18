package com.assignment.clush.todo.vo;

import com.assignment.clush.common.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("ToDo")
@Schema(description = "할 일")
public class ToDo {
    @Schema(description = "할 일 pk", example = "5")
    private Integer no;

    @NotBlank(message = "id를 입력하세요.")
    @Schema(description = "유저 아이디", example = "clush5", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;

    @NotBlank(message = "제목을 입력하세요.")
    @Schema(description = "제목", example = "clush title5", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotBlank(message = "내용를 입력하세요.")
    @Schema(description = "내용", example = "clush content5", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "생성일", example = "2025-02-19 14:00:00")
    private LocalDateTime createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "지나간 날짜를 입력할 수 없습니다.")
    @NotNull(message = "만료일을 입력하세요.")
    @Schema(description = "만료일", example = "2025-02-21 14:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime dueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "수정일", example = "2025-02-20 14:00:00")
    private LocalDateTime modifiedDate;

    @Schema(description = "상태", example = "PENDING", defaultValue = "PENDING", allowableValues = "DONE, IN_PROGRESS, PENDING")
    private Status status;

    @NotNull(message = "우선순위를 입력하세요.")
    @Range(min=1, max=3, message = "우선순위 범위는 1부터 3까지입니다.")
    @Schema(description = "우선순위", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer priority;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ToDo other = (ToDo) obj;
        return userId.equals(other.userId)
                && title.equals(other.title)
                && content.equals(other.content)
                && status.equals(other.status)
                && priority == other.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, title, content, status, priority);
    }
}
