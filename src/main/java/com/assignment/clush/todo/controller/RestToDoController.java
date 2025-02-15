package com.assignment.clush.todo.controller;

import com.assignment.clush.common.RestResponseDto;
import com.assignment.clush.todo.dto.ConditionDto;
import com.assignment.clush.todo.dto.ToDoByConditionDto;
import com.assignment.clush.todo.service.ToDoService;
import com.assignment.clush.todo.vo.ToDo;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api")
public class RestToDoController {

    private final ToDoService toDoService;

    @Autowired
    public RestToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/toDo/{toDoNo}")
    public ResponseEntity<RestResponseDto<ToDo>> getToDoByNo(@PathVariable("toDoNo") Integer toDoNo) {
        return ResponseEntity.ok(RestResponseDto.success(toDoService.getToDoByNo(toDoNo), "정상적으로 할 일을 불러왔습니다."));
    }

    @PostMapping("/toDo")
    public ResponseEntity<RestResponseDto<ToDo>> insertToDo(@Valid @ModelAttribute ToDo toDo) {
        return ResponseEntity.ok(RestResponseDto.success(toDoService.insertToDo(toDo), "정상적으로 할 일이 등록되었습니다."));
    }

    @PutMapping("/toDo")
    public ResponseEntity<RestResponseDto<ToDo>> updateToDo(@Valid @ModelAttribute ToDo toDo) {
        return ResponseEntity.ok(RestResponseDto.success(toDoService.updateToDo(toDo), "정상적으로 할 일이 수정되었습니다."));
    }

    @DeleteMapping("/toDo/{toDoNo}")
    public ResponseEntity<RestResponseDto<ToDo>> deleteToDo(@PathVariable("toDoNo") Integer toDoNo) {
        toDoService.deleteToDo(toDoNo);
        return ResponseEntity.ok(RestResponseDto.success(null, "정상적으로 할 일이 삭제되었습니다."));
    }

    @GetMapping("/toDo")
    public List<ToDoByConditionDto> getToDoListByCondition(@ModelAttribute ConditionDto conditionDto) {
        return toDoService.getToDoListByCondition(conditionDto);
    }
}
