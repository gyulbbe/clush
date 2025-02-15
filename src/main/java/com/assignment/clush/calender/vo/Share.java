package com.assignment.clush.calender.vo;

import lombok.*;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("Share")
public class Share {
    private Integer no;
    private Calendar calendar;
    private String userId;
}
