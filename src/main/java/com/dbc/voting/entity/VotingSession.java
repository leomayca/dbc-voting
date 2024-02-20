package com.dbc.voting.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "voting_session")
public class VotingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private AgendaItem agendaItem;
    private LocalDateTime startTime;
    private int duration;
    private boolean isOpen;

}