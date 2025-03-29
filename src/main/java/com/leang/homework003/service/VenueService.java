package com.leang.homework003.service;

import com.leang.homework003.model.entity.Venue;
import com.leang.homework003.model.request.VenueRequest;

import java.util.List;

public interface VenueService {
    Venue getVenueById(Long venueId);

    Venue updateVenueById(Long venueId, VenueRequest venueRequest);

    void removeVenueById(Long venueId);

    List<Venue> getAllVenues(Integer offset, Integer size);

    Venue addVenue(VenueRequest venueRequest);
}
