package com.leang.homework003.repository;

import com.leang.homework003.model.entity.Attendee;
import com.leang.homework003.model.request.AttendeeRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
    })
    @Select(
            """
                        select * from attendees where attendee_id=#{attendeeId}
                    """
    )
    Attendee getAttendeeById(Long attendeeId);

    @ResultMap("attendeeMapper")
    @Select(
            """
                        update attendees set attendee_name=#{request.attendeeName},email=#{request.email} where attendee_id=#{attendeeId} returning *;
                    """
    )
    Attendee updateAttendeeById(Long attendeeId, @Param("request") AttendeeRequest venueRequest);

    @ResultMap("attendeeMapper")
    @Select(
            """
                        delete from attendees where attendee_id=#{attendeeId}
                    """
    )
    void removeAttendeeById(Long attendeeId);

    @ResultMap("attendeeMapper")
    @Select(
            """
                        select * from attendees offset #{offset} limit #{size};
                    """
    )
    List<Attendee> getAllAttendees(Integer offset, Integer size);

    @ResultMap("attendeeMapper")
    @Select(
            """
                          insert into attendees values (default,#{request.attendeeName},#{request.email}) returning *;
                    """
    )
    Attendee addAttendee(@Param("request") AttendeeRequest venueRequest);
}
