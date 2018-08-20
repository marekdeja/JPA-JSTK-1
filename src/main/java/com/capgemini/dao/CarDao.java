package com.capgemini.dao;

import com.capgemini.domain.CarEntity;

import java.util.List;

public interface CarDao extends Dao<CarEntity, Long> {

   List<CarEntity> findCarByType (String type);
  List<CarEntity> findCarByModel (String model);
    List<CarEntity> findCarByModelAndType (String model, String type);
    List<CarEntity> findCarByEmployee (Long id);
    void setCarToEmployee (Long carId, Long employeeId);
}
