package com.capgemini.types;


import org.springframework.util.CollectionUtils;

import javax.persistence.Column;
import java.util.Collection;
import java.util.HashSet;

public class PlaceTO {

    private String address;
    private int phone;

    private Collection<EmployeeTO> employees;
    private Collection<RentalTO> rentalStart;
    private Collection<RentalTO> rentalEnd;

    private Long id;

    public PlaceTO(){
    }

    public PlaceTO(String address, int phone, Collection<EmployeeTO> employees, Collection<RentalTO> rentalStart, Collection<RentalTO> rentalEnd) {
        this.address = address;
        this.phone = phone;
        this.employees = employees;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;

    }

    public PlaceTO(String address, int phone, Collection<EmployeeTO> employees, Collection<RentalTO> rentalStart, Collection<RentalTO> rentalEnd, Long id) {
        this.address = address;
        this.phone = phone;
        this.employees = employees;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public Collection<EmployeeTO> getEmployees() {
        return employees;
    }

    public Collection<RentalTO> getRentalStart() {
        return rentalStart;
    }

    public Collection<RentalTO> getRentalEnd() {
        return rentalEnd;
    }

    public Long getId() {
        return id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setEmployees(Collection<EmployeeTO> employees) {
        this.employees = employees;
    }

    public void setRentalStart(Collection<RentalTO> rentalStart) {
        this.rentalStart = rentalStart;
    }

    public void setRentalEnd(Collection<RentalTO> rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static PlaceTOBuilder builder() {
        return new PlaceTOBuilder();
    }

    public static class PlaceTOBuilder {

        private String address;
        private int phone;

        private Collection<EmployeeTO> employees = new HashSet<>();
        private Collection<RentalTO> rentalStart = new HashSet<>();
        private Collection<RentalTO> rentalEnd = new HashSet<>();

        private Long id;

        public PlaceTOBuilder() {
            super();
        }

        public PlaceTOBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public PlaceTOBuilder withPhone(int phone) {
            this.phone = phone;
            return this;
        }

        public PlaceTOBuilder withEmployee(EmployeeTO employee) {
            this.employees.add(employee);
            return this;
        }

        public PlaceTOBuilder withEmployees(Collection<EmployeeTO> employees) {
            this.employees.addAll(employees);
            return this;
        }

        public PlaceTOBuilder withRentalStart(Collection<RentalTO> rentals) {
            this.rentalStart.addAll(rentals);
            return this;
        }

        public PlaceTOBuilder withRentalEnd(Collection<RentalTO> rentals) {
            this.rentalEnd.addAll(rentals);
            return this;
        }

        public PlaceTOBuilder withId(long id){
            this.id=id;
            return this;
        }
        public PlaceTO build( ){
            checkBeforeBuild(address,  phone);
            return new PlaceTO(address,  phone, employees,  rentalStart, rentalEnd, id);
        }

        private void checkBeforeBuild(String address, int phone){
            if (address==null||phone==0){
                throw new RuntimeException("Incorrect place to be created");
            }
        }
    }


}