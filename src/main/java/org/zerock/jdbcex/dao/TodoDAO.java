package org.zerock.jdbcex.dao;

import lombok.Cleanup;
import org.zerock.jdbcex.domain.TodoVO;

import java.sql.*;

public class TodoDAO {

    public String getTime() {
        String now = null;

        //try()안의 객체들은 자동으로 close()됨
        try(Connection connection=ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select now()"); //sql 문 준비
            //결과를 받아오는 resultSet 객체
            ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            //결과에서 첫번째 행을 가져옴
            resultSet.next();
            //첫번째 행에서 1번째 열의 값을 가져옴
            now = resultSet.getString(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return now;
    }

    public String getTime2() throws Exception {

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement =
                connection.prepareStatement("select now() from dual");
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString(1);
    }

    //사용자가 입력한 할일을 데이터베이스에 저장함
    public void insert(TodoVO vo) throws Exception {
        String sql = "insert into tbl_todo (title,dueDate,finished) values (?,?,?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //sql 객체에 ?에 실제 값을 입력함
        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
        preparedStatement.setBoolean(3, vo.isFinished());

        preparedStatement.executeUpdate(); //sql 을 실행함
    }
}









