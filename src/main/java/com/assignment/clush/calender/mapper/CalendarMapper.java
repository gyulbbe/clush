package com.assignment.clush.calender.mapper;

import com.assignment.clush.calender.vo.Calendar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CalendarMapper {

    Calendar getCalendarByNo(@Param("calendarNo") Integer calendarNo);
    int countCalendarByNo(@Param("calendarNo") Integer calendarNo);
    void insertCalendar(@Param("calendar") Calendar calendar);
    void updateCalendar(@Param("calendar") Calendar calendar);
    void deleteCalendar(@Param("calendarNo") Integer calendarNo);
}
