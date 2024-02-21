package com.dbc.voting.controller;

import com.dbc.voting.dto.VoteDTO;
import com.dbc.voting.enums.VoteValue;
import com.dbc.voting.service.VoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
@Tag(name = "Votação", description = " /api/votes, utilizado para gerenciamento de votação.")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/cast")
    public ResponseEntity<Void> castVote(@RequestParam Long memberId,
                                            @RequestParam Long agendaItemId,
                                            @RequestParam VoteValue voteValue) {
        voteService.castVote(memberId, agendaItemId, voteValue);
        return ResponseEntity.ok().build();
    }
}