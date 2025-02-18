package com.assignment.clush.calender.controller;

import com.assignment.clush.calender.dto.CalendarByRangeDto;
import com.assignment.clush.calender.dto.CalendarUpdateForm;
import com.assignment.clush.calender.dto.ShareListDto;
import com.assignment.clush.calender.service.CalendarService;
import com.assignment.clush.calender.vo.Calendar;
import com.assignment.clush.common.RestResponseDto;
import com.assignment.clush.common.enums.Range;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "일정 API")
@RequestMapping("/api")
public class RestCalenderController {

    private final CalendarService calendarService;

    public RestCalenderController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @Operation(summary = "일정 상세 조회")
    @GetMapping("/calendar/{calendarNo}")
    public ResponseEntity<RestResponseDto<Calendar>> getCalendarByNo(@Parameter(description = "일정 번호", example = "5") @PathVariable("calendarNo") Integer calendarNo) {
        return ResponseEntity.ok(RestResponseDto.success(calendarService.getCalendarByNo(calendarNo), "정상적으로 일정을 불러왔습니다."));
    }

    @Operation(summary = "일정 등록")
    @PostMapping("/calendar")
    public ResponseEntity<RestResponseDto<Calendar>> insertCaledar(@Valid @ModelAttribute Calendar calendar) {
        return ResponseEntity.ok(RestResponseDto.success(calendarService.insertCalendar(calendar), "정상적으로 일정이 추가되었습니다."));
    }

    @Operation(summary = "일정 수정")
    @PutMapping("/calendar")
    public ResponseEntity<RestResponseDto<Calendar>> updateCalendar(@Valid @ModelAttribute CalendarUpdateForm calendarUpdateForm) {
        return ResponseEntity.ok(RestResponseDto.success(calendarService.updateCalendar(calendarUpdateForm), "정상적으로 일정이 수정되었습니다."));
    }

    @Operation(summary = "일정 삭제")
    @DeleteMapping("/calendar/{calendarNo}")
    public ResponseEntity<RestResponseDto<Void>> deleteCalendar(@Parameter(description = "일정 번호", example = "5") @PathVariable("calendarNo") Integer calendarNo) {
        calendarService.deleteCalendar(calendarNo);
        return ResponseEntity.ok(RestResponseDto.success(null, "정상적으로 일정이 삭제되었습니다."));
    }

    @Operation(summary = "일정에 공유된 ID목록 불러오기")
    @GetMapping("/share/{calendarNo}")
    public ResponseEntity<RestResponseDto<List<String>>> getShareByNo(@Parameter(description = "일정 번호", example = "5") @PathVariable("calendarNo") Integer calendarNo) {
        return ResponseEntity.ok(RestResponseDto.success(calendarService.getSharedIdByNo(calendarNo), "정상적으로 일정에 공유된 ID목록을 불러왔습니다."));
    }

    @Operation(summary = "일정 공유")
    @PostMapping("/share/{calendarNo}/{userId}")
    public ResponseEntity<RestResponseDto<ShareListDto>> insertShareList(@Parameter(description = "일정 번호", example = "5") @PathVariable("calendarNo") Integer calendarNo
            , @Parameter(description = "유저 ID", example = "clush5") @PathVariable("userId") String userId) {
        return ResponseEntity.ok(RestResponseDto.success(calendarService.insertShare(calendarNo, userId), "정상적으로 해당 일정에 " + userId + "를 공유하였습니다."));
    }

    @Operation(summary = "범위에 따른 일정 조회", description = "오늘, 이번주, 이번달에 해당하는 일정 리스트를 조회한다.")
    @GetMapping("/share/{userId}/{range}")
    public ResponseEntity<RestResponseDto<List<CalendarByRangeDto>>> getShareByIdAndRange(@Parameter(description = "유저 ID", example = "clush5") @PathVariable("userId") String userId
            , @PathVariable("range") Range range) {
        return ResponseEntity.ok(RestResponseDto.success(calendarService.getShareByIdAndRange(userId, range), "정상적으로 범위에 해당하는 공유된 일정을 불러왔습니다."));
    }
}
