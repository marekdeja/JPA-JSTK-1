package com.capgemini.service;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    CarService carService;
    @Autowired
    CarDao carDao;
    @Autowired
    EmployeeDao employeeDao;

    @Test
    @Transactional
    public void savedCarExists(){
        //given
        CarTO car = new CarTO.CarTOBuilder()
                .withModel("Ford")
                .withType("sedan")
                .withYear(1918)
                .withColor("white")
                .withPower(200)
                .withMileage(40000)
                .withEngine(1.4f)
                .build();
        // when
        CarTO savedCar = carService.saveCar(car);

        long elements = carDao.count();

        //then
        assertEquals(1, elements);
    }

    @Test
    @Transactional
    public void deleteCarAndSizeIsTwo(){
        //given
        CarTO car1 = new CarTO.CarTOBuilder()
                .withModel("Ford")
                .withType("sedan")
                .withYear(1918)
                .withColor("white")
                .withPower(200)
                .withMileage(40000)
                .withEngine(1.4f)
                .build();

        CarTO car2 = new CarTO.CarTOBuilder()
                .withModel("Toyota")
                .withType("sedan")
                .withYear(1918)
                .withColor("white")
                .withPower(200)
                .withMileage(40000)
                .withEngine(1.4f)
                .build();

        CarTO car3 = new CarTO.CarTOBuilder()
                .withModel("Ferrari")
                .withType("sedan")
                .withYear(1918)
                .withColor("white")
                .withPower(200)
                .withMileage(40000)
                .withEngine(1.4f)
                .build();
        // when
       CarTO car1To = carService.saveCar(car1);
        CarTO car2To = carService.saveCar(car2);
        CarTO car3To = carService.saveCar(car3);

        carService.deleteCar(car1To);

        long elements = carDao.count();

        //then
        System.out.println(elements);
        assertEquals(2, elements);
    }

    @Test
    @Transactional
    public void changeDataAndColorIsBlack() {
        //given
        CarTO car1 = new CarTO.CarTOBuilder()
                .withModel("Ford")
                .withType("sedan")
                .withYear(1918)
                .withColor("white")
                .withPower(200)
                .withMileage(40000)
                .withEngine(1.4f)
                .build();

        // when
        CarTO car1TO = carService.saveCar(car1);
        car1TO.setColor("black");
        carService.changeData(car1TO);

        //then
        CarEntity carTest = carDao.getOne(1L);
        assertEquals("black", carTest.getColor() );

    }

    @Test
    @Transactional
    public void setEmployeeAndCountIs1(){
        //given
        //given
        CarTO car1 = new CarTO.CarTOBuilder()
                .withModel("Ford")
                .withType("sedan")
                .withYear(1918)
                .withColor("white")
                .withPower(200)
                .withMileage(40000)
                .withEngine(1.4f)
                .build();

        EmployeeEntity employee1 = new EmployeeEntity("Arek", "Janiszeski", new Date(1988-10-10), "Poznan", "arek@jarmarek.pl", 505505606, null);

        // when
        carService.saveCar(car1);
        employeeDao.save(employee1);

        carService.setEmployeeToCar(1L,1L);
        int sizeEmployees = carDao.getOne(1L).getEmployees().size();
        //

        assertEquals(sizeEmployees, 1 );

    }



}

