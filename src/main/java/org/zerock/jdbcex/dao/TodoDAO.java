package org.zerock.jdbcex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
