package com.anam.vhsrentalshop.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name="rentals")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne()
    @JoinColumn(name="vhs_id", nullable = false)
    private VhsEntity vhsEntity;

    private LocalDate rentalDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;
    private double lateFee;
    private boolean deleted;

    public RentalEntity(UserEntity userEntity, VhsEntity vhsEntity, LocalDate rentalDate) {
        this.userEntity = userEntity;
        this.vhsEntity = vhsEntity;
        this.rentalDate = rentalDate;
        this.dueDate = rentalDate.plusDays(14);
        this.returned = false;
        this.deleted = false;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.returned = returnDate != null;
    }
}
