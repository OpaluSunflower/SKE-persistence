package com.example.fotowoltaika.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="dailymeasurements")
public class DailyMeasurement {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date")
    private LocalDate date;

    @Column(name="dailysum")
    private double dailysum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="instalation_id")
    private Instalation instalation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getDailysum() {
        return dailysum;
    }

    public void setDailysum(double dailysum) {
        this.dailysum = dailysum;
    }

    public Instalation getInstalation() {
        return instalation;
    }

    public void setInstalation(Instalation instalation) {
        this.instalation = instalation;
    }
}
