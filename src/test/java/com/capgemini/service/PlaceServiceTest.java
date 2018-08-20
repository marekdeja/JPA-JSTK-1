package com.capgemini.service;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.PlaceDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PlaceTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceServiceTest {

    @Autowired
    PlaceService placeService;
    @Autowired
    PlaceDao placeDao;
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    CarDao carDao;

    @Test
    @Transactional
    public void saveMyPlaceAndCountIsOne() {
        //given
        PlaceTO place = new PlaceTO.PlaceTOBuilder()
                .withAddress("Poznan")
                .withPhone(605987)
                .build();

        //when
        placeService.savePlace(place);
        long elements = placeDao.count();

        //then
        assertEquals(elements, 1);
    }

    @Test
    @Transactional
    public void deleteAndCountIsZero() {
        //given
        PlaceTO place = new PlaceTO.PlaceTOBuilder()
                .withAddress("Poznan")
                .withPhone(605987)
                .build();
        //when
        PlaceTO placeTO = placeService.savePlace(place);
        System.out.println(placeTO.getId());
        placeService.deletePlace(placeTO);
        long elements = placeDao.count();

        //then
        assertEquals(0, elements);
    }

    @Test
    @Transactional
    public void changedAddressIsGdynia() {
        //given
        PlaceTO place = new PlaceTO.PlaceTOBuilder()
                .withAddress("Poznan")
                .withPhone(605987)
                .build();
        //when
        placeService.savePlace(place);
        place.setAddress("Gdynia");
        placeService.changeData(place);

        String address = placeDao.getOne(1L).getAddress();
        //then
        assertEquals(address, "Gdynia");
    }

    @Test
    @Transactional
    public void newSetEmployeeAndEmployeesSizeIsOne(){
        //given
        PlaceTO place = new PlaceTO.PlaceTOBuilder()
                .withAddress("Poznan")
                .withPhone(605987)
                .build();

        EmployeeEntity employee1 = new EmployeeEntity("Arek", "Janiszeski", new Date(1988-10-10), "Poznan", "arek@jarmarek.pl", 505505606, null);

        //when
        placeService.savePlace(place);
        employeeDao.save(employee1);
        placeService.setEmployeeToPlace(1L, 1L);
        //then
        int sizePlaceEmployee = placeDao.getOne(1L).getEmployees().size();
        assertEquals(sizePlaceEmployee, 1);
    }

    @Test
    @Transactional
    public void deleteEmployeeAndEmployeesSizeIsOne(){
        //given
        PlaceTO place = new PlaceTO.PlaceTOBuilder()
                .withAddress("Poznan")
                .withPhone(605987)
                .build();

        EmployeeEntity employee1 = new EmployeeEntity("Arek", "Janiszeski", new Date(1988-10-10), "Poznan", "arek@jarmarek.pl", 505505606, null);
        EmployeeEntity employee2 = new EmployeeEntity("Arek", "Janiszeski", new Date(1988-10-10), "Poznan", "arek@jarmarek.pl", 505505606, null);

        //when
        placeService.savePlace(place);
        employeeDao.save(employee1);
        employeeDao.save(employee2);
        placeService.setEmployeeToPlace(1L, 1L);
        placeService.setEmployeeToPlace(2L, 1L);

        placeService.deleteEmployeeFromPlace(1L, 1L);
        //then
        int sizePlaceEmployee = placeDao.getOne(1L).getEmployees().size();
        assertEquals(sizePlaceEmployee, 1);
    }

    @Test
    @Transactional
    public void foundEmployeesSizeIsThree(){
        //given
        PlaceTO place = new PlaceTO.PlaceTOBuilder()
                .withAddress("Poznan")
                .withPhone(605987)
                .build();

        EmployeeEntity employee1 = new EmployeeEntity("Arek", "Janiszeski", new Date(1988-10-10), "Poznan", "arek@jarmarek.pl", 505505606, null);
        EmployeeEntity employee2 = new EmployeeEntity("Arek", "Janiszeski", new Date(1988-10-10), "Poznan", "arek@jarmarek.pl", 505505606, null);
        EmployeeEntity employee3 = new EmployeeEntity("Arek", "Janiszeski", new Date(1988-10-10), "Poznan", "arek@jarmarek.pl", 505505606, null);

        PlaceTO placeTO = placeService.savePlace(place);

        EmployeeEntity employeeSaved1 = employeeDao.save(employee1);
        EmployeeEntity employeeSaved2 = employeeDao.save(employee2);
        EmployeeEntity employeeSaved3 = employeeDao.save(employee3);

        placeService.setEmployeeToPlace(1L, 1L);
        placeService.setEmployeeToPlace(2L, 1L);
        placeService.setEmployeeToPlace(3L, 1L);
        //when
        Collection<EmployeeTO> employeesAtPlace = placeService.findAllEmployees(1L);
        int size = employeesAtPlace.size();

        assertEquals(size, 3);
    }

    @Test
    @Transactional
    public void findEmployeesWhereCarIdIsOneAndPlaceIsOne(){
        //given
        PlaceTO place = new PlaceTO.PlaceTOBuilder()
                .withAddress("Poznan")
                .withPhone(605987)
                .build();

        PlaceTO place2 = new PlaceTO.PlaceTOBuilder()
                .withAddress("Poznan")
                .withPhone(605987)
                .build();

        CarEntity car1 = new CarEntity("Ford", "sedan", 1949, "orange", 500, 890000, 2.0f, null, null);
        CarEntity car2 = new CarEntity("Ford", "sedan", 1949, "orange", 500, 890000, 2.0f, null, null);

        Collection<CarEntity> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);

        EmployeeEntity employee1 = new EmployeeEntity("Arek", "Janiszeski", new Date(1988-10-10), "Poznan", "arek@jarmarek.pl", 505505606, cars);
        EmployeeEntity employee2 = new EmployeeEntity("Arek", "Janiszeski", new Date(1988-10-10), "Poznan", "arek@jarmarek.pl", 505505606, cars);
        EmployeeEntity employee3 = new EmployeeEntity("Arek", "Janiszeski", new Date(1988-10-10), "Poznan", "arek@jarmarek.pl", 505505606, null);

        placeService.savePlace(place);
        placeService.savePlace(place2);

        carDao.save(car1);
        carDao.save(car2);

        employeeDao.save(employee1);
        employeeDao.save(employee2);
        employeeDao.save(employee3);

        placeService.setEmployeeToPlace(1L, 1L);
        placeService.setEmployeeToPlace(2L, 2L);
        placeService.setEmployeeToPlace(3L, 1L);
        //when
        Collection<EmployeeTO> employeesAtPlace = placeService.findEmployeesOfCarAtPlace(1L,1L);
        int size = employeesAtPlace.size();

        assertEquals(size, 1);
    }
}
