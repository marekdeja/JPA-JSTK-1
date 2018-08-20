package com.capgemini.service.impl;

import com.capgemini.dao.PlaceDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PlaceEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.PlaceMapper;
import com.capgemini.service.PlaceService;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PlaceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDao placeRepository;

    public PlaceTO savePlace(PlaceTO placeTO) {
        PlaceEntity placeEntity = placeRepository.save(PlaceMapper.toPlaceEntity(placeTO));
        return PlaceMapper.toPlaceTO(placeEntity);
    }

    public void deletePlace(PlaceTO placeTO) {
        placeRepository.delete(placeTO.getId());
    }

    public PlaceTO changeData(PlaceTO placeTO) {
        PlaceEntity placeEntity = placeRepository.update(PlaceMapper.toPlaceEntity(placeTO));
        return PlaceMapper.toPlaceTO(placeEntity);
    }

    public void setEmployeeToPlace(Long employeeId, Long placeId) {
        placeRepository.setEmployeeToPlace(employeeId, placeId);
    }

    public void deleteEmployeeFromPlace(Long employeeId, Long placeId) {
        placeRepository.deleteEmployeeFromPlace(employeeId, placeId);
    }

    public Collection<EmployeeTO> findAllEmployees(Long placeId) {
        Collection<EmployeeEntity> employees = placeRepository.findAllEmployees(placeId);
        return EmployeeMapper.map2TOs(employees);
    }

    /**
     * searching for employees working at the place who care after the car
     *
     * @param carId
     * @param placeId
     * @return collection of found employees
     */
    public Collection<EmployeeTO> findEmployeesOfCarAtPlace(Long carId, Long placeId) {
        Collection<EmployeeEntity> employees = placeRepository.findEmployeesOfCarAtPlace(carId, placeId);
        return EmployeeMapper.map2TOs(employees);
    }

}
