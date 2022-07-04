package com.example.fotowoltaika.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="measurements")
public class Measurement {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="instalation_id")
    private Instalation instalation;

    @Column(name="score")
    private Double score;

    public Instalation getInstalation() {
        return instalation;
    }

    public void setInstalation(Instalation instalation) {
        this.instalation = instalation;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name="date")
    private LocalDate date;
}
