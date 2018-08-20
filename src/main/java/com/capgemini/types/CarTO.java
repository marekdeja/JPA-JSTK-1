package com.capgemini.types;



import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Stream;

public class CarTO {
    private String model;
    private String type;
    private int year;
    private String color;
    private int power;
    private int mileage;
    private float engine;

    private Collection<RentalTO> rentals;
    private Collection<EmployeeTO> employees;

    private long id;

    public CarTO(String model, String type, int year, String color, int power, int mileage, float engine, Collection<RentalTO> rentals, Collection<EmployeeTO> employees) {
        super();
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

    public CarTO(String model, String type, int year, String color, int power, int mileage, float engine, Collection<RentalTO> rentals, Collection<EmployeeTO> employees, long id) {
        super();
        this.model = model;
        this.type = type;
        this.year = year;
        this.color = color;
        this.power = power;
        this.mileage = mileage;
        this.engine = engine;
        this.rentals = rentals;
        this.employees = employees;
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public int getPower() {
        return power;
    }

    public int getMileage() {
        return mileage;
    }

    public float getEngine() {
        return engine;
    }

    public Collection<RentalTO> getRentals() {
        return rentals;
    }

    public Collection<EmployeeTO> getEmployees() {
        return employees;
    }

    public long getId() {
        return id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setEngine(float engine) {
        this.engine = engine;
    }

    public void setRentals(Collection<RentalTO> rentals) {
        this.rentals = rentals;
    }

    public void setEmployees(Collection<EmployeeTO> employees) {
        this.employees = employees;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static CarTOBuilder builder() {
        return new CarTOBuilder();
    }

    public static class CarTOBuilder {
        private String model;
        private String type;
        private int year;
        private String color;
        private int power;
        private int mileage;
        private float engine;

        private Collection<RentalTO> rentals = new HashSet<>();
        private Collection<EmployeeTO> employees = new HashSet<>();

        private long id;

        public CarTOBuilder(){ super(); }

        public CarTOBuilder withModel(String model){
            this.model=model;
            return this;
        }

        public CarTOBuilder withType(String type){
            this.type=type;
            return this;
        }

        public CarTOBuilder withYear(int year){
            this.year=year;
            return this;
        }

        public CarTOBuilder withColor(String color){
            this.color=color;
            return this;
        }

        public CarTOBuilder withPower(int power){
            this.power=power;
            return this;
        }

        public CarTOBuilder withMileage(int mileage){
            this.mileage=mileage;
            return this;
        }

        public CarTOBuilder withEngine(float engine){
            this.engine=engine;
            return this;
        }

        public CarTOBuilder withRental(RentalTO rentalToBeAdded){
            this.rentals.add(rentalToBeAdded);
            return this;
        }

        public CarTOBuilder withRentals(Collection<RentalTO> rentalsToBeAdded){
            this.rentals.addAll(rentalsToBeAdded);
            return this;
        }

        public CarTOBuilder withEmployee(EmployeeTO employeeToBeAdded){
            this.employees.add(employeeToBeAdded);
            return this;
        }


        public CarTOBuilder withEmployees(Collection<EmployeeTO> employeesToBeAdded){
            this.employees.addAll(employeesToBeAdded);
            return this;
        }

        public CarTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CarTO build() {
            checkBeforeBuild(model, type, year, color, power, mileage);
            return new CarTO(model, type, year, color, power, mileage, engine, rentals, employees, id);
        }

        private void checkBeforeBuild(String model, String type, int year, String color, int power, int mileage){
            if (model==null||type==null||year==0||color==null||power==0||mileage==0){
                throw new RuntimeException("Incorrect car to be created");
            }
        }

//        private void checkBeforeBuild(String model, String type, int year, String color, int power, int mileage, float engine){
//            if (model==null||type==null||year==0||color==null||power==0||mileage==0||engine==0.0f){
//                throw new RuntimeException("Incorrect car to be created");
//            }
//        }

    }
}
