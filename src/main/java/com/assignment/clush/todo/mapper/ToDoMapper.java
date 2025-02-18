package com.assignment.clush.todo.mapper;

import com.assignment.clush.todo.dto.ConditionDto;
import com.assignment.clush.todo.dto.ToDoByConditionDto;
import com.assignment.clush.todo.dto.UpdateForm;
import com.assignment.clush.todo.vo.ToDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ToDoMapper {

    ToDo getToDoByNo(@Param("toDoNo") Integer toDoNo);
    void insertToDo(@Param("toDo") ToDo toDo);
    void updateToDo(@Param("updateForm") UpdateForm updateForm);
    void deleteToDo(@Param("toDoNo") Integer toDoNo);
    int countToDoByNo(@Param("toDoNo") Integer toDoNo);
    List<ToDoByConditionDto> getToDoListByCondition(@Param("conditionDto") ConditionDto conditionDto);
}
