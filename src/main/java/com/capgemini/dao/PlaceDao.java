package com.capgemini.dao;


import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PlaceEntity;

import java.util.List;

public interface PlaceDao extends Dao<PlaceEntity, Long>  {
    void setEmployeeToPlace (Long employeeId, Long placeId);
    void deleteEmployeeFromPlace (Long employeeId, Long placeId);
    List<EmployeeEntity> findEmployeesOfCarAtPlace(Long carId, Long placeId);


}
