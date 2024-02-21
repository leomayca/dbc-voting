package com.dbc.voting.service;

import com.dbc.voting.dto.VotingResultDTO;
import com.dbc.voting.entity.AgendaItem;
import com.dbc.voting.entity.Vote;
import com.dbc.voting.entity.VotingSession;
import com.dbc.voting.enums.VoteValue;
import com.dbc.voting.repository.AgendaItemRepository;
import com.dbc.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VotingResultService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    public VotingResultDTO getVotingResults(Long agendaItemId) {
        AgendaItem agendaItem = getAgendaItem(agendaItemId);

        VotingSession votingSession = agendaItem.getVotingSession();
        if (votingSession == null) {
            throw new IllegalStateException("No voting session exists for this agenda item");
        }

        List<Vote> votes = voteRepository.findByAgendaItemId(agendaItemId);

        VoteValue result = calculateVotingResult(votes);

        int countYes = (int) votes.stream().filter(vote -> vote.getVoteValue() == VoteValue.SIM).count();
        int countNo = votes.size() - countYes;

        if (!isSessionOpen(agendaItem.getVotingSession())) {
            setAgendaItemResult(agendaItem);
        }

        return new VotingResultDTO(agendaItemId, agendaItem.getTitle(), countYes, countNo, result);
    }

    public void setAgendaItemResult(AgendaItem agendaItem) {
        List<Vote> votes = voteRepository.findByAgendaItemId((long) agendaItem.getId());

        VoteValue result = calculateVotingResult(votes);
        agendaItem.setVotingResult(result.toString());
        agendaItem.getVotingSession().setClosed(true);
        agendaItemRepository.save(agendaItem);
    }

    private AgendaItem getAgendaItem(Long agendaItemId) {
        return agendaItemRepository.findById(agendaItemId)
                .orElseThrow(() -> new NoSuchElementException("AgendaItem not found with id: " + agendaItemId));
    }

    private VoteValue calculateVotingResult(List<Vote> votes) {
        int countYes = (int) votes.stream().filter(vote -> vote.getVoteValue() == VoteValue.SIM).count();
        return countYes > (votes.size() - countYes) ? VoteValue.SIM : VoteValue.NAO;
    }

    private boolean isSessionOpen(VotingSession votingSession) {
        LocalDateTime sessionEndTime = votingSession.getStartTime().plusMinutes(votingSession.getDuration());
        return LocalDateTime.now().isBefore(sessionEndTime);
    }
}