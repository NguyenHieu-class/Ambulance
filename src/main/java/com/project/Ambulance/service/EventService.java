package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.Event;

public interface EventService {

	void saveEvent(Event event);
	void deleteEvent(int idEvent);
	List<Event> getAllEvent();
	Event getEventByIdEvent(int idEvent);
}
