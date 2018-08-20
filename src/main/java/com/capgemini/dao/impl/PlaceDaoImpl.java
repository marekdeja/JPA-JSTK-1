package com.capgemini.dao.impl;

import com.capgemini.dao.PlaceDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PlaceEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

@Repository
public class PlaceDaoImpl extends AbstractDao<PlaceEntity, Long> implements PlaceDao {

    @Override
    public void setEmployeeToPlace (Long employeeId, Long placeId){
PlaceEntity place = getOne(placeId);
Collection<EmployeeEntity> employees = place.getEmployees();
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select employee from EmployeeEntity employee where employee.id = :employeeId", EmployeeEntity.class);
        query.setParameter("employeeId", employeeId);
        List<EmployeeEntity> results = query.getResultList();
        
    }

    @Override
    public void deleteEmployeeFromPlace (Long employeeId, Long placeId){

    }

    @Override
    public List<EmployeeEntity> findEmployeesOfCarAtPlace (Long carId, Long placeId){

        return null;
    }
}
