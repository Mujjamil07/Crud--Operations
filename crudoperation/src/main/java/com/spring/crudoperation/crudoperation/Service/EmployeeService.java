package com.spring.crudoperation.crudoperation.Service;


import com.spring.crudoperation.crudoperation.Model.MyEmployee;
import com.spring.crudoperation.crudoperation.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Object getEmp(Long id){
        return employeeRepository.findById(id);
    }
    public List<MyEmployee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public String saveEmployee(MyEmployee myEmployee){
          employeeRepository.save(myEmployee);
          return "Employee Saved";

    }

    public Optional<MyEmployee> updateemployee(Long id){
        return employeeRepository.findById(id);
    }

    public String deleteEmployee(Long id){
        employeeRepository.deleteById(id);
        return "Record Deleted Successfully";
    }


}
