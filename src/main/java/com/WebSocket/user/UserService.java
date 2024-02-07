package com.WebSocket.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class UserService {
//
//	private final UserRepository userRepository;
//	
//	public void saveUser(User user) {
//		try {
//			user.setStatus(Status.ONLINE);
//			userRepository.save(user);
//		} catch (Exception e) {
//			log.error("Exception occurred in saveUser()-> UserService "+ e);
//		}
//		
//	}
//	
//	public void disconnect(User user) {
//		
//		try {
//			User storedUser = userRepository.findById(user.getNickName())
//					.orElse(null);
//			if(storedUser != null) {
//			storedUser.setStatus(Status.OFFLINE);
//			userRepository.save(storedUser);
//			}
//		} catch (Exception e) {
//			log.error("Exception occurred in disconnect()-> UserService "+ e);
//		}
//	}
//	
//	public List<User> findConnectedUsers(){
//		try {
//			return userRepository.findAllByStatus(Status.ONLINE);
//		} catch (Exception e) {
//			log.error("Exception occurred in findConnectedUsers()-> UserService "+ e);
//		}
//		return new ArrayList<>();
//	}
//	
//}

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(User user) {
        var storedUser = repository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }
}
