package com.project.Ambulance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Ambulance.model.Event;
import com.project.Ambulance.repository.EventRepository;

@Service
public class EventServiceImpl  implements EventService{

	@Autowired
	private EventRepository repo;
	
	@Override
	public void saveEvent(Event event) {
		repo.save(event);
	}

	@Override
	public void deleteEvent(int idEvent) {
		repo.deleteById(idEvent);
	}

	@Override
	public List<Event> getAllEvent() {
		
		return repo.findAll();
	}

	@Override
	public Event getEventByIdEvent(int idEvent) {
		
		return repo.findById(idEvent).get();
	}

	
}
