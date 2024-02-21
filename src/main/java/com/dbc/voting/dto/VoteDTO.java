package com.dbc.voting.dto;

import com.dbc.voting.entity.AgendaItem;
import com.dbc.voting.entity.Member;
import com.dbc.voting.enums.VoteValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {
    private int id;
    private Member member;
    private AgendaItem agendaItem;
    private VoteValue voteValue;
}
