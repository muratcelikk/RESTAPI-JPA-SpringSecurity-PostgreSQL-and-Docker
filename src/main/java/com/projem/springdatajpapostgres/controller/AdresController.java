package com.projem.springdatajpapostgres.controller;

import com.projem.springdatajpapostgres.entity.Adres;
import com.projem.springdatajpapostgres.exception.ResourceNotFoundException;
import com.projem.springdatajpapostgres.repo.AdresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/m2")
@RequiredArgsConstructor
public class AdresController {

    private final AdresRepository adresRepository;

    @GetMapping("/adres")
    public List<Adres> getAllAdres() {
        return adresRepository.findAll();
    }

    @GetMapping("/adres/{id}")
    public ResponseEntity<Adres> getAdresById(@PathVariable(value = "id") long AdresId) throws ResourceNotFoundException {
        Adres adres = adresRepository.findById(AdresId)
                .orElseThrow(() -> new ResourceNotFoundException("Adres not found for this id :: " + AdresId));
        return ResponseEntity.ok().body(adres);
    }

    @DeleteMapping("/adres/{id}")
    public Map<String, Boolean> deleteAdres(@PathVariable(value = "id") Long adresId) throws ResourceNotFoundException {
        Adres adres = adresRepository.findById(adresId)
                .orElseThrow(() -> new ResourceNotFoundException("Adres not found for this id :: " + adresId));

        adresRepository.delete(adres);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/adres")
    public Adres createAdres(@Valid @RequestBody Adres adres) {
        return adresRepository.save(adres);
    }

    @PutMapping("/adres/{id}")
    public ResponseEntity<Adres> updateAdres(@PathVariable(value = "id") Long adresId,
                                            @Valid @RequestBody Adres adresDetails) throws ResourceNotFoundException {
        Adres adres = adresRepository.findById(adresId)
                .orElseThrow(() -> new ResourceNotFoundException("Adres not found for this id :: " + adresId));

        adres.setId(adresDetails.getId());
        adres.setAdres(adresDetails.getAdres());
        adres.setAdresTipi(adresDetails.getAdresTipi());
        adres.setAktif(adresDetails.getAktif());
        final Adres updatedAdres = adresRepository.save(adres);
        return ResponseEntity.ok(updatedAdres);
    }
}
