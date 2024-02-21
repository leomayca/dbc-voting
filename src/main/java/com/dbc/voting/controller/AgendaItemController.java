package com.dbc.voting.controller;

import com.dbc.voting.dto.AgendaItemDTO;
import com.dbc.voting.entity.AgendaItem;
import com.dbc.voting.service.AgendaItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/agenda_items")
@Tag(name = "Pautas", description = " /api/agenda_items, utilizado para gerenciamento de pautas.")
public class AgendaItemController {

    @Autowired
    AgendaItemService agendaItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAgendaItem(@RequestBody AgendaItemDTO agendaItem) {
        agendaItemService.createAgendaItem(agendaItem);
    }

    @GetMapping
    public ResponseEntity<List<AgendaItemDTO>> getAllAgendaItems() {
        List<AgendaItemDTO> agendaItems = agendaItemService.getAgendaItems();
        return ResponseEntity.ok(agendaItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaItemDTO> getAgendaItemById(@PathVariable Long id) {
        AgendaItemDTO agendaItem = agendaItemService.getAgendaItem(id);
        return ResponseEntity.ok(agendaItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaItem> updateAgendaItem(@PathVariable Long id, @RequestBody AgendaItemDTO agendaItemDetails) {
        agendaItemService.updateAgendaItem(id, agendaItemDetails);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendaItem(@PathVariable Long id) {
        agendaItemService.deleteAgendaItem(id);
        return ResponseEntity.ok().build();
    }
}
