package com.assignment.clush.calender.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("Share")
@Schema(description = "공유")
public class Share {
    @Schema(description = "공유 테이블 pk", example = "5")
    private Integer no;

    @Schema(description = "일정")
    private Calendar calendar;

    @Schema(description = "일정에 공유된 ID", example = "clush5")
    private String userId;
}
