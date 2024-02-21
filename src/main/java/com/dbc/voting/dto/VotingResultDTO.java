package com.dbc.voting.dto;

import com.dbc.voting.enums.VoteValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotingResultDTO {
    private Long agendaItemId;
    private String agendaItemTitle;
    private int votesYes;
    private int votesNo;
    private VoteValue result;
}