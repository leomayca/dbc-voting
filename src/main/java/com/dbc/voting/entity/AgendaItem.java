package com.dbc.voting.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "agendaItem")
    @JsonManagedReference
    private List<VotingSession> votingSessions;

}