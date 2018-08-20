package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="cars")
@NamedQuery(name="cars.findCarsByEmployee", query="select c from CarEntity c join c.employees e where e.id = :employeeId")
public class CarEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, length = 45)
    private String model;
    @Column(nullable = false, length = 45)
    private String type;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false, length = 45)
    private String color;
    @Column(nullable = false)
    private int power;
    @Column(nullable = false)
    private int mileage;
    @Column(nullable = false)
    private float engine;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Collection<RentalEntity> rentals;

    @ManyToMany(mappedBy="cars")
    private Collection<EmployeeEntity> employees;

    public CarEntity(){
    }

    public CarEntity(String model, String type, int year, String color, int power, int mileage, float engine, Collection<RentalEntity> rentals, Collection<EmployeeEntity> employees) {
        this.model = model;
        this.type = type;
        this.year = year;
        this.color = color;
        this.power = power;
        this.mileage = mileage;
        this.engine = engine;
        this.rentals = rentals;
        this.employees = employees;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public float getEngine() {
        return engine;
    }

    public void setEngine(float engine) {
        this.engine = engine;
    }

    public Collection<RentalEntity> getRentals() {
        return rentals;
    }

    public void setRentals(Collection<RentalEntity> rentals) {
        this.rentals = rentals;
    }

    public Collection<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<EmployeeEntity> employees) {
        this.employees = employees;
    }
}
