package com.assignment.clush.todo.vo;

import com.assignment.clush.common.enums.Status;
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
public class ToDo {
    private Integer no;
    @NotBlank(message = "id를 입력하세요.")
    private String userId;
    @NotBlank(message = "제목을 입력하세요.")
    private String title;
    @NotBlank(message = "내용를 입력하세요.")
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "지나간 날짜를 입력할 수 없습니다.")
    @NotNull(message = "종료일을 입력하세요.")
    private LocalDateTime dueDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;
    private Status status;
    @NotNull(message = "우선순위를 입력하세요.")
    @Range(min=1, max=3, message = "우선순위 범위는 1부터 3까지입니다.")
    private int priority;

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
