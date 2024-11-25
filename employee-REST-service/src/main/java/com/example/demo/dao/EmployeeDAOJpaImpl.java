package com.example.demo.dao;

import com.example.demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    //fields for entity-manager
    private EntityManager entityManager;

    //constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        //query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",Employee.class);

        //result for the query
        List<Employee> employees = theQuery.getResultList();

        //return statement
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        //get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);
        //return employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
         //save Employee
        // if id == 0 then insert/save
        // else update,
        Employee dbEmployee = entityManager.merge(theEmployee);
        //return updated employee
        return dbEmployee;
    }

    @Override
    public void delete(int theId) {
        //get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);
        //remove employee
        entityManager.remove(theEmployee);
    }
}
