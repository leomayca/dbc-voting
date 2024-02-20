package com.dbc.voting.controller;

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
    public ResponseEntity<AgendaItem> createAgendaItem(@RequestBody AgendaItem agendaItem) {
        try {
            AgendaItem newAgendaItem = agendaItemService.createAgendaItem(agendaItem);
            return ResponseEntity.ok(newAgendaItem);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AgendaItem>> getAllAgendaItems() {
        try {
            List<AgendaItem> agendaItems = agendaItemService.getAgendaItems();
            return ResponseEntity.ok(agendaItems);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaItem> getAgendaItemById(@PathVariable Long id) {
        try {
            AgendaItem agendaItem = agendaItemService.getAgendaItem(id);
            return ResponseEntity.ok(agendaItem);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaItem> updateAgendaItem(@PathVariable int id, @RequestBody AgendaItem agendaItemDetails) {
        try {
            AgendaItem updatedAgendaItem = agendaItemService.updateAgendaItem(id, agendaItemDetails);
            return ResponseEntity.ok(updatedAgendaItem);
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
