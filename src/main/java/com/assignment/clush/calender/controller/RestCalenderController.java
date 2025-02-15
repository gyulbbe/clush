package com.assignment.clush.calender.controller;

import com.assignment.clush.calender.dto.ShareListDto;
import com.assignment.clush.calender.service.CalendarService;
import com.assignment.clush.calender.vo.Calendar;
import com.assignment.clush.common.RestResponseDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestCalenderController {

    private final CalendarService calendarService;

    public RestCalenderController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar/{calendarNo}")
    public ResponseEntity<RestResponseDto<Calendar>> getCalendarByNo(@PathVariable("calendarNo") Integer calendarNo) {
        return ResponseEntity.ok(RestResponseDto.success(calendarService.getCalendarByNo(calendarNo), "정상적으로 일정을 불러왔습니다."));
    }

    @PostMapping("/calendar")
    public ResponseEntity<RestResponseDto<Calendar>> insertCaledar(@Valid @ModelAttribute Calendar calendar) {
        return ResponseEntity.ok(RestResponseDto.success(calendarService.insertCalendar(calendar), "정상적으로 일정이 추가되었습니다."));
    }

    @PutMapping("/calendar")
    public ResponseEntity<RestResponseDto<Calendar>> updateCalendar(@Valid @ModelAttribute Calendar calendar) {
        return ResponseEntity.ok(RestResponseDto.success(calendarService.updateCalendar(calendar), "정상적으로 일정이 수정되었습니다."));
    }

    @DeleteMapping("/calendar/{calendarNo}")
    public ResponseEntity<RestResponseDto<Void>> deleteCalendar(@PathVariable("calendarNo") Integer calendarNo) {
        calendarService.deleteCalendar(calendarNo);
        return ResponseEntity.ok(RestResponseDto.success(null, "정상적으로 일정이 삭제되었습니다."));
    }

    @GetMapping("/share/{calendarNo}")
    public ResponseEntity<RestResponseDto<List<String>>> getShareByNo(@PathVariable("calendarNo") Integer calendarNo) {
        return ResponseEntity.ok(RestResponseDto.success(calendarService.getSharedIdByNo(calendarNo), "정상적으로 일정에 공유된 ID목록을 불러왔습니다."));
    }

    @PostMapping("/share/{calendarNo}/{userId}")
    public ResponseEntity<RestResponseDto<ShareListDto>> insertShareList(@PathVariable("calendarNo") Integer calendarNo, @PathVariable("userId") String userId) {
        return ResponseEntity.ok(RestResponseDto.success(calendarService.insertShare(calendarNo, userId), "정상적으로 해당 일정에 " + userId + "를 공유하였습니다."));
    }
}
