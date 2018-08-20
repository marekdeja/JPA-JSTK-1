package com.capgemini.domain;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "position")
public class PositionEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 45)
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Collection<EmployeeEntity> employees;


    public PositionEntity() {
    }

    public PositionEntity(String name, Collection<EmployeeEntity> employees) {
        this.name = name;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<EmployeeEntity> employees) {
        this.employees = employees;
    }
}
