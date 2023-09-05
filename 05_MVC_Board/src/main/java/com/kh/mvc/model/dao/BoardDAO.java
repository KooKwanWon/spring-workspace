package com.kh.mvc.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mvc.model.vo.Board;
import com.kh.mvc.model.vo.Criteria;
@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insertBoard(Board board) {
		int result = sqlSession.insert("board.insert", board);
		
		return result;
	}
	
	public List<Board> selectAll(Criteria cri){
		return sqlSession.selectList("board.selectAll", cri);
	}
	
	public Board select(int no){
		return sqlSession.selectOne("board.select", no);
	}
	
	public int getTotal() {
		return sqlSession.selectOne("board.getTotal");
	}
	
	public int updateBoard(Board board) {
		return sqlSession.update("board.update", board);
	}
	
	public int deleteBoard(int no) {
		return sqlSession.delete("board.delete", no);
	}
}
