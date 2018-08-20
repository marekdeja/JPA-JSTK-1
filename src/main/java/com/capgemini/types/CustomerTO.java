package com.capgemini.types;


import org.springframework.util.CollectionUtils;;

import java.util.Collection;
import java.util.Date;

public class CustomerTO {

    private String name;
    private String surname;
    private Date birth;
    private String email;
    private int phone;
    private long creditCard;

    private Collection<RentalTO> rentals;

    private int id;

    public CustomerTO(String name, String surname, Date birth, String email, int phone, long creditCard, Collection<RentalTO> rentals) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.creditCard = creditCard;
        this.rentals = rentals;
    }

    public CustomerTO(String name, String surname, Date birth, String email, int phone, long creditCard, Collection<RentalTO> rentals, int id) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.creditCard = creditCard;
        this.rentals = rentals;
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

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public long getCreditCard() {
        return creditCard;
    }

    public Collection<RentalTO> getRentals() {
        return rentals;
    }

    public int getId() {
        return id;
    }

    public static CustomerTOBuilder builder() {
        return new CustomerTOBuilder();
    }

    public static class CustomerTOBuilder {
        private String name;
        private String surname;
        private Date birth;
        private String email;
        private int phone;
        private long creditCard;

        private Collection<RentalTO> rentals;

        private int id;

        public CustomerTOBuilder() {
            super();
        }

        public CustomerTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CustomerTOBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public CustomerTOBuilder withBirth(Date birth) {
            this.birth = birth;
            return this;
        }

        public CustomerTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerTOBuilder withPhone(int phone) {
            this.phone = phone;
            return this;
        }

        public CustomerTOBuilder withCreditCard(long creditCard) {
            this.creditCard = creditCard;
            return this;
        }

        public CustomerTOBuilder withRental(RentalTO rental) {
            this.rentals.add(rental);
            return this;
        }

        public CustomerTOBuilder withRentals(Collection<RentalTO> rentalsToBeAdded) {
            this.rentals.addAll(rentalsToBeAdded);
            return this;
        }

        public CustomerTOBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public CustomerTO build() {
            checkBeforeBuild(name, surname, birth, email, phone, creditCard, rentals);
            return new CustomerTO(name, surname, birth, email, phone, creditCard, rentals, id);
        }

        private void checkBeforeBuild(String name, String surname, Date birth, String email, int phone, long creditCard, Collection<RentalTO> rentals){
            if (CollectionUtils.isEmpty(rentals)||name==null||surname==null||birth==null||email==null||phone==0||creditCard==0){
                throw new RuntimeException("Incorrect customer to be created");
            }
        }
    }
}
