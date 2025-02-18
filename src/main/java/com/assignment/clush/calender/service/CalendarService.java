package com.assignment.clush.calender.service;

import com.assignment.clush.calender.dto.CalendarByRangeDto;
import com.assignment.clush.calender.dto.CalendarUpdateForm;
import com.assignment.clush.calender.dto.ShareListDto;
import com.assignment.clush.calender.mapper.CalendarMapper;
import com.assignment.clush.calender.vo.Calendar;
import com.assignment.clush.common.DateCalculator;
import com.assignment.clush.common.RestClushException;
import com.assignment.clush.common.enums.Range;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
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
        compareDate(calendar.getStartDate(), calendar.getEndDate());

        calendar.setCreatedDate(LocalDateTime.now());
        calendarMapper.insertCalendar(calendar);
        calendarMapper.insertShare(calendar.getNo(), calendar.getUserId());
        return calendarMapper.getCalendarByNo(calendar.getNo());
    }

    public Calendar updateCalendar(CalendarUpdateForm calendarUpdateForm) {
        int no = calendarUpdateForm.getNo();
        isExistedCalendar(no);
        Calendar calendar = calendarMapper.getCalendarByNo(no);
        CalendarUpdateForm prevCalendarUpdateForm = CalendarUpdateForm.builder()
                .title(calendar.getTitle())
                .content(calendar.getContent())
                .startDate(calendar.getStartDate())
                .endDate(calendar.getEndDate())
                .repeats(calendar.getRepeats()).build();
        if (prevCalendarUpdateForm.equals(calendarUpdateForm)) throw new RestClushException("수정된 부분이 존재하지 않습니다.");
        compareDate(calendarUpdateForm.getStartDate(), calendarUpdateForm.getEndDate());

        calendarUpdateForm.setModifiedDate(LocalDateTime.now());
        calendarMapper.updateCalendar(calendarUpdateForm);
        return calendarMapper.getCalendarByNo(no);
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

    public List<CalendarByRangeDto> getShareByIdAndRange(String userId, Range range) {
        if (range.equals(Range.DAY)) {
            return calendarMapper.getShareOneDayByIdAndRange(userId, LocalDate.now());
        } else if (range.equals(Range.WEEK)) {
            int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
            LocalDate firstDate = LocalDate.now().minusDays(dayOfWeek); // 이번 주 첫째 날
            LocalDate lastDate = firstDate.plusDays(6); // 이번 주 마지막 날
            List<CalendarByRangeDto> dtoList = calendarMapper.getShareOneWeekByIdAndRange(userId, firstDate, lastDate);
            if (dtoList.isEmpty()) {
                throw new RestClushException("이번 주에 해당하는 일정이 존재하지 않습니다.");
            }
            return dtoList;
        } else {
            LocalDate firstDate = LocalDate.now().withDayOfMonth(1); // 이번 달 첫째 날
            LocalDate lastDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()); // 이번 달 마지막 날
            List<CalendarByRangeDto> dtoList = calendarMapper.getShareOneMonthByIdAndRange(userId, firstDate, lastDate);
            if (dtoList.isEmpty()) {
                throw new RestClushException("이번 달에 해당하는 일정이 존재하지 않습니다.");
            }
            return dtoList;
        }
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
     * @param startDate 시작 날짜, endDate 만료 날짜
     * @return 에러
     */
    private void compareDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (!DateCalculator.dateCalculate(startDate, endDate)) {
            throw new RestClushException("시작 날짜가 종료 날짜보다 이전이어야 합니다.");
        }
    }
}
