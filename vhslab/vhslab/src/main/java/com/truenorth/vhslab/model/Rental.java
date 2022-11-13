package com.truenorth.vhslab.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Rental id cannot be null")
    private Long rentalId;

    @NotNull(message = "Rental start date cannot be null")
    @Column(name = "CREATED_AT")
    private Date startDate;

    @Column(name = "RETURNED_AT")
    private Date endDate;

    @NotNull(message = "Due date cannot be null")
    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Column(name = "PAID_FEE")
    private Integer fee;

    @NotNull(message = "Rental VHS cannot be null")
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn(name = "vhsId")
    private VHS vhs;

    @NotNull(message = "Rental VHS cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public VHS getVhs() {
        return vhs;
    }

    public void setVhs(VHS vhs) {
        this.vhs = vhs;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj)
            return true;

        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        Rental rental = (Rental) obj;

        return (rental.rentalId == this.rentalId && rental.fee == this.fee && rental.startDate.equals(this.startDate) && rental.endDate.equals(this.endDate) && rental.dueDate.equals(this.dueDate) && rental.vhs.equals(this.vhs) && rental.user.equals(this.user));
    }

    @Override
    public int hashCode() {

        int result = 17;
        result = 31 * result + rentalId.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + dueDate.hashCode();
        result = 31 * result + fee.hashCode();
        result = 31 * result + vhs.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Rental id: " + rentalId + "Rental user: " + user.getUserId() + " Rental VHS: " + vhs.getVhsId() + " Rental date: " + startDate + " Due date: " + dueDate + " Return date: " + endDate + " Fee: " + fee;
    }
}