package com.inacioalves.microservice.notify_plant.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inacioalves.microservice.notify_plant.dto.MessageResponseDto;
import com.inacioalves.microservice.notify_plant.dto.UserDto;
import com.inacioalves.microservice.notify_plant.exeption.objectNotFoundException;
import com.inacioalves.microservice.notify_plant.model.Plant;
import com.inacioalves.microservice.notify_plant.model.User;
import com.inacioalves.microservice.notify_plant.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/user")
@Api(value = "Api Rest User")
@CrossOrigin(origins = "*")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation(value = "Create user")
	public MessageResponseDto createUser(@RequestBody UserDto userDto) {
		return userService.createUser(userDto);
	}
	
	@GetMapping("/all")
	@ApiOperation(value = "Return list user")
	public List<UserDto> listAll(){
		return userService.listAll();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Return user by Id")
	public UserDto findById(@PathVariable Long id) throws objectNotFoundException {
		 return userService.findById(id);
	 }
	
	@GetMapping("/{id}/plant")
	@ApiOperation(value = "Return user by Id")
	public List<Plant> findByPlant(@PathVariable Long id) throws objectNotFoundException {
		return userService.findByPlant(id);
	}
	 
	 @PutMapping("update/{id}")
	 @ApiOperation(value = "Update user")
	 public ResponseEntity<Void> UpdateUser(@RequestBody User user,@PathVariable Long id){
		 	user.setId(id);
			userService.updateById(user);
			return ResponseEntity.noContent().build();
		}
	 
	 @DeleteMapping("/{id}")
	 @ApiOperation(value = "Delete user")
	 @ResponseStatus(code = HttpStatus.NO_CONTENT)
	 public void deleteById(@PathVariable  Long id) throws objectNotFoundException {
		 userService.deleteById(id);
		 
	 }
	
	

}
