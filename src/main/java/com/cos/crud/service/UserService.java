package com.cos.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.User;
import com.cos.crud.repository.UserRepository;
import com.cos.crud.utils.Script;

@Service
public class UserService {

	@Autowired
	private UserRepository mRepo;
	
	
	public List<User> getUser(int id) {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			users.add(mRepo.findById(id).get());
		}
		return users;
	}	
	
	public User userLogin(User user) {
		
		User u = mRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		return u;
	}
	
	
	
	public int userJoin(User user) {
		
		try {
			mRepo.save(user);
			
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	
}
