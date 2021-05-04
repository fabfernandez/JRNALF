package com.mercadolibre.finalchallengedemo.controller;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.service.PartServiceImpl;
import com.mercadolibre.finalchallengedemo.util.ValidatorUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    private PartServiceImpl partService;

    public PartController(PartServiceImpl partService) {
        this.partService = partService;
    }

    @GetMapping("/list")
    public ResponseEntity getParts(PartFilterDTO filter) {
        ValidatorUtil.validatePartFilter(filter);

        if(filter.hasFilters())
            return ResponseEntity.ok(this.partService.getPartsByFilter(filter));

        return ResponseEntity.ok(this.partService.getAll());
    }
}
