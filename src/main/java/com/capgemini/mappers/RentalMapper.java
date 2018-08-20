package com.capgemini.mappers;

import com.capgemini.domain.RentalEntity;
import com.capgemini.types.RentalTO;

import java.util.Collection;
import java.util.stream.Collectors;

public class RentalMapper {
    public static RentalTO toRentalTO(RentalEntity rentalEntity) {
        if (rentalEntity == null)
            return null;

        return new RentalTO.RentalTOBuilder()
                .withAmount(rentalEntity.getAmount())
                .withStartDate(rentalEntity.getStartDate())
                .withEndDate(rentalEntity.getEndDate())
                .build();
    }

    public static RentalEntity toRentalEntity(RentalTO rentalTO) {
        if (rentalTO == null)
            return null;
    RentalEntity rentalEntity = new RentalEntity();
    rentalEntity.setAmount(rentalTO.getAmount());
    rentalEntity.setStartDate(rentalTO.getStartDate());
    rentalEntity.setEndDate(rentalTO.getEndDate());

    return rentalEntity;
    }

    public static Collection<RentalTO> map2TOs (Collection<RentalEntity> rentalEntities){
        return rentalEntities.stream().map(RentalMapper::toRentalTO).collect(Collectors.toList());
    }
    public static Collection<RentalEntity> map2Entities (Collection<RentalTO> rentalTOs){
        return rentalTOs.stream().map(RentalMapper::toRentalEntity).collect(Collectors.toList());
    }

}

