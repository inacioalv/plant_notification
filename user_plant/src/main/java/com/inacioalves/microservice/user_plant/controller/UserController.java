package com.inacioalves.microservice.user_plant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inacioalves.microservice.user_plant.dto.MessageResponseDto;
import com.inacioalves.microservice.user_plant.dto.UserDto;
import com.inacioalves.microservice.user_plant.exeption.UserNotFoundExeption;
import com.inacioalves.microservice.user_plant.model.User;
import com.inacioalves.microservice.user_plant.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public MessageResponseDto createUser(@RequestBody UserDto userDto) {
		return userService.createUser(userDto);
	}
	
	@GetMapping("/all")
	public List<UserDto> listAll(){
		return userService.listAll();
	}
	
	@GetMapping("/{id}")
	 public UserDto findById(@PathVariable Long id) throws UserNotFoundExeption {
		 return userService.findById(id);
	 }
	 
	 @PutMapping("update/{id}")
	 public ResponseEntity<Void> UpdateUser(@RequestBody User user,@PathVariable Long id){
		 	user.setId(id);
			userService.updateById(user);
			return ResponseEntity.noContent().build();
		}
	 
	 @DeleteMapping("/{id}")
	 @ResponseStatus(code = HttpStatus.NO_CONTENT)
	 public void deleteById(@PathVariable  Long id) throws UserNotFoundExeption {
		 userService.deleteById(id);
		 
	 }
	
	

}
