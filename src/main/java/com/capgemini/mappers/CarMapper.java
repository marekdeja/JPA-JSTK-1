package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.RentalTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {
    public static CarTO toCarTO (CarEntity carEntity) {
        if (carEntity == null)
            return null;

        Collection<RentalTO> rentalTOs = RentalMapper.map2TOs(carEntity.getRentals());
        Collection<EmployeeTO> employeeTOs = EmployeeMapper.map2TOs(carEntity.getEmployees());

        return new CarTO.CarTOBuilder()
                .withModel(carEntity.getModel())
                .withType(carEntity.getType())
                .withYear(carEntity.getYear())
                .withColor(carEntity.getColor())
                .withPower(carEntity.getPower())
                .withMileage(carEntity.getMileage())
                .withEngine(carEntity.getEngine())
                .withRentals(rentalTOs)
                .withEmployees(employeeTOs)
                .build();

    }

    public static CarEntity toCarEntity(CarTO carTO){
        if (carTO==null)
            return null;
        CarEntity carEntity = new CarEntity();
        carEntity.setModel(carTO.getModel());
        carEntity.setType(carTO.getType());
        carEntity.setYear(carTO.getYear());
        carEntity.setModel(carTO.getModel());
        carEntity.setColor(carTO.getColor());
        carEntity.setPower(carTO.getPower());
        carEntity.setMileage(carTO.getMileage());

        Collection<RentalEntity> rentals = RentalMapper.map2Entities(carTO.getRentals());
        Collection<EmployeeEntity> employees = EmployeeMapper.map2Entities(carTO.getEmployees());

        carEntity.setRentals(rentals);
        carEntity.setEmployees(employees);

        return carEntity;
    }
    public static Collection<CarTO> map2TOs (Collection<CarEntity> carEntities){
        return carEntities.stream().map(CarMapper::toCarTO).collect(Collectors.toList());
    }

    public static Collection<CarEntity> map2Entities (Collection<CarTO> carTOs){
        return carTOs.stream().map(CarMapper::toCarEntity).collect(Collectors.toList());
    }
}
