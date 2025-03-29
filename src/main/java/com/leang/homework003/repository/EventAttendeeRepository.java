package com.leang.homework003.repository;

import com.leang.homework003.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventAttendeeRepository {
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
    })
    @Select("""
                    select * from event_attendee ea inner join attendees a on ea.attendee_id=a.attendee_id where event_id=#{eventId};
            """)
    List<Attendee> getEventAttendeesByEventId(Long eventId);

    @Insert("""
            insert into event_attendee values(#{eventId},#{attendeeId});
            """)
    void addEventAttendee(Long eventId, Long attendeeId);

    @Delete("""
            delete from event_attendee where event_id=#{eventId};
            """)
    void deleteEventAttendeeByEventId(Long eventId);
}
