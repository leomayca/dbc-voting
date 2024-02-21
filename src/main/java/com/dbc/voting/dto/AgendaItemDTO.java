package com.dbc.voting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaItemDTO {
    private int id;
    private String title;
    private String details;
}
