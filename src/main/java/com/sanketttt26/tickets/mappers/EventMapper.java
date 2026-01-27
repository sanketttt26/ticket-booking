package com.sanketttt26.tickets.mappers;

import com.sanketttt26.tickets.domain.CreateEventRequest;
import com.sanketttt26.tickets.domain.CreateTicketTypeRequest;
import com.sanketttt26.tickets.domain.dtos.CreateEventRequestDTO;
import com.sanketttt26.tickets.domain.dtos.CreateEventResponseDTO;
import com.sanketttt26.tickets.domain.dtos.CreateTicketTypeRequestDTO;
import com.sanketttt26.tickets.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDTO dto);

    CreateEventRequest fromDto(CreateEventRequestDTO dto);

    CreateEventResponseDTO toDto(Event event);

}
