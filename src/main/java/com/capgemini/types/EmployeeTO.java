package com.capgemini.types;

import com.capgemini.domain.CarEntity;
import org.springframework.util.CollectionUtils;

import javax.persistence.Column;
import java.util.Collection;
import java.util.Date;

public class EmployeeTO {


    private String name;
    private String surname;
    private Date birth;
    private String address;
    private String email;
    private int phone;

    private Collection<CarTO> cars;

    private long id;

    public EmployeeTO() {

    }

    public EmployeeTO(String name, String surname, Date birth, String address, String email, int phone, Collection<CarTO> cars) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.cars = cars;
    }

    public EmployeeTO(String name, String surname, Date birth, String address, String email, int phone, Collection<CarTO> cars, long id) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.cars = cars;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirth() {
        return birth;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public Collection<CarTO> getCars() {
        return cars;
    }

    public long getId() {
        return id;
    }

    public static EmployeeTOBuilder builder() {
        return new EmployeeTOBuilder();
    }

    public static class EmployeeTOBuilder {
        private String name;
        private String surname;
        private Date birth;
        private String address;
        private String email;
        private int phone;

        private Collection<CarTO> cars;

        private long id;

        public EmployeeTOBuilder() {
            super();
        }

        public EmployeeTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public EmployeeTOBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public EmployeeTOBuilder withBirth(Date birth) {
            this.birth = birth;
            return this;
        }

        public EmployeeTOBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public EmployeeTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public EmployeeTOBuilder withPhone(int phone) {
            this.phone = phone;
            return this;
        }

        public EmployeeTOBuilder withCar(CarTO car) {
            this.cars.add(car);
            return this;
        }

        public EmployeeTOBuilder withCars(Collection<CarTO> carsToBeAdded) {
            this.cars.addAll(carsToBeAdded);
            return this;
        }

        public EmployeeTOBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public EmployeeTO build() {
            checkBeforeBuild(name, surname, birth, address, email, phone, cars);
            return new EmployeeTO(name, surname, birth, address, email, phone, cars, id);
        }

        private void checkBeforeBuild(String name, String surname, Date birth, String address, String email, int phone, Collection<CarTO> cars) {
            if (name == null || surname == null || birth == null || address == null || email == null || phone == 0 ) {
                throw new RuntimeException("Incorrect employee to be created");
            }
        }
    }
}
