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

    /**
     * Adding employees to work at the place
     *
     * getting place from repo
     * then getting its employees
     * then adding new employee and updating
     * @param employeeId
     * @param placeId
     */
    @Override
    public void setEmployeeToPlace (Long employeeId, Long placeId){
PlaceEntity place = getOne(placeId);
Collection<EmployeeEntity> employees = place.getEmployees();
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select employee from EmployeeEntity employee where employee.id = :employeeId", EmployeeEntity.class);
        query.setParameter("employeeId", employeeId);
        List<EmployeeEntity> results = query.getResultList();
        EmployeeEntity employeeTarget = results.get(0);

        employees.add(employeeTarget);

        place.setEmployees(employees);

        this.update(place);
    }

    /**
     * Adding employees to work at the place
     *
     * getting place from repo
     * then getting its employees
     * then removing employee and updating
     * @param employeeId
     * @param placeId
     */
    @Override
    public void deleteEmployeeFromPlace (Long employeeId, Long placeId){
        PlaceEntity place = getOne(placeId);
        Collection<EmployeeEntity> employees = place.getEmployees();

        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select employee from EmployeeEntity employee where employee.id = :employeeId", EmployeeEntity.class);
        query.setParameter("employeeId", employeeId);
        List<EmployeeEntity> results = query.getResultList();
        EmployeeEntity employeeTarget = results.get(0);

        employees.remove(employeeTarget);

        place.setEmployees(employees);

        this.update(place);
    }

    @Override
    public Collection<EmployeeEntity> findAllEmployees(Long placeId){
        PlaceEntity place = getOne(placeId);
        return place.getEmployees();
    }

    /**
     * Looking ´for employee who works at the place and takes care of the car
     * @param carId
     * @param placeId
     * @return list with results
     */
    @Override
    public List<EmployeeEntity> findEmployeesOfCarAtPlace (Long carId, Long placeId){
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select e from EmployeeEntity e join PlaceEntity p where (p.employees.id = e.id AND p.id = :placeId AND e.cars.id = :carId)", EmployeeEntity.class);
        query.setParameter("carId", carId).setParameter("placeId", placeId);
        return query.getResultList();
    }
}
