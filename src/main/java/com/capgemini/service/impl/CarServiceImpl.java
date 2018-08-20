package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carRepository;

    @Override
    public CarTO saveCar(CarTO carTO) {
        CarEntity carEntity = carRepository.save(CarMapper.toCarEntity(carTO));
        return CarMapper.toCarTO(carEntity);
    }

    @Override
    public void deleteCar(CarTO carTO) {
        carRepository.delete(CarMapper.toCarEntity(carTO));
    }

    @Override
    public CarTO changeData(CarTO carTO) {
        CarEntity carEntity = carRepository.update(CarMapper.toCarEntity(carTO));
        return CarMapper.toCarTO(carEntity);
    }


    @Override
    public void setEmployeeToCar (Long carId, Long employeeId){
       carRepository.setCarToEmployee(carId, employeeId);
    }

    @Override
    public Collection<CarTO> findCarByType (String type){
       List<CarEntity> cars = carRepository.findCarByType(type);
       return CarMapper.map2TOs(cars);
    }

    @Override
    public Collection<CarTO> findCarByModel (String model){
        List<CarEntity> cars = carRepository.findCarByModel(model);
        return CarMapper.map2TOs(cars);
    }
    @Override
    public Collection<CarTO> findCarByEmployee (Long id){
        List<CarEntity> cars = carRepository.findCarByEmployee(id);
        return CarMapper.map2TOs(cars);
    }


}
