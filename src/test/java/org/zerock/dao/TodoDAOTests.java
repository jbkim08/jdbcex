package org.zerock.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dao.TodoDAO;

public class TodoDAOTests {

    private TodoDAO todoDAO;

    //미리 테스트전에 todoDAO 객체를 생성함
    @BeforeEach
    public void read(){
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception {
        System.out.println(todoDAO.getTime());
    }
}
