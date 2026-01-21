package com.sanketttt26.tickets.services;

import com.sanketttt26.tickets.domain.CreateEventRequest;
import com.sanketttt26.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
}
