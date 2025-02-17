package com.assignment.clush.calender.dto;

import com.assignment.clush.calender.vo.Calendar;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "일정")
    private Calendar calendar;

    @Schema(description = "일정에 공유가 된 유저 ID 리스트")
    private List<String> userList;
}
