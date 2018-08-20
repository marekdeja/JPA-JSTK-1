package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.CustomerEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.CustomerTO;
import com.capgemini.types.RentalTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static CustomerTO toCustomerTO(CustomerEntity customerEntity) {
        if (customerEntity == null)
            return null;

        Collection<RentalTO> rentalTOs = RentalMapper.map2TOs(customerEntity.getRentals());

        return new CustomerTO.CustomerTOBuilder()
                .withName(customerEntity.getName())
                .withSurname(customerEntity.getSurname())
                .withBirth(customerEntity.getBirth())
                .withEmail(customerEntity.getEmail())
                .withPhone(customerEntity.getPhone())
                .withCreditCard(customerEntity.getCreditCard())
                .withRentals(rentalTOs)
                .build();

    }

    public static CustomerEntity toCustomerEntity(CustomerTO customerTO) {
        if (customerTO == null)
            return null;
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerTO.getName());
        customerEntity.setSurname(customerTO.getSurname());
        customerEntity.setBirth(customerTO.getBirth());
        customerEntity.setEmail(customerTO.getEmail());
        customerEntity.setPhone(customerTO.getPhone());
        customerEntity.setCreditCard(customerTO.getCreditCard());

        Collection<RentalEntity> rentals = RentalMapper.map2Entities(customerTO.getRentals());

        customerEntity.setRentals(rentals);

        return customerEntity;

    }
public static Collection<CustomerTO> map2TOs (Collection<CustomerEntity> customerEntities){
        return customerEntities.stream().map(CustomerMapper::toCustomerTO).collect(Collectors.toList());
}
    public static Collection<CustomerEntity> map2Entities (Collection<CustomerTO> customerTOs){
        return customerTOs.stream().map(CustomerMapper::toCustomerEntity).collect(Collectors.toList());
    }

}
