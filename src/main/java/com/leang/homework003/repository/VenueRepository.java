package com.leang.homework003.repository;

import com.leang.homework003.model.entity.Venue;
import com.leang.homework003.model.request.VenueRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })
    @Select(
            """
                        select * from venues where venue_id=#{venueId}
                    """
    )
    Venue getVenueById(Long venueId);

    @ResultMap("venueMapper")
    @Select(
            """
                        update venues set venue_name=#{request.venueName},location=#{request.location} where venue_id=#{venueId} returning *;
                    """
    )
    Venue updateVenueById(Long venueId, @Param("request") VenueRequest venueRequest);

    @ResultMap("venueMapper")
    @Select(
            """
                        delete from venues where venue_id=#{venueId}
                    """
    )
    void removeVenueById(Long venueId);

    @ResultMap("venueMapper")
    @Select(
            """
                        select * from venues offset #{offset} limit #{size};
                    """
    )
    List<Venue> getAllVenues(Integer offset, Integer size);

    @ResultMap("venueMapper")
    @Select(
            """
                          insert into venues values (default,#{request.venueName},#{request.location}) returning *;
                    """
    )
    Venue addVenue(@Param("request") VenueRequest venueRequest);
}
