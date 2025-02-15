package com.assignment.clush.calender.controller;

import com.assignment.clush.calender.service.CalendarService;
import com.assignment.clush.calender.vo.Calendar;
import com.assignment.clush.common.RestResponseDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
