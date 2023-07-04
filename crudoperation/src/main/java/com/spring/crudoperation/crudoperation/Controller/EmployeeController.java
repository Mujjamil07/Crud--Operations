package com.spring.crudoperation.crudoperation.Controller;



import com.spring.crudoperation.crudoperation.Model.MyEmployee;
import com.spring.crudoperation.crudoperation.Repository.EmployeeRepository;
import com.spring.crudoperation.crudoperation.Service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@Transactional
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("/addEmployee")
    public String displayForm(){
        return "addEmployee.html";
    }

    @GetMapping("/")
     public String getAllEmployee(Model model)
    {
        model.addAttribute("employee" , employeeRepository.findAll());
        return "index";
    }


    @PostMapping("/index")
    public String saveEmployee(Model model ,@ModelAttribute MyEmployee myEmployee){
        model.addAttribute("employee",myEmployee);
        employeeService.saveEmployee(myEmployee);
        return "redirect:index";
    }

    @GetMapping("/index")
    public String dispList(){
        return "redirect:/";
    }
// Delete Form ...................................................
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }
    //Update Employee Form.....................................

    @GetMapping("/updateEmp/{id}")
    public String updateEmployee(@PathVariable("id") Long id , Model model){

         model.addAttribute("employee",employeeService.getEmp(id));
         return "updateForm.html";
    }

    @PostMapping("/update/{id}")
    public String updateEmp(@PathVariable("id") Model model, @ModelAttribute MyEmployee myEmployee){
        employeeRepository.save(myEmployee);
        model.addAttribute("employee",employeeRepository.findAll());
        return "redirect:/";
    }

    //Mapping For React..........................................
    @ResponseBody
    @GetMapping("/getData")
    public List<MyEmployee> getAll(){
        return employeeService.getAllEmployee();
    }

    @ResponseBody
    @PostMapping("/datasave")
    public String addData(@RequestBody MyEmployee myEmployee){
        return employeeService.saveEmployee(myEmployee);
    }

    @ResponseBody
    @DeleteMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "File Deleted";

    }


    @ResponseBody
    @GetMapping("/getEmployeeById/{id}")
    public Optional<MyEmployee> getEmpId(@PathVariable Long id){
        return employeeRepository.findById(id);
    }


//    @ResponseBody
//    @PutMapping("/updateEmployee/{id}")
//    public ResponseEntity<MyEmployee> updateEmployee(@RequestBody MyEmployee myEmployee, @PathVariable Long id){
//        Optional<MyEmployee> updateEmployee = employeeRepository.findById(id);
//        updateEmployee.setFirstName(myEmployee.getFirstName());
//        updateEmployee.setLastName(myEmployee.getLastName());
//        updateEmployee.setEmailId(myEmployee.getEmailId());
//        employeeRepository.save(updateEmployee);
//        return ResponseEntity.ok(updateEmployee);
//    }
}



