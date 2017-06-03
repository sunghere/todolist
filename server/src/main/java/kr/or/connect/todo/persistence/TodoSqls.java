package kr.or.connect.todo.persistence;

public class TodoSqls {
    /*todos 삭제*/
    static final String DELETE_BY_ID =
            "DELETE FROM todo WHERE id= :id";
    /*todos 완료 업데이트*/
    static final String UPDATE_COMP_BY_ID =
            "UPDATE todo SET completed = :completed WHERE id= :id";
    /*리스트*/
    static final String SELECT_ALL =
            "SELECT id,todo,completed,date FROM todo ORDER BY ID DESC";


}
