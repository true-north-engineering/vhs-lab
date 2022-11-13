package com.truenorth.vhslab.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "vhs")
public class VHS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "VHS id cannot be null")
    private Long vhsId;

    @NotNull(message = "Title should not be null")
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NotNull(message = "Availability should not be null")
    @Column(name = "AVAILABILITY", nullable = false)
    private Boolean isAvailable;

    public Long getVhsId() {
        return vhsId;
    }

    public void setVhsId(Long vhsId) {
        this.vhsId = vhsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj)
            return true;

        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        VHS vhs = (VHS) obj;

        return (vhs.title.equals(this.title)  && vhs.vhsId == this.vhsId && isAvailable == this.isAvailable);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + vhsId.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + String.valueOf(isAvailable).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "VHS id: " + vhsId + " Title: " + title + " Availability: " + isAvailable;
    }
}
