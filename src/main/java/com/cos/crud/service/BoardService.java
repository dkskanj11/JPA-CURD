package com.cos.crud.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.Board;
import com.cos.crud.model.User;
import com.cos.crud.repository.BoardRepository;
import com.cos.crud.utils.Script;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository mRepo;
	
	@Transactional
	public void increaseCountAndTimeUpdate(int id) {
		try {
			mRepo.increaseCount(id);
			mRepo.TimeUpdate(id);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	// DB관련 로직만 서비스에 필요하다.
	public List<Board> boardList() {
		List<Board> boards = mRepo.findAll();

		return boards;
	}

	
	public Board boardDetail(int id) {
		Optional<Board> board = mRepo.findById(id);
		
		return board.get();
	}

	
	public int boardWrite(Board board, HttpSession session) {
		
		try {
			User user = (User) session.getAttribute("user");
			board.setUser(user);
			mRepo.save(board);
			return 1;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	
	public int boardDelete(int id) {
		
		try {
			mRepo.deleteById(id);
			 return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
		
	}

	
	public Board boardUpdateForm(@PathVariable int id) {
		Optional<Board> board = mRepo.findById(id);
		return board.get();
	}
	
	public int boardUpdate(Board board, HttpSession session) {
		
		try {
			User user = (User) session.getAttribute("user");
			board.setUser(user);
			mRepo.save(board);
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

}
