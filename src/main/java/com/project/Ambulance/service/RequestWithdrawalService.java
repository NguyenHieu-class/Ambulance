package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.RequestWithdrawal;

public interface RequestWithdrawalService {
	void saveRequestWithdrawal( RequestWithdrawal reqWithdrawal);
	List<RequestWithdrawal> getAllRequestWithdraw();
	List<RequestWithdrawal> getAllRequestWithdrawOrderByCreateDate();
	List<RequestWithdrawal> getAllRequestWithdrawByIdUserOrderByCreateDate(int idUser);
	RequestWithdrawal getRequestWithdrawalById(int idReq);
	void deleteRequestWithdrawal(int idReq);
	void changeStatusRequestWithdraw(int stausRequest, int idRequest);
	
}
