package com.capgemini.dao.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {
}
