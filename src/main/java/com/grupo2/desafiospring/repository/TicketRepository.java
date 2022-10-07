package com.grupo2.desafiospring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.grupo2.desafiospring.dto.TicketDto;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TicketRepository {
    private final String PATH_NAME = "src/main/resources/data/tickets.json";


    private final ObjectMapper objectMapper;

    public TicketRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<TicketDto> getAllTickets() throws IOException {
        return Arrays.asList(objectMapper.readValue(new File(PATH_NAME), TicketDto[].class));
    }
    public TicketDto addTicket(TicketDto ticket) throws IOException {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        List<TicketDto> ticketList = new ArrayList<>(getAllTickets());

        ticketList.add(ticket);
        writer.writeValue(new File(PATH_NAME), ticketList);

        return ticket;
    }
}
