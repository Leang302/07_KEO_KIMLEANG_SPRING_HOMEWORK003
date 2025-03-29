package com.leang.homework003.service.impl;

import com.leang.homework003.exception.NotFoundException;
import com.leang.homework003.model.entity.Venue;
import com.leang.homework003.model.request.VenueRequest;
import com.leang.homework003.repository.VenueRepository;
import com.leang.homework003.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    @Override
    public Venue addVenue(VenueRequest venueRequest) {
        return venueRepository.addVenue(venueRequest);
    }

    @Override
    public List<Venue> getAllVenues(Integer offset, Integer size) {
        offset = (offset - 1) * size;
        return venueRepository.getAllVenues(offset, size);
    }

    @Override
    public void removeVenueById(Long venueId) {
        getVenueById(venueId);
        venueRepository.removeVenueById(venueId);
    }

    @Override
    public Venue updateVenueById(Long venueId, VenueRequest venueRequest) {
        getVenueById(venueId);
        return venueRepository.updateVenueById(venueId, venueRequest);
    }

    @Override
    public Venue getVenueById(Long venueId) {
        Venue venueById = venueRepository.getVenueById(venueId);
        if (venueById == null) {
            throw new NotFoundException("Venue with id " + venueId + " not found");
        }
        return venueById;
    }
}
