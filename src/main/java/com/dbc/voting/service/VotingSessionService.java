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

@Service
public class VotingSessionService {

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    public VotingSession openVotingSession(Long agendaItemId, int durationMinutes) {
        AgendaItem agendaItem = agendaItemRepository.findById(agendaItemId)
                .orElseThrow(() -> new NoSuchElementException("AgendaItem not found with id: " + agendaItemId));

        VotingSession votingSession = new VotingSession();
        votingSession.setAgendaItem(agendaItem);
        votingSession.setStartTime(LocalDateTime.now());
        votingSession.setDuration(durationMinutes > 0 ? durationMinutes : 1);

        return votingSessionRepository.save(votingSession);
    }

    public boolean isSessionOpen(Long votingSessionId) {
        VotingSession votingSession = votingSessionRepository.findById(votingSessionId)
                .orElseThrow(() -> new NoSuchElementException("VotingSession not found with id: " + votingSessionId));

        LocalDateTime sessionEndTime = votingSession.getStartTime().plusMinutes(votingSession.getDuration());
        return LocalDateTime.now().isBefore(sessionEndTime);
    }
}