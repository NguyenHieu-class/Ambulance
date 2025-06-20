package com.project.Ambulance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.Ambulance.model.Role;
import com.project.Ambulance.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;

	@Override
	public List<Role> getAllRole() {

		return repository.findAll();
	}

	@Override
	public Role getRoleById(int id) {

		Optional<Role> optional = repository.findById(id);
		Role role = null;

		if (optional.isPresent()) {
			role = optional.get();
		} else {
			throw new RuntimeException("Not found id role: " + id);
		}

		return role;
	}

	@Override
	public void saveRole(@RequestBody Role role) {

		System.out.println(role);
		repository.save(role);

	}

	@Override
	public boolean deteleRole(int id) {

		return false;
	}

}
