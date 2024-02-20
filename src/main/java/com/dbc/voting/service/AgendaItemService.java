package com.dbc.voting.service;

import com.dbc.voting.entity.AgendaItem;
import com.dbc.voting.repository.AgendaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaItemService {

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    public AgendaItem createAgendaItem(AgendaItem agendaItem) {
        return agendaItemRepository.save(agendaItem);
    }

    public void deleteAgendaItem(int agendaItemId) {
        agendaItemRepository.deleteById(agendaItemId);
    }

    public AgendaItem updateAgendaItem(int agendaItemId, AgendaItem agendaItem) {
        return agendaItemRepository.save(agendaItem);
    }

    public AgendaItem getAgendaItem(Long agendaItemId) {
        return agendaItemRepository.findById(agendaItemId).orElseThrow();
    }

    public List<AgendaItem> getAgendaItems() {
        return agendaItemRepository.findAll();
    }

    public boolean existsById(Long id) {
        return agendaItemRepository.existsById(id);
    }
}