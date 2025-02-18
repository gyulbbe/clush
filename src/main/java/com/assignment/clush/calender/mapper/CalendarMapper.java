package com.assignment.clush.calender.mapper;

import com.assignment.clush.calender.dto.CalendarByRangeDto;
import com.assignment.clush.calender.dto.CalendarUpdateForm;
import com.assignment.clush.calender.vo.Calendar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CalendarMapper {
    Calendar getCalendarByNo(@Param("calendarNo") Integer calendarNo);
    int countCalendarByNo(@Param("calendarNo") Integer calendarNo);
    void insertCalendar(@Param("calendar") Calendar calendar);
    void updateCalendar(@Param("calendarUpdateForm") CalendarUpdateForm calendarUpdateForm);
    void deleteCalendar(@Param("calendarNo") Integer calendarNo);
    List<String> getSharedIdByNo(@Param("calendarNo") Integer calendarNo);
    void insertShare(@Param("calendarNo") Integer calendarNo, @Param("userId") String userId);
    int countShareByIdAndNo(@Param("calendarNo") Integer calendarNo, @Param("userId") String userId);
    List<CalendarByRangeDto> getShareOneDayByIdAndRange(@Param("userId") String userId, @Param("now") LocalDate now);
    List<CalendarByRangeDto> getShareOneWeekByIdAndRange(@Param("userId") String userId, @Param("firstDate") LocalDate firstDate, @Param("lastDate") LocalDate lastDate);
    List<CalendarByRangeDto> getShareOneMonthByIdAndRange(@Param("userId") String userId, @Param("firstDate") LocalDate firstDate, @Param("lastDate") LocalDate lastDate);
}
