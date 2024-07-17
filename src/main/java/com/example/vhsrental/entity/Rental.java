package com.example.vhsrental.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    private User user;

    @ManyToOne(optional = false)
    private VHS vhs;

    @Column(nullable = false)
    private LocalDate rentalDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    private Double lateFee;

    public Rental(Long id, User user, VHS vhs, LocalDate rentalDate, LocalDate dueDate, Double lateFee) {
        this.id = id;
        this.user = user;
        this.vhs = vhs;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
        this.lateFee = lateFee;
    }

    public Rental() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VHS getVhs() {
        return vhs;
    }

    public void setVhs(VHS vhs) {
        this.vhs = vhs;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Double getLateFee() {
        return lateFee;
    }

    public void setLateFee(Double lateFee) {
        this.lateFee = lateFee;
    }
}
