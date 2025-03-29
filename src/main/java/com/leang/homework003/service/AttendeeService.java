package com.leang.homework003.service;

import com.leang.homework003.model.entity.Attendee;
import com.leang.homework003.model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {
    Attendee getAttendeeById(Long attendeeId);

    Attendee updateAttendeeById(Long attendeeId, AttendeeRequest attendeeRequest);

    void removeAttendeeById(Long attendeeId);

    List<Attendee> getAllAttendees(Integer offset, Integer size);

    Attendee addAttendee(AttendeeRequest attendeeRequest);
}
