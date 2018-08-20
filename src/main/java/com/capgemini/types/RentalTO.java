package com.capgemini.types;

import java.util.Date;

public class RentalTO {
    private Date startDate;
    private Date endDate;
    private int amount;

    private Long id;

    public RentalTO() {
    }

    public RentalTO(Date startDate, Date endDate, int amount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }


    public RentalTO(Date startDate, Date endDate, int amount, Long id) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getAmount() {
        return amount;
    }

    public Long getId() {
        return id;
    }

    public static RentalTOBuilder builder() {
        return new RentalTOBuilder();
    }

    public static class RentalTOBuilder {
        private Date startDate;
        private Date endDate;
        private int amount;

        private Long id;

        public RentalTOBuilder() {
            super();
        }

        public RentalTOBuilder withStartDate(Date date) {
            this.startDate = date;
            return this;
        }

        public RentalTOBuilder withEndDate(Date date) {
            this.endDate = date;
            return this;
        }

        public RentalTOBuilder withAmount(int amount) {
            this.amount = amount;
            return this;
        }
        public RentalTO build() {
            checkBeforeBuild( startDate,  endDate,  amount);
            return new RentalTO(startDate,  endDate,  amount, id);
        }
        private void checkBeforeBuild(Date startDate, Date endDate, int amount) {
            if (startDate == null || endDate == null || amount == 0 ) {
                throw new RuntimeException("Incorrect rental to be created");
            }
        }
    }
}