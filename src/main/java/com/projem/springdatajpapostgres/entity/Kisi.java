package com.projem.springdatajpapostgres.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "kisi")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Kisi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, name = "adi")
    private String adi;

    @Column(length = 100, name = "soyadi")
    private String soyadi;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="kisi_adres_id", nullable = false)
    private Set<Adres> adresler;

    }
