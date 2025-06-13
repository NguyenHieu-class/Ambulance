package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.Notification;

public interface NotificationService {
	List<Notification> getAllNotification();
	void saveNotification(Notification noti);
	void deleteNotification( int idNotifi);
	Notification getANotification ( int idNoti);
	Notification getNotificationLaster();
	List<Notification> getNotificationByIdUser(int idUser);

}
