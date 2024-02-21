package com.dbc.voting.service;

import com.dbc.voting.dto.VotingResultDTO;
import com.dbc.voting.entity.AgendaItem;
import com.dbc.voting.entity.Vote;
import com.dbc.voting.enums.VoteValue;
import com.dbc.voting.repository.AgendaItemRepository;
import com.dbc.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VotingResultService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    public VotingResultDTO getVotingResults(Long agendaItemId) {
        AgendaItem agendaItem = agendaItemRepository.findById(agendaItemId)
                .orElseThrow(() -> new NoSuchElementException("AgendaItem not found with id: " + agendaItemId));
        List<Vote> votes = voteRepository.findByAgendaItemId(agendaItemId);

        int countYes = (int) votes.stream().filter(vote -> vote.getVoteValue() == VoteValue.SIM).count();
        int countNo = votes.size() - countYes;

        return new VotingResultDTO(agendaItemId, agendaItem.getTitle(), countYes, countNo);
    }
}