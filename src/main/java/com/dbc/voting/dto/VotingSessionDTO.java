package com.dbc.voting.dto;

import com.dbc.voting.entity.AgendaItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotingSessionDTO {
    private int id;
    private AgendaItem agendaItem;
    private LocalDateTime startTime;
    private int duration;
    private boolean isOpen;
}
