package com.leang.homework003.repository;

import com.leang.homework003.model.entity.Event;
import com.leang.homework003.model.request.EventRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {
    @Results(id = "eventMapper", value = {@Result(property = "eventId", column = "event_id"), @Result(property = "eventName", column = "event_name"), @Result(property = "eventDate", column = "event_date"), @Result(property = "venue", column = "venue_id", one = @One(select = "com.leang.homework003.repository.VenueRepository.getVenueById")), @Result(property = "attendees", column = "event_id", many = @Many(select = "com.leang.homework003.repository.EventAttendeeRepository.getEventAttendeesByEventId"))})
    @Select("""
                select * from events where event_id=#{eventId}
            """)
    Event getEventById(Long eventId);

    @ResultMap("eventMapper")
    @Select("""
                select * from events offset #{offset} limit #{size};
            """)
    List<Event> getAllEvent(Integer offset, Integer size);

    @Delete("""
                delete from events where event_id=#{eventId}
            """)
    void removeEventById(Long eventId);

    @ResultMap("eventMapper")
    @Select("""
             insert into events values(default,#{request.eventName},#{request.eventDate},#{request.venueId}) returning *;
            """)
    Event createEvent(@Param("request") EventRequest eventRequest);

    @ResultMap("eventMapper")
    @Select("""
             update events set event_name=#{request.eventName}, event_date=#{request.eventDate},venue_id=#{request.venueId} where event_id=#{eventId} returning *;
            """)
    Event updateEvent(Long eventId, @Param("request") EventRequest eventRequest);
}
