package kr.or.connect.todo.model;

import java.io.Serializable;

/**
 * Created by SungHere on 2017-06-02.
 */

public class Todo implements Serializable {

    private int id;
    private String todo;
    private String date;
    private int completed;

    public Todo() {
    }

    /**
     *
     * @param id 식별자 (기본키)
     * @param todo, 할일 내용
     * @param date 날짜 (default TimeStemp)
     * @param completed 완료 여부( default 0) 완료시 1
     */
    public Todo(int id, String todo, String date, int completed) {
        this.id = id;
        this.todo = todo;
        this.date = date;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", todo='" + todo + '\'' +
                ", date='" + date + '\'' +
                ", completed=" + completed +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}
