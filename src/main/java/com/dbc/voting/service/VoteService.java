package com.dbc.voting.service;

import com.dbc.voting.dto.VoteDTO;
import com.dbc.voting.entity.AgendaItem;
import com.dbc.voting.entity.Member;
import com.dbc.voting.entity.Vote;
import com.dbc.voting.enums.VoteValue;
import com.dbc.voting.repository.AgendaItemRepository;
import com.dbc.voting.repository.MemberRepository;
import com.dbc.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    @Autowired
    private VotingSessionService votingSessionService;

    public VoteDTO castVote(Long memberId, Long agendaItemId, VoteValue voteValue) {

        if (!votingSessionService.checkIfSessionIsOpenForAgendaItem(agendaItemId)) {
            throw new IllegalStateException("Voting session is closed");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Member not found with id: " + memberId));
        AgendaItem agendaItem = agendaItemRepository.findById(agendaItemId)
                .orElseThrow(() -> new NoSuchElementException("AgendaItem not found with id: " + agendaItemId));

        if (voteRepository.existsByMemberAndAgendaItem(member, agendaItem)) {
            throw new IllegalStateException("Member has already voted on this agenda item");
        }

        Vote vote = new Vote();
        vote.setMember(member);
        vote.setAgendaItem(agendaItem);
        vote.setVoteValue(voteValue);

        voteRepository.save(vote);

        return new VoteDTO(vote.getId(), vote.getMember(), vote.getAgendaItem(), vote.getVoteValue());
    }
}