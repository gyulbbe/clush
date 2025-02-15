package com.assignment.clush.calender.service;

import com.assignment.clush.calender.mapper.CalendarMapper;
import com.assignment.clush.calender.vo.Calendar;
import com.assignment.clush.common.DateCalculator;
import com.assignment.clush.common.RestClushException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CalendarService {

    private final CalendarMapper calendarMapper;

    public CalendarService(CalendarMapper calendarMapper) {
        this.calendarMapper = calendarMapper;
    }

    public Calendar getCalendarByNo(Integer calendarNo) {
        if (calendarMapper.countCalendarByNo(calendarNo) == 0) {
            throw new RestClushException("존재하지 않는 일정입니다.");
        }
        return calendarMapper.getCalendarByNo(calendarNo);
    }

    public Calendar insertCalendar(Calendar calendar) {
        if (!DateCalculator.dateCalculate(calendar.getStartDate(), calendar.getEndDate())) {
            throw new RestClushException("시작 날짜가 종료 날짜보다 이전이어야 합니다.");
        }

        calendar.setCreatedDate(LocalDateTime.now());
        calendarMapper.insertCalendar(calendar);
        return calendarMapper.getCalendarByNo(calendar.getNo());
    }

    public Calendar updateCalendar(Calendar calendar) {
        if (calendarMapper.countCalendarByNo(calendar.getNo()) == 0) {
            throw new RestClushException("존재하지 않는 일정입니다.");
        }

        if (!DateCalculator.dateCalculate(calendar.getStartDate(), calendar.getEndDate())) {
            throw new RestClushException("시작 날짜가 종료 날짜보다 이전이어야 합니다.");
        }

        calendar.setModifiedDate(LocalDateTime.now());
        calendarMapper.updateCalendar(calendar);
        return calendarMapper.getCalendarByNo(calendar.getNo());
    }

    public void deleteCalendar(Integer calendarNo) {
        calendarMapper.deleteCalendar(calendarNo);
    }
}
