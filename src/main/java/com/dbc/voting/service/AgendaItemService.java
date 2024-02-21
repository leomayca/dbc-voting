package com.dbc.voting.service;

import com.dbc.voting.dto.AgendaItemDTO;
import com.dbc.voting.entity.AgendaItem;
import com.dbc.voting.repository.AgendaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaItemService {

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    public void createAgendaItem(AgendaItemDTO agendaItem) {
        AgendaItem agendaItemEntity = new AgendaItem();
        agendaItemEntity.setDetails(agendaItem.getDetails());
        agendaItemEntity.setTitle(agendaItem.getTitle());
        agendaItemRepository.save(agendaItemEntity);
    }

    public void deleteAgendaItem(int agendaItemId) {
        agendaItemRepository.deleteById(agendaItemId);
    }

    public void updateAgendaItem(Long agendaItemId, AgendaItemDTO agendaItem) {
        AgendaItem existingAgendaItem = agendaItemRepository.findById(agendaItemId).orElseThrow();

        existingAgendaItem.setTitle(agendaItem.getTitle());
        existingAgendaItem.setDetails(agendaItem.getDetails());

        agendaItemRepository.save(existingAgendaItem);
    }

    public AgendaItemDTO getAgendaItem(Long agendaItemId) {
        AgendaItem agendaItem = agendaItemRepository.findById(agendaItemId).orElseThrow();
        return new AgendaItemDTO(agendaItem.getId(), agendaItem.getTitle(), agendaItem.getDetails());
    }

    public List<AgendaItemDTO> getAgendaItems() {
        List<AgendaItem> agendaItemList = agendaItemRepository.findAll();

        return agendaItemList.stream()
                .map(agendaItem -> new AgendaItemDTO(agendaItem.getId(), agendaItem.getTitle(), agendaItem.getDetails()))
                .collect(Collectors.toList());
    }

    public boolean existsById(Long id) {
        return agendaItemRepository.existsById(id);
    }
}