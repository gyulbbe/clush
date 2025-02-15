package com.assignment.clush.calender.service;

import com.assignment.clush.calender.dto.ShareListDto;
import com.assignment.clush.calender.mapper.CalendarMapper;
import com.assignment.clush.calender.vo.Calendar;
import com.assignment.clush.common.DateCalculator;
import com.assignment.clush.common.RestClushException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CalendarService {

    private final CalendarMapper calendarMapper;

    public CalendarService(CalendarMapper calendarMapper) {
        this.calendarMapper = calendarMapper;
    }

    public Calendar getCalendarByNo(Integer calendarNo) {
        isExistedCalendar(calendarNo);
        
        return calendarMapper.getCalendarByNo(calendarNo);
    }

    public Calendar insertCalendar(Calendar calendar) {
        compareDate(calendar);

        calendar.setCreatedDate(LocalDateTime.now());
        calendarMapper.insertCalendar(calendar);
        calendarMapper.insertShare(calendar.getNo(), calendar.getUserId());
        return calendarMapper.getCalendarByNo(calendar.getNo());
    }

    public Calendar updateCalendar(Calendar calendar) {
        isExistedCalendar(calendar.getNo());
        compareDate(calendar);

        calendar.setModifiedDate(LocalDateTime.now());
        calendarMapper.updateCalendar(calendar);
        return calendarMapper.getCalendarByNo(calendar.getNo());
    }

    public void deleteCalendar(Integer calendarNo) {
        calendarMapper.deleteCalendar(calendarNo);
    }

    public List<String> getSharedIdByNo(Integer calendarNo) {
        isExistedCalendar(calendarNo);

        return calendarMapper.getSharedIdByNo(calendarNo);
    }

    public ShareListDto insertShare(Integer calendarNo, String userId) {
        if (calendarMapper.countShareByIdAndNo(calendarNo, userId) == 1) {
            throw new RestClushException("이미 공유중인 유저입니다.");
        }

        calendarMapper.insertShare(calendarNo, userId);
        List<String> userList = calendarMapper.getSharedIdByNo(calendarNo);
        Calendar calendar = calendarMapper.getCalendarByNo(calendarNo);
        return new ShareListDto(calendar, userList);
    }

    /**
     * calendar가 존재하지 않을 경우 에러를 반환한다.
     * @param calendarNo 일정 번호
     * @return 에러
     */
    private void isExistedCalendar(Integer calendarNo) {
        if (calendarMapper.countCalendarByNo(calendarNo) == 0) {
            throw new RestClushException("존재하지 않는 일정입니다.");
        }
    }

    /**
     * calendar의 시작날짜와 만료날짜를 비교해서 시작날짜가 더 미래이면 에러를 반환한다.
     * @param calendar 객체
     * @return 에러
     */
    private void compareDate(Calendar calendar) {
        if (!DateCalculator.dateCalculate(calendar.getStartDate(), calendar.getEndDate())) {
            throw new RestClushException("시작 날짜가 종료 날짜보다 이전이어야 합니다.");
        }
    }
}
