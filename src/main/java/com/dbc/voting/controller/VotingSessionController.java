package com.dbc.voting.controller;

import com.dbc.voting.entity.VotingSession;
import com.dbc.voting.service.VotingSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/voting_sessions")
@Tag(name = "Sessões de votação", description = " /api/voting_sessions, utilizado para gerenciamento das sessões de votação.")
public class VotingSessionController {

    @Autowired
    private VotingSessionService votingSessionService;

    @PostMapping("/open")
    public ResponseEntity<VotingSession> openVotingSession(@RequestParam Long agendaItemId,
                                                              @RequestParam(required = false) Integer durationMinutes) {
        try {
            VotingSession session = votingSessionService.openVotingSession(agendaItemId, durationMinutes);
            return ResponseEntity.status(HttpStatus.CREATED).body(session);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}