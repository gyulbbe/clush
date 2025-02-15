package com.assignment.clush.todo.dto;

import com.assignment.clush.common.Status;
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
public class ToDoByConditionDto {
    private String userId;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;
    private Status status;
    private int priority;
}
