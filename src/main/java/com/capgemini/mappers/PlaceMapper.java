package com.capgemini.mappers;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PlaceEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PlaceTO;
import com.capgemini.types.RentalTO;

import java.util.Collection;
import java.util.stream.Collectors;

public class PlaceMapper {
    public static PlaceTO toPlaceTO(PlaceEntity placeEntity) {
        if (placeEntity == null)
            return null;

        Collection<EmployeeTO> employeeTOs = EmployeeMapper.map2TOs(placeEntity.getEmployees());
        Collection<RentalTO> rentalStartTOs = RentalMapper.map2TOs(placeEntity.getRentalEntitiesStart());
        Collection<RentalTO> rentalEndTOs = RentalMapper.map2TOs(placeEntity.getRentalEntitiesEnd());

        return new PlaceTO.PlaceTOBuilder()
                .withAddress(placeEntity.getAddress())
                .withPhone(placeEntity.getPhone())
                .withEmployees(employeeTOs)
                .withRentalStart(rentalStartTOs)
                .withRentalEnd(rentalEndTOs)
                .withId(placeEntity.getId())
                .build();
    }

    public static PlaceEntity toPlaceEntity(PlaceTO placeTO) {
        if (placeTO == null)
            return null;

        PlaceEntity placeEntity = new PlaceEntity();
        placeEntity.setAddress(placeTO.getAddress());
        placeEntity.setPhone(placeTO.getPhone());
        placeEntity.setId(placeTO.getId());

        Collection<EmployeeEntity> employees = EmployeeMapper.map2Entities(placeTO.getEmployees());
        Collection<RentalEntity> rentalStartEntities = RentalMapper.map2Entities(placeTO.getRentalStart());
        Collection<RentalEntity> rentalEndEntities = RentalMapper.map2Entities(placeTO.getRentalEnd());

        placeEntity.setEmployees(employees);
        placeEntity.setRentalEntitiesStart(rentalStartEntities);
        placeEntity.setRentalEntitiesEnd(rentalEndEntities);

        return placeEntity;
    }
    public static Collection<PlaceTO> map2TOs (Collection<PlaceEntity> placeEntities){
        return placeEntities.stream().map(PlaceMapper::toPlaceTO).collect(Collectors.toList());
    }
    public static Collection<PlaceEntity> map2Entities (Collection<PlaceTO> placeTOs){
        return placeTOs.stream().map(PlaceMapper::toPlaceEntity).collect(Collectors.toList());
    }
}