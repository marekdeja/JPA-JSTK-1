package com.capgemini.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "place")
public class PlaceEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 45)
    private String address;
    @Column(nullable = false)
    private int phone;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id")
    private Collection<EmployeeEntity> employees;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "placeIdStart")
    private Collection<RentalEntity> rentalEntitiesStart;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "placeIdEnd")
    private Collection<RentalEntity> rentalEntitiesEnd;


    public PlaceEntity() {
    }

    public PlaceEntity(String address, int phone, Collection<EmployeeEntity> employees, Collection<RentalEntity> rentalEntitiesStart, Collection<RentalEntity> rentalEntitiesEnd) {
        this.address = address;
        this.phone = phone;
        this.employees = employees;
        this.rentalEntitiesStart = rentalEntitiesStart;
        this.rentalEntitiesEnd = rentalEntitiesEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Collection<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public Collection<RentalEntity> getRentalEntitiesStart() {
        return rentalEntitiesStart;
    }

    public void setRentalEntitiesStart(Collection<RentalEntity> rentalEntitiesStart) {
        this.rentalEntitiesStart = rentalEntitiesStart;
    }

    public Collection<RentalEntity> getRentalEntitiesEnd() {
        return rentalEntitiesEnd;
    }

    public void setRentalEntitiesEnd(Collection<RentalEntity> rentalEntitiesEnd) {
        this.rentalEntitiesEnd = rentalEntitiesEnd;
    }
}
