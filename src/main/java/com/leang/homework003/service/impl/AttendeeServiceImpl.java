package com.leang.homework003.service.impl;

import com.leang.homework003.exception.NotFoundException;
import com.leang.homework003.model.entity.Attendee;
import com.leang.homework003.model.request.AttendeeRequest;
import com.leang.homework003.repository.AttendeeRepository;
import com.leang.homework003.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    @Override
    public Attendee getAttendeeById(Long attendeeId) {
        Attendee attendeeById = attendeeRepository.getAttendeeById(attendeeId);
        if (attendeeById == null) {
            throw new NotFoundException("Attendee with id " + attendeeId + " not found");
        }
        return attendeeById;
    }

    @Override
    public Attendee updateAttendeeById(Long attendeeId, AttendeeRequest attendeeRequest) {
        getAttendeeById(attendeeId);
        return attendeeRepository.updateAttendeeById(attendeeId, attendeeRequest);
    }

    @Override
    public void removeAttendeeById(Long attendeeId) {
        getAttendeeById(attendeeId);
        attendeeRepository.removeAttendeeById(attendeeId);
    }

    @Override
    public List<Attendee> getAllAttendees(Integer offset, Integer size) {
        offset = (offset - 1) * size;
        return attendeeRepository.getAllAttendees(offset, size);
    }

    @Override
    public Attendee addAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.addAttendee(attendeeRequest);
    }
}
