-- Insert data into venues
INSERT INTO venues (venue_name, location) VALUES
                                              ('Grand Hall', 'New York'),
                                              ('Sunset Arena', 'Los Angeles'),
                                              ('Downtown Theater', 'Chicago'),
                                              ('Skyline Pavilion', 'San Francisco'),
                                              ('Metro Center', 'Houston');

-- Insert data into attendees
INSERT INTO attendees (attendee_name, email) VALUES
                                                 ('Alice Johnson', 'alice.johnson@example.com'),
                                                 ('Bob Smith', 'bob.smith@example.com'),
                                                 ('Charlie Brown', 'charlie.brown@example.com'),
                                                 ('Diana Ross', 'diana.ross@example.com'),
                                                 ('Ethan Hunt', 'ethan.hunt@example.com');

-- Insert data into events
INSERT INTO events (event_name, event_date, venue_id) VALUES
                                                          ('Tech Conference 2025', '2025-06-15 10:00:00', 1),
                                                          ('Music Festival', '2025-07-20 18:00:00', 2),
                                                          ('Business Summit', '2025-08-05 09:00:00', 3),
                                                          ('Comedy Night', '2025-09-10 20:00:00', 4),
                                                          ('Charity Gala', '2025-10-25 19:00:00', 5);

-- Insert data into event_attendee
INSERT INTO event_attendee (event_id, attendee_id) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (2, 3),
                                                       (2, 4),
                                                       (3, 5),
                                                       (3, 1),
                                                       (4, 2),
                                                       (4, 3),
                                                       (5, 4),
                                                       (5, 5);
