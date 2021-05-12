package com.mercadolibre.finalchallengedemo.controller;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import com.mercadolibre.finalchallengedemo.service.PartServiceImpl;
import com.mercadolibre.finalchallengedemo.util.ValidatorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    private PartServiceImpl partService;

    public PartController(PartServiceImpl partService) {
        this.partService = partService;
    }

    @GetMapping("/list")
    public ResponseEntity<PartResponseDTO> getParts(@Valid PartFilterDTO filter) {

        if(filter.hasFilters()) {
            ValidatorUtil.validatePartFilter(filter);
            return ResponseEntity.ok(this.partService.getPartsByFilter(filter));
        }

        return ResponseEntity.ok(partService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity savePart(@Validated @RequestBody PartDTO part){
        //This is the REQ 4 endpoint for adding or modifying parts to the database.
        if (DecodeToken.location == 1) {
            partService.savePart(part);
            return ResponseEntity.ok("Part saved.");
        }else
            return new ResponseEntity("Unauthorized.", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity findPart(@PathVariable Integer id){
        return ResponseEntity.ok(partService.findPart(id));
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody PartDTO part){
        partService.savePart(part);
        return ResponseEntity.ok("Part updated.");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePart(@PathVariable Integer id){
        partService.deletePart(id);
        return ResponseEntity.ok("Part eliminated.");
    }

}
