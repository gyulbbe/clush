package com.assignment.clush.todo.dto;

import com.assignment.clush.common.enums.Status;
import lombok.*;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("ConditionDto")
public class ConditionDto {
    private String userId;
    private String title;
    private String content;
    private Status status;
    private int priority;
    private String sort;
}
