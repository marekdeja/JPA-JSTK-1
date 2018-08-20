package com.capgemini.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "customer")
public class CustomerEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(nullable = false, length = 45)
    private String surname;
    @Column(nullable = false)
    private Date birth;
    @Column(nullable = false, length = 45)
    private String email;
    @Column(nullable = false)
    private int phone;
    @Column(nullable = false)
    private long creditCard;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Collection<RentalEntity> rentals;

//    @OneToMany(mappedBy = "customerEntity")
//    private Collection<RentalEntity> rentalEntities;


    public CustomerEntity(){

    }

    public CustomerEntity(String name, String surname, Date birth, String email, int phone, long creditCard, Collection<RentalEntity> rentals) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.creditCard = creditCard;
        this.rentals = rentals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public long getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(long creditCard) {
        this.creditCard = creditCard;
    }

    public Collection<RentalEntity> getRentals() {
        return rentals;
    }

    public void setRentals(Collection<RentalEntity> rentals) {
        this.rentals = rentals;
    }
}
