package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static EmployeeTO toEmployeeTO(EmployeeEntity employeeEntity) {
        if (employeeEntity == null)
            return null;

        Collection<CarTO> carTOs = CarMapper.map2TOs(employeeEntity.getCars());

        return new EmployeeTO.EmployeeTOBuilder()
                .withName(employeeEntity.getName())
                .withSurname(employeeEntity.getSurname())
                .withBirth(employeeEntity.getBirth())
                .withAddress(employeeEntity.getAddress())
                .withEmail(employeeEntity.getEmail())
                .withPhone(employeeEntity.getPhone())
                .withCars(carTOs)
                .withId(employeeEntity.getId())
                .build();

    }

    public static EmployeeEntity toEmployeeEntity(EmployeeTO employeeTO) {
        if (employeeTO == null)
            return null;
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employeeTO.getName());
        employeeEntity.setSurname(employeeTO.getSurname());
        employeeEntity.setBirth(employeeTO.getBirth());
        employeeEntity.setAddress(employeeTO.getAddress());
        employeeEntity.setEmail(employeeTO.getEmail());
        employeeEntity.setPhone(employeeTO.getPhone());
        employeeEntity.setId(employeeTO.getId());

        Collection<CarEntity> cars = CarMapper.map2Entities(employeeTO.getCars());

        employeeEntity.setCars(cars);

        return employeeEntity;

    }
    public static Collection<EmployeeTO> map2TOs (Collection<EmployeeEntity> employeeEntities){
        return employeeEntities.stream().map(EmployeeMapper::toEmployeeTO).collect(Collectors.toList());
    }
    public static Collection<EmployeeEntity> map2Entities (Collection<EmployeeTO> employeeTOs){
        return employeeTOs.stream().map(EmployeeMapper::toEmployeeEntity).collect(Collectors.toList());
    }

}
