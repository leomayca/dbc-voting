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
@Tag(name = "Pautas", description = " /api/v1/agenda_items, utilizado para gerenciamento de pautas.")
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
        try {
            List<AgendaItemDTO> agendaItems = agendaItemService.getAgendaItems();
            return ResponseEntity.ok(agendaItems);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaItemDTO> getAgendaItemById(@PathVariable Long id) {
        try {
            AgendaItemDTO agendaItem = agendaItemService.getAgendaItem(id);
            return ResponseEntity.ok(agendaItem);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaItem> updateAgendaItem(@PathVariable Long id, @RequestBody AgendaItemDTO agendaItemDetails) {
        try {
            agendaItemService.updateAgendaItem(id, agendaItemDetails);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendaItem(@PathVariable int id) {
        if (!agendaItemService.existsById((long) id)) {
            return ResponseEntity.notFound().build();
        }

        try {
            agendaItemService.deleteAgendaItem(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
