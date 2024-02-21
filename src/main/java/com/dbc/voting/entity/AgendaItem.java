package com.dbc.voting.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "agenda_item")
public class AgendaItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String details;

    private String votingResult;

    @OneToOne(mappedBy = "agendaItem")
    private VotingSession votingSession;

}