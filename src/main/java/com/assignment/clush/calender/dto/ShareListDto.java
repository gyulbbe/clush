package com.assignment.clush.calender.dto;

import com.assignment.clush.calender.vo.Calendar;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("ShareListDto")
public class ShareListDto {
    private Calendar calendar;
    private List<String> userList;
}
