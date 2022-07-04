
package com.example.fotowoltaika.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="monthlymeasurement")
public class MonthlyMeasurement {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date")
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMonthlysum() {
        return monthlysum;
    }

    public void setMonthlysum(Double monthlysum) {
        this.monthlysum = monthlysum;
    }

    public Instalation getInstalation() {
        return instalation;
    }

    public void setInstalation(Instalation instalation) {
        this.instalation = instalation;
    }

    @Column(name="monthlysum")
    private Double monthlysum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="instalation_id")
    private Instalation instalation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

