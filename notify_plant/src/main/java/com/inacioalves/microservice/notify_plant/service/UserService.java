package com.inacioalves.microservice.notify_plant.service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inacioalves.microservice.notify_plant.dto.MessageResponseDto;
import com.inacioalves.microservice.notify_plant.dto.UserDto;
import com.inacioalves.microservice.notify_plant.exeption.objectNotFoundException;
import com.inacioalves.microservice.notify_plant.mapper.UserMapper;
import com.inacioalves.microservice.notify_plant.model.Plant;
import com.inacioalves.microservice.notify_plant.model.User;
import com.inacioalves.microservice.notify_plant.repository.UserRepository;



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
	
	public UserDto findById(Long id) throws objectNotFoundException {
		User User =verifyIfExists(id);
		
		return userMapper.tpDto(User);
	}
	
	public List<Plant> findByPlant(Long id) throws objectNotFoundException{
		User user =verifyIfExists(id);
		
		return user.getPlant();
		
	}
	


	public void deleteById(Long id) throws objectNotFoundException {
		verifyIfExists(id);
		
		repository.deleteById(id);
	}
	

	public User updateById(User user)  {
		return  repository.save(user);
		
	}
	
	
	private User verifyIfExists(Long id) throws objectNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new objectNotFoundException("Plant not found with ID:"+id));
	}
	
	
	private MessageResponseDto createMessageResponse(Long id,String message) {
		return MessageResponseDto
				.builder()
				.message(message+id)
				.build();
	}

}
