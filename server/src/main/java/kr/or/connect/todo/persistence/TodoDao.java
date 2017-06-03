package kr.or.connect.todo.persistence;

import kr.or.connect.todo.model.Todo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Todo> rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);

    public TodoDao(DataSource dataSource) { /* Dao Init*/
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("todo")
                .usingGeneratedKeyColumns("id").usingColumns("todo");
    }


    public int delete(Integer id) { /* 삭제 */
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.update(TodoSqls.DELETE_BY_ID, params);

    }

    public int complete(Todo todo) {/*완료 */
        Map<String, Object> params = new HashMap<>();
        params.put("id", todo.getId());
        params.put("completed", todo.getCompleted());
        return jdbc.update(TodoSqls.UPDATE_COMP_BY_ID, params);
    }

    public Integer create(Todo todo) {/*생성*/
        SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public List<Todo> listAll() {/*모든리스트*/
        Map<String, Object> params = Collections.emptyMap();
        return jdbc.query(TodoSqls.SELECT_ALL, params, rowMapper);
    }


}
