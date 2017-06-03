(function ($) {
    'use strict';
    var selector = "";
    /* 필터 기억용 변수*/
    // Your starting point. Enjoy the ride!
    /*리스트 불러오기 + 할일갯수 ㅊ초기화*/
    var todo_list_load = function () {
        var str = "";
        $.ajax({
            url: "/api/todos",
            method: "get",
            success: function (data) {
                var notCom_count = 0;
                $.each(data, function (index, val) {
                    /* 선택자에 의해서 필터링해주는부분*/
                    if (selector == "active" && val.completed == 1) {

                        return true;
                    } else if (selector == "completed" && val.completed == 0) {
                        return true;

                    }
                    /*필터링 End*/
                    if (val.completed == 1) {
                        str += '<li class="completed">'

                    } else {
                        str += '<li>'
                    }
                    str += '<div class="view">'
                        + '<input class="toggle" type="checkbox" datasrc="' + val.id + '" ';
                    if (val.completed == 1) {
                        str += ' checked="">'
                    } else {
                        str += '>'
                        notCom_count++;
                    }
                    str += '<label>' + val.todo + '</label>'
                        + '<button class="destroy" datasrc="' + val.id + '"></button>'
                        + '</div>'
                        + '<input class="edit" value="Create a TodoMVC template">'
                        + '</li>';
                })
                if (selector == "" || selector == null) {
                    $('.todo-count strong').html(notCom_count);
                }
                $('.todo-list').html(str);
            }, fail: function () {
                alert("실패")

            }

        })
    }
    /*todos_create ajax*/

    var todo_create = function () {
        var todo_content = $('.new-todo').val();
        if (todo_content.length < 1 || todo_content == "") {

            alert("추가할 일정을 입력하세요");

            return;
        }
        var object = new Object();
        object.todo = todo_content;
        $.ajax({
            url: "/api/todos",
            method: "post",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(object),
            success: function (data) {
                $('.new-todo').val('')
                console.log(data.result)
                if (data.result == "SUCS") {
                    todo_list_load("");
                } else {
                    alert("실패")

                }
            }, fail: function () {
                alert("실패")

            }

        })
    }
    /*todo_delete 변경 ajax*/

    var todo_delete = function (id) {

        $.ajax({
            url: "/api/todos/" + id,
            contentType: "application/json",
            method: "delete",
            success: function (data) {
                if (data.result == "SUCS") {
                    todo_list_load("");
                } else {
                    alert("실패")

                }
            }, fail: function () {
                alert("실패")

            }

        })
    }
    /*completed 변경 ajax*/
    var todo_complete = function (id, completed) {
        var object = new Object();
        object.completed = completed;
        $.ajax({
            url: "/api/todos/" + id,
            contentType: "application/json",
            data: JSON.stringify(object),
            method: "put",
            success: function (data) {

                if (data.result == "SUCS") {
                    todo_list_load("");
                } else {
                    alert("실패")

                }

            }

        })
    }
    /*filter css*/
    var filter_css_reset = function () {
        $('a').removeClass("selected");
    }

    /*초기 세팅*/
    todo_list_load("");


    /* Event */
    $(".new-todo").keypress(function (event) {
        if (event.which == '13') {
            event.preventDefault();
            todo_create();
        }
    });
    /* 체크박스 누를떄 */
    $('.todo-list').on("change", ".toggle", function () {
        var todo_id = $(this).attr("datasrc");
        var chkbox_isSel = $(this).prop("checked");
        if (chkbox_isSel) {/* 체크할경우*/
            todo_complete(todo_id, 1);
        } else { /* 체크 풀경우 */
            todo_complete(todo_id, 0);
        }
    })
    /* destory 누를때*/
    $('.todo-list').on("click", ".destroy", function () {
        var todo_id = $(this).attr("datasrc");


        todo_delete(todo_id);
    });

    /*filter*/
    $('.filters').on('click', 'a', function () {
        event.preventDefault();

        selector = $(this).attr('href').split('/')[1];
        if (selector != null) {
            filter_css_reset();
            $(this).addClass('selected');
            todo_list_load();
        } else {
            /* null일경우 default값 */
            selector = ""
        }

    })
    $('.clear-completed').click(function () {
        if ($('.filters li a.selected').attr('href').split('/')[1] == 'active') {
            /*만약 선택되있던 filter 가 active라면, 디폴트로 돌린다*/
            $('.filters li a[href="#/"]').click();
        }
        /*위의 이벤트가 일어나기 전까지 딜레이*/
        setTimeout("$('li.completed').find('.destroy').click()", 500);


    })

})
(jQuery);