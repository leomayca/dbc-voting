package com.dbc.voting.dto;

import com.dbc.voting.entity.VotingSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaItemDTO {
    private int id;
    private String title;
    private String details;
    private String votingResult;
    private VotingSession votingSession;
}
