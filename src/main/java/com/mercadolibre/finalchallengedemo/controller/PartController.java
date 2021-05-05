package com.mercadolibre.finalchallengedemo.controller;

import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.service.PartServiceImpl;
import com.mercadolibre.finalchallengedemo.util.ValidatorUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    private PartServiceImpl partService;

    public PartController(PartServiceImpl partService) {
        this.partService = partService;
    }

    @GetMapping("/list")
    public ResponseEntity getParts(PartFilterDTO filter) {

        if(filter.hasFilters()) {
            ValidatorUtil.validatePartFilter(filter);
            return ResponseEntity.ok(this.partService.getPartsByFilter(filter));
        }

        return ResponseEntity.ok(this.partService.getAll());
    }

    @PostMapping("/save")
    private ResponseEntity savePart(@RequestBody PartDTO part){
        partService.savePart(part);
        return ResponseEntity.ok("Part saved.");
    }

    @GetMapping("/find/{id}")
    private ResponseEntity findPart(@PathVariable Long id){
        return ResponseEntity.ok(partService.findPart(id));
    }

    @PutMapping("/update")
    private ResponseEntity<String> update(@RequestBody PartDTO part){
        partService.savePart(part);
        return ResponseEntity.ok("Part updated.");
    }


    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteStudent(@PathVariable Long id){
        partService.deletePart(id);
        return ResponseEntity.ok("Eliminó el estudienta");
    }

}
