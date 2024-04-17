package org.zerock.jdbcex.service;

import com.sun.tools.javac.comp.Todo;
import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.util.MapperUtil;

public enum TodoService {
    INSTANCE;

    private TodoDAO dao;  //DB사용객체(CRUD)
    private ModelMapper modelMapper; //DTO <=> VO 데이터 변환

    TodoService() {
        //생성시 필요한 객체들을 만듬 (싱글턴)
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    //화면(유저,브라우저)로 받은 데이터(DTO)를 VO로 변환해서 DB에 저장
    public void register(TodoDTO todoDTO) throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        System.out.println("todoVO = " + todoVO);
        dao.insert(todoVO);
    }
}
