package com.leang.homework003.service;

import com.leang.homework003.model.entity.Event;
import com.leang.homework003.model.request.EventRequest;

import java.util.List;

public interface EventService {
    Event getEventById(Long eventId);

    Event updateEventById(Long eventId, EventRequest eventRequest);

    void removeEventById(Long eventId);

    List<Event> getAllEvents(Integer offset, Integer size);

    Event addEvent(EventRequest eventRequest);
}
