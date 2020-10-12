package com.projem.springdatajpapostgres.controller;

import com.projem.springdatajpapostgres.entity.Kisi;
import com.projem.springdatajpapostgres.exception.ResourceNotFoundException;
import com.projem.springdatajpapostgres.repo.KisiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/m1")
@RequiredArgsConstructor
public class KisiController {

    private final KisiRepository kisiRepository;

    @GetMapping("/kisi")
    public List<Kisi> getAllKisi() {
        return kisiRepository.findAll();
    }

    @GetMapping("/kisi/{id}")
    public ResponseEntity<Kisi> getKisiById(@PathVariable(value = "id") long KisiId) throws ResourceNotFoundException {
        Kisi kisi = kisiRepository.findById(KisiId)
                .orElseThrow(() -> new ResourceNotFoundException("Kisi not found for this id :: " + KisiId));
        return ResponseEntity.ok().body(kisi);
    }

    @DeleteMapping("/kisi/{id}")
    public Map<String, Boolean> deleteKisi(@PathVariable(value = "id") Long kisiId) throws ResourceNotFoundException {
        Kisi kisi = kisiRepository.findById(kisiId)
                .orElseThrow(() -> new ResourceNotFoundException("Kisi not found for this id :: " + kisiId));

        kisiRepository.delete(kisi);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/kisi")
    public Kisi createKisi(@Valid @RequestBody Kisi kisi) {
        return kisiRepository.save(kisi);
    }

    @PutMapping("/kisi/{id}")
    public ResponseEntity<Kisi> updateKisi(@PathVariable(value = "id") Long kisiId,
                                           @Valid @RequestBody Kisi kisiDetails) throws ResourceNotFoundException {
        Kisi kisi = kisiRepository.findById(kisiId)
                .orElseThrow(() -> new ResourceNotFoundException("Kisi not found for this id :: " + kisiId));

        kisi.setId(kisiDetails.getId());
        kisi.setAdi(kisiDetails.getAdi());
        kisi.setSoyadi(kisiDetails.getSoyadi());
      //  kisi.setAdresleri(kisiDetails.getAdresleri());
        final Kisi updatedKisi = kisiRepository.save(kisi);
        return ResponseEntity.ok(updatedKisi);
    }
}
