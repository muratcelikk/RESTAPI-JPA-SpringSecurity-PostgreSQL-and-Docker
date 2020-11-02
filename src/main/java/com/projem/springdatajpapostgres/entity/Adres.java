package com.projem.springdatajpapostgres.entity;

import lombok.*;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Entity
@Table(name = "kisi_adres")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Adres implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 500, name = "adres")
    private String adres;

    @Enumerated
    private AdresTipi adresTipi;

    @Column(name = "aktif")
    private Boolean aktif;

    public enum AdresTipi {
        EV_ADRESI,
        IS_ADRESI,
        DIGER
    }

    @ManyToOne
    private Kisi kisi;
    }
