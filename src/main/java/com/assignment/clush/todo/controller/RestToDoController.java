package com.assignment.clush.todo.controller;

import com.assignment.clush.common.RestResponseDto;
import com.assignment.clush.todo.dto.ConditionDto;
import com.assignment.clush.todo.dto.ToDoByConditionDto;
import com.assignment.clush.todo.dto.ToDoUpdateForm;
import com.assignment.clush.todo.service.ToDoService;
import com.assignment.clush.todo.vo.ToDo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "할 일 API")
@RequestMapping("/api")
public class RestToDoController {

    private final ToDoService toDoService;

    @Autowired
    public RestToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Operation(summary = "할 일 상세 조회")
    @GetMapping("/toDo/{toDoNo}")
    public ResponseEntity<RestResponseDto<ToDo>> getToDoByNo(@Parameter(description = "할 일 번호", example = "5") @PathVariable("toDoNo") Integer toDoNo) {
        return ResponseEntity.ok(RestResponseDto.success(toDoService.getToDoByNo(toDoNo), "정상적으로 할 일을 불러왔습니다."));
    }

    @Operation(summary = "할 일 등록")
    @PostMapping("/toDo")
    public ResponseEntity<RestResponseDto<ToDo>> insertToDo(@Valid @ModelAttribute ToDo toDo) {
        return ResponseEntity.ok(RestResponseDto.success(toDoService.insertToDo(toDo), "정상적으로 할 일이 등록되었습니다."));
    }

    @Operation(summary = "할 일 수정")
    @PutMapping("/toDo")
    public ResponseEntity<RestResponseDto<ToDo>> updateToDo(@Valid @ModelAttribute ToDoUpdateForm updateForm) {
        return ResponseEntity.ok(RestResponseDto.success(toDoService.updateToDo(updateForm), "정상적으로 할 일이 수정되었습니다."));
    }

    @Operation(summary = "할 일 삭제")
    @DeleteMapping("/toDo/{toDoNo}")
    public ResponseEntity<RestResponseDto<ToDo>> deleteToDo(@Parameter(description = "할 일 번호", example = "5") @PathVariable("toDoNo") Integer toDoNo) {
        toDoService.deleteToDo(toDoNo);
        return ResponseEntity.ok(RestResponseDto.success(null, "정상적으로 할 일이 삭제되었습니다."));
    }

    @Operation(summary = "할 일 리스트 조회", description = "조건에 따라 검색과 정렬을 하여 할 일 리스트를 불러옵니다.")
    @GetMapping("/toDo")
    public ResponseEntity<RestResponseDto<List<ToDoByConditionDto>>> getToDoListByCondition(@ModelAttribute ConditionDto conditionDto) {
        return ResponseEntity.ok(RestResponseDto.success(toDoService.getToDoListByCondition(conditionDto), "정상적으로 리스트를 불러왔습니다."));
    }
}
