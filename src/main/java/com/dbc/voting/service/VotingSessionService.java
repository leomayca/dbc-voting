package com.dbc.voting.service;

import com.dbc.voting.dto.VotingSessionDTO;
import com.dbc.voting.entity.AgendaItem;
import com.dbc.voting.entity.VotingSession;
import com.dbc.voting.repository.AgendaItemRepository;
import com.dbc.voting.repository.VotingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class VotingSessionService {

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    public VotingSessionDTO openVotingSession(Long agendaItemId, int durationMinutes) {
        AgendaItem agendaItem = agendaItemRepository.findById(agendaItemId)
                .orElseThrow(() -> new NoSuchElementException("AgendaItem not found with id: " + agendaItemId));

        if (!Objects.isNull(agendaItem.getVotingSession()) && isSessionOpen(agendaItem.getVotingSession())) {
            throw new IllegalStateException("A voting session is already open for this agenda item");
        }

        if (!Objects.isNull(agendaItem.getVotingSession()) && agendaItem.getVotingSession().isClosed()) {
            throw new IllegalStateException("Voting session for this agenda item is already closed");
        }

        VotingSession votingSession = new VotingSession();
        votingSession.setAgendaItem(agendaItem);
        votingSession.setStartTime(LocalDateTime.now());
        votingSession.setDuration(durationMinutes > 0 ? durationMinutes : 1);

        votingSessionRepository.save(votingSession);

        return new VotingSessionDTO(votingSession.getId(), votingSession.getAgendaItem(), votingSession.getStartTime(), votingSession.getDuration(), false);
    }

    private boolean isSessionOpen(VotingSession votingSession) {
        LocalDateTime sessionEndTime = votingSession.getStartTime().plusMinutes(votingSession.getDuration());
        return LocalDateTime.now().isBefore(sessionEndTime);
    }

    public void checkIfSessionIsOpenForAgendaItem(Long agendaItemId) {
        AgendaItem agendaItem = agendaItemRepository.findById(agendaItemId)
                .orElseThrow(() -> new NoSuchElementException("AgendaItem not found with id: " + agendaItemId));

        VotingSession votingSession = agendaItem.getVotingSession();
        if (votingSession == null) {
            throw new IllegalStateException("No voting session exists for this agenda item");
        }

        if (!isSessionOpen(votingSession)) {
            throw new IllegalStateException("Voting session is closed");
        }
    }
}