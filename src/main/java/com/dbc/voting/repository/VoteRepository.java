package com.dbc.voting.repository;

import com.dbc.voting.entity.AgendaItem;
import com.dbc.voting.entity.Member;
import com.dbc.voting.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByMemberAndAgendaItem(Member member, AgendaItem agendaItem);

    List<Vote> findByAgendaItemId(Long agendaItemId);
}
