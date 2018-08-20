package com.capgemini.types;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;

public class PositionTO {

    private String name;
    private Collection<EmployeeTO> employees;

    private Long id;

    public PositionTO() {

    }

    public PositionTO(String name, Collection<EmployeeTO> employees) {
        this.name = name;
        this.employees = employees;
    }

    public PositionTO(String name, Collection<EmployeeTO> employees, Long id) {
        this.name = name;
        this.employees = employees;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Collection<EmployeeTO> getEmployees() {
        return employees;
    }

    public Long getId() {
        return id;
    }

    public static PositionTOBuilder builder() {
        return new PositionTOBuilder();
    }

    public static class PositionTOBuilder {
        private String name;
        private Collection<EmployeeTO> employees = new HashSet<>();

        private Long id;

        public PositionTOBuilder() {
            super();
        }

        public PositionTOBuilder withName(String name) {
            this.name = name;
            return this;
        }
        public PositionTOBuilder withEmployee(EmployeeTO employee) {
            this.employees.add(employee);
            return this;
        }
        public PositionTOBuilder withEmployees(Collection<EmployeeTO> employees) {
            this.employees.addAll(employees);
            return this;
        }
        public PositionTOBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public PositionTO build() {
            checkBeforeBuild();
            return new PositionTO( name,  employees);
        }

        private void checkBeforeBuild() {
            if (CollectionUtils.isEmpty(employees) || name == null ) {
                throw new RuntimeException("Incorrect position to be created");
            }

        }

    }
}
