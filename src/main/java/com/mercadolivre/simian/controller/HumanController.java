package com.mercadolivre.simian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.simian.domain.dto.HumanDTO;
import com.mercadolivre.simian.domain.dto.StatsDTO;
import com.mercadolivre.simian.exception.NotSimianException;
import com.mercadolivre.simian.exception.NotValidDNAException;
import com.mercadolivre.simian.service.HumanService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class HumanController {

    @Autowired
    private HumanService humanService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It's a simian"),
            @ApiResponse(responseCode = "403", description = "Not a simian", content = @Content(examples = @ExampleObject(), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Not valid DNA", content = @Content(examples = @ExampleObject(), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping(name = "simian", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HumanDTO> create(@RequestBody HumanDTO newHumanDTO) throws NotSimianException, NotValidDNAException {
        return ResponseEntity.ok(this.humanService.save(newHumanDTO));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stats of DNA: mutant, human and ratio mutant/human"),
            @ApiResponse(responseCode = "403", description = "Not a simian", content = @Content(examples = @ExampleObject(), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Not valid DNA", content = @Content(examples = @ExampleObject(), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping(name="stats" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatsDTO> stats() {
        return ResponseEntity.ok(humanService.getStats());
    }
}
