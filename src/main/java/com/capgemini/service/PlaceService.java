package com.capgemini.service;

import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PlaceTO;

import java.util.Collection;
import java.util.List;

public interface PlaceService {
    PlaceTO savePlace(PlaceTO placeTO);
    void deletePlace(PlaceTO placeTO);
    PlaceTO changeData(PlaceTO placeTO);

    void setEmployeeToPlace (Long employeeId, Long placeId);
    void deleteEmployeeFromPlace (Long employeeId, Long placeId);
    Collection<EmployeeTO> findAllEmployees(Long placeId);
    Collection<EmployeeTO> findEmployeesOfCarAtPlace(Long carId, Long placeId);
}
