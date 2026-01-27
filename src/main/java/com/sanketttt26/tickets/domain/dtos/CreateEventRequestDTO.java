package com.sanketttt26.tickets.domain.dtos;

import com.sanketttt26.tickets.domain.CreateTicketTypeRequest;
import com.sanketttt26.tickets.domain.entities.EventStatusEnum;
import com.sanketttt26.tickets.domain.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequestDTO {

    @NotBlank(message = "Event name cannot be blank")
    private String name;

    private LocalDateTime start;

    private LocalDateTime end;

    @NotBlank(message = "Venue cannot be blank")
    private String venue;

    private LocalDateTime salesStart;

    private LocalDateTime salesEnd;

    @NotNull(message = "Event status cannot be null")
    private EventStatusEnum status;

    @NotNull(message = "At least one ticket type must be provided")
    @Valid
    private List<CreateTicketTypeRequestDTO> ticketTypes;
}
