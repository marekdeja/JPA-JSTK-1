package com.capgemini.dao;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CarDaoTest {

    @Autowired
    private CarDao carDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    @Transactional
    public void savedCarWithIdOneIsWhite(){
        //given
        CarEntity car1 = new CarEntity("Toyota", "sedan", 2000, "white", 150, 20000, 2.0f, null, null);
        CarEntity car2 = new CarEntity("BMW", "combi", 2001, "black", 250, 54000, 3.0f, null, null);
        CarEntity car3 = new CarEntity("Subaru", "sedan", 2003, "green", 120, 25000, 1.5f, null, null);

        //when
        car1 = carDao.save(car1);
        carDao.save(car2);
        carDao.save(car3);

        CarEntity myCar = carDao.getOne(car1.getId());
        //then
        System.out.println(myCar.getColor());
        assertEquals(myCar.getColor(), "white");
    }

    @Test
    @Transactional
    public void carWithTypeCombiShouldHaveYear2001(){
        //given
        CarEntity car1 = new CarEntity("Toyota", "sedan", 2000, "white", 150, 20000, 2.0f, null, null);
        CarEntity car2 = new CarEntity("BMW", "combi", 2001, "black", 250, 54000, 3.0f, null, null);
        CarEntity car3 = new CarEntity("Subaru", "sedan", 2003, "green", 120, 25000, 1.5f, null, null);

       // when
        carDao.save(car1);
        carDao.save(car2);
        carDao.save(car3);

        List<CarEntity> myCars = carDao.findCarByType("combi");

        //then
        assertEquals(myCars.get(0).getYear(), 2001);
    }

    @Test
    @Transactional
    public void carWithModelSubaruShouldHaveColorGreen(){
        //given
        CarEntity car1 = new CarEntity("Toyota", "sedan", 2000, "white", 150, 20000, 2.0f, null, null);
        CarEntity car2 = new CarEntity("BMW", "combi", 2001, "black", 250, 54000, 3.0f, null, null);
        CarEntity car3 = new CarEntity("Subaru", "sedan", 2003, "green", 120, 25000, 1.5f, null, null);

        // when
        carDao.save(car1);
        carDao.save(car2);
        carDao.save(car3);

        List<CarEntity> myCars = carDao.findCarByModel("Subaru");

        //then
        assertEquals(myCars.get(0).getColor(), "green");
    }

    @Test
    @Transactional
    public void searchedBySubaruShouldCarsSizeBeTwo(){
        //given
        CarEntity car1 = new CarEntity("Toyota", "sedan", 2000, "white", 150, 20000, 2.0f, null, null);
        CarEntity car2 = new CarEntity("BMW", "combi", 2001, "black", 250, 54000, 3.0f, null, null);
        CarEntity car3 = new CarEntity("Subaru", "sedan", 2003, "green", 120, 25000, 1.5f, null, null);
        CarEntity car4 = new CarEntity("Subaru", "sedan", 2001, "black", 120, 25000, 1.5f, null, null);

        // when
        carDao.save(car1);
        carDao.save(car2);
        carDao.save(car3);
        carDao.save(car4);

        List<CarEntity> myCars = carDao.findCarByModel("Subaru");

        //then
        assertEquals(myCars.size(), 2);
    }

    @Test
    @Transactional
    public void searchedBySubaruCombiShouldBeSizeOne(){
        //given
        CarEntity car1 = new CarEntity("Toyota", "sedan", 2000, "white", 150, 20000, 2.0f, null, null);
        CarEntity car2 = new CarEntity("BMW", "combi", 2001, "black", 250, 54000, 3.0f, null, null);
        CarEntity car3 = new CarEntity("Subaru", "sedan", 2003, "green", 120, 25000, 1.5f, null, null);
        CarEntity car4 = new CarEntity("Subaru", "combi", 2001, "black", 120, 25000, 1.5f, null, null);

        // when
        carDao.save(car1);
        carDao.save(car2);
        carDao.save(car3);
        carDao.save(car4);

        List<CarEntity> myCars = carDao.findCarByModelAndType("Subaru", "Combi");


        //then
        assertEquals(myCars.size(), 1);
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void findByIdEmployee(){
        //given
        CarEntity car1 = new CarEntity("Toyota", "sedan", 2000, "white", 150, 20000, 2.0f, null, null);
        CarEntity car2 = new CarEntity("BMW", "combi", 2001, "black", 250, 54000, 3.0f, null, null);
        CarEntity car3 = new CarEntity("Subaru", "sedan", 2003, "green", 120, 25000, 1.5f, null, null);

        EmployeeEntity employee1 = new EmployeeEntity("Arek", "Janiszeski", new Date(1988-10-10), "Poznan", "arek@jarmarek.pl", 505505606, null);

        Collection<CarEntity> carsToEmployee = new HashSet<>();
        carsToEmployee.add(car1);
        employee1.setCars(carsToEmployee);

        Collection<EmployeeEntity> employeesToCars = new HashSet<>();
       employeesToCars.add(employee1);
       car1.setEmployees(employeesToCars);

        // when
        employeeDao.save(employee1);

        car1 = carDao.save(car1);
        carDao.save(car2);
        carDao.save(car3);


        //then
        System.out.println(carDao.findAll().get(0).getEmployees().size());

        List <CarEntity> carResult = carDao.findCarByEmployee(1L);
        String expectedColor = carResult.get(0).getColor();
        assertEquals(expectedColor, "white");
    }
}
