package com.inacioalves.microservice.user_plant.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inacioalves.microservice.user_plant.dto.MessageResponseDto;
import com.inacioalves.microservice.user_plant.dto.UserDto;
import com.inacioalves.microservice.user_plant.exeption.UserNotFoundExeption;
import com.inacioalves.microservice.user_plant.mapper.UserMapper;
import com.inacioalves.microservice.user_plant.model.User;
import com.inacioalves.microservice.user_plant.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository repository;
	
	private final UserMapper userMapper = UserMapper.INSTANCE;

	
	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}

	public MessageResponseDto createUser(UserDto userDto) {
		User UserSave = userMapper.toModel(userDto);
		
		User savedUser = repository.save(UserSave);
		return createMessageResponse(savedUser.getId(), "Created User with Id:");
	}
	
	public List<UserDto> listAll(){
		List<User> allUser = repository.findAll();
		
		return allUser.stream()
				.map(userMapper::tpDto)
				.collect(Collectors.toList());
	}
	
	public UserDto findById(Long id) throws UserNotFoundExeption {
		User User =verifyIfExists(id);
		
		return userMapper.tpDto(User);
	}
	


	public void deleteById(Long id) throws UserNotFoundExeption {
		verifyIfExists(id);
		
		repository.deleteById(id);
	}
	

	public User updateById(User user)  {
		return  repository.save(user);
		
	}
	
	
	private User verifyIfExists(Long id) throws UserNotFoundExeption {
		return repository.findById(id)
				.orElseThrow(() -> new UserNotFoundExeption(id));
	}
	
	
	private MessageResponseDto createMessageResponse(Long id,String message) {
		return MessageResponseDto
				.builder()
				.message(message+id)
				.build();
	}

}
