package kr.or.connect.todo.Service;

import kr.or.connect.todo.model.Todo;
import kr.or.connect.todo.persistence.TodoDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SungHere on 2017-06-02.
 */
@Service
public class TodoService {
    private TodoDao dao;


    public TodoService(TodoDao dao) {
        this.dao = dao;
    }

    public boolean create(Todo todo) {
        int result = dao.create(todo);
        return result > 0;
    }

    public List<Todo> listAll() {

        return dao.listAll();
    }

    public boolean complete(Todo todo) {
        int result = dao.complete(todo);
        return result == 1;
    }

    public boolean delete(Integer id) {
        int result = dao.delete(id);
        return result == 1;
    }

}
