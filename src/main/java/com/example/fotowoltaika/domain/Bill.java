package com.example.fotowoltaika.domain;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="bill")
public class Bill {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="payment")
    private Double payment;

    @Column(name="energy")
    private Double energy;

    @Column(name="date")
    private LocalDate date;

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Double getUsage() {
        return energy;
    }

    public void setUsage(Double energy) {
        this.energy = energy;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade =
            {CascadeType.
                    DETACH,CascadeType.MERGE,CascadeType.
                    REFRESH})
    @JoinColumn(name="user_id")
    @RestResource
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
