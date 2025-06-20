package com.project.Ambulance.service;

import java.util.List;
import java.util.Optional;

import com.project.Ambulance.model.User;

public interface UserService {

	void saveUser( User u);
	List<User> getAllUser();
	List<User> getAllUserOrderByUsername();
	void deleteUser(int id);
	User getAUser(int id_user);
	void updateTotalMoney(int totalMoney, int idUser);
	int countUser();
	Optional<User> findUserByUserName(String username);
	User getUserByIdCar(int idCar);
}
