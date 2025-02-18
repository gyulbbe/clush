package com.assignment.clush.todo.service;

import com.assignment.clush.common.DateCalculator;
import com.assignment.clush.common.RestClushException;
import com.assignment.clush.todo.dto.ConditionDto;
import com.assignment.clush.todo.dto.ToDoByConditionDto;
import com.assignment.clush.todo.dto.ToDoUpdateForm;
import com.assignment.clush.todo.mapper.ToDoMapper;
import com.assignment.clush.todo.vo.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ToDoService {

    private final ToDoMapper toDoMapper;

    public ToDoService(ToDoMapper toDoMapper) {
        this.toDoMapper = toDoMapper;
    }

    public ToDo getToDoByNo(Integer toDoNo) {
        isNotExisted(toDoNo);

        return toDoMapper.getToDoByNo(toDoNo);
    }

    public ToDo insertToDo(ToDo toDo) {
        compareDate(LocalDateTime.now(), toDo.getDueDate());
        toDo.setCreatedDate(LocalDateTime.now());
        toDoMapper.insertToDo(toDo);
        return toDoMapper.getToDoByNo(toDo.getNo());
    }

    public ToDo updateToDo(ToDoUpdateForm updateForm) {
        int no = updateForm.getNo();
        isNotExisted(no);
        compareDate(LocalDateTime.now(), updateForm.getDueDate());
        ToDo prevToDo = getToDoByNo(no);
        ToDoUpdateForm prevUpdateForm = ToDoUpdateForm.builder()
                .no(no)
                .title(prevToDo.getTitle())
                .content(prevToDo.getContent())
                .status(prevToDo.getStatus())
                .priority(prevToDo.getPriority())
                .build();
        if (updateForm.equals(prevUpdateForm)) throw new RestClushException("수정된 부분이 존재하지 않습니다.");

        updateForm.setModifiedDate(LocalDateTime.now());
        toDoMapper.updateToDo(updateForm);
        return toDoMapper.getToDoByNo(no);
    }

    public void deleteToDo(Integer toDoNo) {
        isNotExisted(toDoNo);

        toDoMapper.deleteToDo(toDoNo);
    }

    public List<ToDoByConditionDto> getToDoListByCondition(ConditionDto conditionDto) {
        return toDoMapper.getToDoListByCondition(conditionDto);
    }

    private void isNotExisted(Integer toDoNo) {
        if (toDoMapper.countToDoByNo(toDoNo) == 0) {
            throw new RestClushException("존재하지 않는 할 일입니다.");
        }
    }

    private void compareDate(LocalDateTime createdDate, LocalDateTime dueDate) {
        if (!DateCalculator.dateCalculate(createdDate, dueDate)) {
            throw new RestClushException("시작 날짜가 종료 날짜보다 이전이어야 합니다.");
        }
    }
}
