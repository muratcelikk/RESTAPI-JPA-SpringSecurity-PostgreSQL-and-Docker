package com.projem.springdatajpapostgres.repo;

import com.projem.springdatajpapostgres.entity.Adres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresRepository extends JpaRepository<Adres, Long> {
}
