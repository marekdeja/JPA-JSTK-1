package com.capgemini.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(nullable = false, length = 45)
    private String surname;
    @Column(nullable = false)
    private Date birth;
    @Column(nullable = false, length = 45)
    private String address;
    @Column(nullable = false, length = 45)
    private String email;
    @Column(nullable = false)
    private int phone;

    @ManyToMany
    @JoinTable(
            name = "employee_car",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "car_id")}
    )
    private Collection<CarEntity> cars;


    public EmployeeEntity() {
    }

    public EmployeeEntity(String name, String surname, Date birth, String address, String email, int phone, Collection<CarEntity> cars) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.cars = cars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Collection<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Collection<CarEntity> cars) {
        this.cars = cars;
    }
}
