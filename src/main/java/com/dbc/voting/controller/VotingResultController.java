package com.dbc.voting.controller;

import com.dbc.voting.dto.VotingResultDTO;
import com.dbc.voting.service.VotingResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/voting_results")
@Tag(name = "Resultado de votação", description = " /api/voting_results, utilizado para gerenciamento do resultado de votação.")
public class VotingResultController {

    @Autowired
    private VotingResultService votingResultService;

    @GetMapping("/{agendaItemId}")
    public ResponseEntity<VotingResultDTO> getVotingResults(@PathVariable Long agendaItemId) {
        VotingResultDTO result = votingResultService.getVotingResults(agendaItemId);
        return ResponseEntity.ok(result);
    }
}