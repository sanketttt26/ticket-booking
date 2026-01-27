package com.sanketttt26.tickets.services.impl;

import com.sanketttt26.tickets.domain.CreateEventRequest;
import com.sanketttt26.tickets.domain.entities.Event;
import com.sanketttt26.tickets.domain.entities.TicketType;
import com.sanketttt26.tickets.domain.entities.User;
import com.sanketttt26.tickets.exceptions.UserNotFoundException;
import com.sanketttt26.tickets.repository.EventRepository;
import com.sanketttt26.tickets.repository.UserRepository;
import com.sanketttt26.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer = userRepository.findById(organizerId).orElseThrow(()-> new UserNotFoundException("User with id "+organizerId+" not found"));
        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream().map(
                ticketTypeRequest -> {
                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketTypeRequest.getName());
                    ticketTypeToCreate.setDescription(ticketTypeRequest.getDescription());
                    ticketTypeToCreate.setPrice(ticketTypeRequest.getPrice());
                    ticketTypeToCreate.setTotalAvailable(ticketTypeRequest.getTotalAvailable());
                    return ticketTypeToCreate;
                }
        ).toList();
        Event eventToCreate = new Event();

        eventToCreate.setName(event.getName());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }
}
