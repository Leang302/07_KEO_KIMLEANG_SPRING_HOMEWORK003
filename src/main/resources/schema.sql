create table if not exists venues
(
    venue_id   serial primary key,
    venue_name varchar(50) not null,
    location   varchar(255) not null
);
create table if not exists attendees
(
    attendee_id   serial primary key,
    attendee_name varchar(50)  not null,
    email         varchar(255) not null
);
create table if not exists events
(
    event_id   serial primary key,
    event_name varchar(50) not null,
    event_date timestamptz default now(),
    venue_id   int         not null,
    constraint fk_venue foreign key (venue_id) references venues (venue_id) on update cascade on delete cascade
);
create table if not exists event_attendee
(
    event_id    int,
    attendee_id int,
    primary key (event_id, attendee_id),
    constraint fk_event foreign key (event_id) references events (event_id) on update cascade on delete cascade,
    constraint fk_attendee foreign key (attendee_id) references attendees (attendee_id) on update cascade on delete cascade
)
