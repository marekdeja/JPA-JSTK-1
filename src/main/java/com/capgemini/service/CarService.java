package com.capgemini.service;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.CarTO;

import java.util.Collection;
import java.util.List;

public interface CarService {
    CarTO saveCar (CarTO carTO);
    void deleteCar (CarTO carTO);
    CarTO changeData (CarTO carTO);
   void setEmployeeToCar (Long carId, Long employeeId);

    Collection<CarTO> findCarByType (String type);
    Collection<CarTO> findCarByModel (String model);
  Collection<CarTO> findCarByEmployee (Long id);
}
