package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.BookEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

    @Override
    public List<CarEntity> findCarByType (String type){
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where upper(car.type) like upper(:type)", CarEntity.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarByModel (String model){
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where upper(car.model) like upper(:model)", CarEntity.class);
        query.setParameter("model", model);
        return query.getResultList();
    }

    /**
     * looking for a car with two parameters: model and type
     * @param model
     * @param type
     * @return list with results
     */
    @Override
    public List<CarEntity> findCarByModelAndType (String model, String type){
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where upper(car.model) like upper(:model) and upper(car.type) like upper(:type) ", CarEntity.class);
        query.setParameter("model", model).setParameter("type", type);
        return query.getResultList();
    }


    @Override
    public List<CarEntity> findCarByEmployee (Long employeeId){
        TypedQuery<CarEntity> query = entityManager.createNamedQuery("cars.findCarsByEmployee", CarEntity.class);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }

    /**
     * Adding car to employee to care after
     *
     * getting car from repo
     * then getting its employees
     * then adding new employee and updating
     * @param carId
     * @param employeeId
     */
    @Override
    public void setCarToEmployee (Long carId, Long employeeId){
        CarEntity car = getOne(carId);
        Collection<EmployeeEntity> employeesCar = car.getEmployees();
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select employee from EmployeeEntity employee where employee.id = :employeeId", EmployeeEntity.class);
        query.setParameter("employeeId", employeeId);
        List<EmployeeEntity> results = query.getResultList();
        EmployeeEntity employeeTarget = results.get(0);

        employeesCar.add(employeeTarget);

        car.setEmployees(employeesCar);

        this.update(car);
    }

    @Override
    public List<CarEntity> findCarsRentedFromTO(Date startDate, Date endDate) {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car, RentalEntity rental where ", CarEntity.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarsRentedByMoreThanTenCustomers() {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car join car.rentals cr group by car.id having count(distinct cr.)>10", CarEntity.class);

        return query.getResultList();
    }


}
