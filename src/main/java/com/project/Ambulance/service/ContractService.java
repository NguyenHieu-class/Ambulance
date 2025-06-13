package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.Contract;

public interface ContractService {
	List<Contract> getAllContract();
	Contract getContractById(int idContract);
	void saveContract(Contract contract);
	void deleteContract(int idContract);
}
