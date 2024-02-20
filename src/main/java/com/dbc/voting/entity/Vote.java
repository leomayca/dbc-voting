package com.dbc.voting.entity;

import com.dbc.voting.enums.VoteValue;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private AgendaItem agendaItem;

    @Enumerated(EnumType.STRING)
    private VoteValue voteValue;

}