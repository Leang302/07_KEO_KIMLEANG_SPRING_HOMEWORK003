package com.leang.homework003.service.impl;

import com.leang.homework003.exception.NotFoundException;
import com.leang.homework003.model.entity.Event;
import com.leang.homework003.model.request.EventRequest;
import com.leang.homework003.repository.EventAttendeeRepository;
import com.leang.homework003.repository.EventRepository;
import com.leang.homework003.service.AttendeeService;
import com.leang.homework003.service.EventService;
import com.leang.homework003.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;
    private final AttendeeService attendeeService;
    private final VenueService venueService;

    @Override
    public Event getEventById(Long eventId) {
        Event eventById = eventRepository.getEventById(eventId);
        if (eventById == null) {
            throw new NotFoundException("Event with id " + eventId + " not found");
        }
        return eventById;
    }

    @Override
    public Event updateEventById(Long eventId, EventRequest eventRequest) {
        getEventById(eventId);
        isValidVenueAndAttendees(eventRequest.getVenueId(), eventRequest.getAttendeesId());

        //remove old records
        eventAttendeeRepository.deleteEventAttendeeByEventId(eventId);
        //update event info
        eventRepository.updateEvent(eventId,eventRequest);
        //add new attendee list
        for (Long attendeeId : eventRequest.getAttendeesId()) {
            eventAttendeeRepository.addEventAttendee(eventId, attendeeId);
        }
        return eventRepository.getEventById(eventId);
    }

    @Override
    public void removeEventById(Long eventId) {
        getEventById(eventId);
        eventRepository.removeEventById(eventId);
    }

    @Override
    public List<Event> getAllEvents(Integer offset, Integer size) {
        offset = (offset - 1) * size;
        return eventRepository.getAllEvent(offset, size);
    }

    @Override
    public Event addEvent(EventRequest eventRequest) {
        isValidVenueAndAttendees(eventRequest.getVenueId(), eventRequest.getAttendeesId());
        Event event = eventRepository.createEvent(eventRequest);
        for (Long attendeeId : eventRequest.getAttendeesId()) {
            eventAttendeeRepository.addEventAttendee(event.getEventId(), attendeeId);
        }
        return eventRepository.getEventById(event.getEventId());
    }

    private void isValidVenueAndAttendees(Long venueId, List<Long> attendeesIds) {
        venueService.getVenueById(venueId);
        for (Long attendeeId : attendeesIds) {
            attendeeService.getAttendeeById(attendeeId);
        }
    }
}
