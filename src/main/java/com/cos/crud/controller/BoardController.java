package com.cos.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.Board;
import com.cos.crud.model.User;
import com.cos.crud.repository.BoardRepository;
import com.cos.crud.service.BoardService;
import com.cos.crud.utils.Script;

@Controller
public class BoardController {

	@Autowired
	private BoardService mService;

	@PostMapping("/ict/{id}")
	public @ResponseBody String increaseCountAndTimeUpdate(@PathVariable int id) {
		mService.increaseCountAndTimeUpdate(id);
		
		return "테스트완료";
	}
	
	@GetMapping("/board/list")
	public String boardList(Model model) {
		List<Board> boards = mService.boardList();
		model.addAttribute("boards", boards);

		return "board/list";
	}

	@GetMapping("/board/detail/{id}")
	public String boardDetail(@PathVariable int id, Model model) {
		Board board = mService.boardDetail(id);
		model.addAttribute("board", board);
		return "board/detail";
	}

	@PostMapping("/board/write")
	public String boardWrite(Board board, HttpSession session) {
		int result = mService.boardWrite(board, session);
			
		if(result == 1) {
			return "redirect:/board/list";
		}else {
			return "redirect:/board/writeForm";
		}
			
		
		
	}

	@GetMapping("/board/writeForm")
	public String boardWriteForm(HttpSession session) {
		// 인터셉터 처리 AOP
		User user = (User) session.getAttribute("user");

		if (user != null) {
			return "/board/writeForm";
		} else {
			return "/user/loginForm";
		}

	}

	@DeleteMapping("/board/delete/{id}")
	public String boardDelete(@PathVariable int id) {
		
		int result = mService.boardDelete(id);
		
		if(result == 1) {
			return "redirect:/board/list";	
		}else {
			return null;
		}
		
	}

	@GetMapping("/board/updateForm/{id}")
	public String boardUpdateForm(@PathVariable int id, Model model) {
		Board board = mService.boardUpdateForm(id);
		model.addAttribute("board", board);
		return "/board/updateForm";
	}

	@PutMapping("/board/update")
	public @ResponseBody String boardUpdate(Board board, HttpSession session) {
		
		int result = mService.boardUpdate(board, session);

		if (result == 1) {
			return Script.href("/board/list");
		} else {
			return Script.back("수정 실패");
		}
	}

}
