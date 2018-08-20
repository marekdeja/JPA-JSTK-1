package com.capgemini.mappers;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PositionEntity;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PositionTO;

import java.util.Collection;
import java.util.stream.Collectors;

public class PositionMapper {
    public static PositionTO toPositionTO(PositionEntity positionEntity) {
        if (positionEntity == null)
            return null;

        Collection<EmployeeTO> employeeTOs = EmployeeMapper.map2TOs(positionEntity.getEmployees());

        return new PositionTO.PositionTOBuilder()
                .withName(positionEntity.getName())
                .withEmployees(employeeTOs)
                .build();
    }
    public static PositionEntity toPositionEntity(PositionTO positionTO){
        if (positionTO==null)
            return null;
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setName(positionTO.getName());

        Collection<EmployeeEntity> employees = EmployeeMapper.map2Entities(positionTO.getEmployees());

        positionEntity.setEmployees(employees);

        return positionEntity;
    }

    public static Collection<PositionTO> map2TOs (Collection<PositionEntity> positionEntities){
        return positionEntities.stream().map(PositionMapper::toPositionTO).collect(Collectors.toList());
    }
    public static Collection<PositionEntity> map2Entities (Collection<PositionTO> positionTOs){
        return positionTOs.stream().map(PositionMapper::toPositionEntity).collect(Collectors.toList());
    }
}
