package com.capgemini.dao;

import com.capgemini.domain.CarEntity;

import java.util.Date;
import java.util.List;

public interface CarDao extends Dao<CarEntity, Long> {

   List<CarEntity> findCarByType (String type);
  List<CarEntity> findCarByModel (String model);
    List<CarEntity> findCarByModelAndType (String model, String type);
    List<CarEntity> findCarByEmployee (Long id);
    List<CarEntity> findCarsRentedFromTO(Date startDate, Date endDate);
    List<CarEntity> findCarsRentedByMoreThanTenCustomers();
    void setCarToEmployee (Long carId, Long employeeId);
}
