package kr.or.connect.todo.api;

import kr.or.connect.todo.Service.TodoService;
import kr.or.connect.todo.help.AjaxResult;
import kr.or.connect.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by SungHere on 2017-06-02.
 */
@RestController
@RequestMapping("/api/todos")
public class TodoController { /* Rest 컨트롤러 */
    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    List<Todo> todoList() {
        return service.listAll();
    }


    @PostMapping/* Insert (할일 추가 ) */
    ResponseEntity<?> create(@RequestBody Todo todo) {
        AjaxResult ajaxResult = new AjaxResult();

        if (service.create(todo)) {
            ajaxResult.setResult("SUCS");

        } else {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }

    @PutMapping(value = "/{id}", consumes = "application/json") /* 할일 완료 여부 */
    ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Todo todo) {
        todo.setId(id);
        AjaxResult ajaxResult = new AjaxResult();
        if (service.complete(todo)) {
            ajaxResult.setResult("SUCS");

        } else {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") /* 할일 삭제 */
    ResponseEntity<?> delete(@PathVariable Integer id) {
        AjaxResult ajaxResult = new AjaxResult();

        if (service.delete(id)) {
            ajaxResult.setResult("SUCS");

        } else {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }
}
