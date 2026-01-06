package com.tka.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tka.entity.Employee;
import com.tka.model.UserLogin;
import com.tka.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // allow Angular access
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // ✅ Login API
    @PostMapping("/login")
    public Employee login(@RequestBody UserLogin userLogin) {
        return service.login(userLogin.getUsername(), userLogin.getPassword());
    }

    // ✅ Register New Employee
    @PostMapping("/register")
    public boolean register(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    // ✅ Get All Employees
    @GetMapping("/getAll")
    public List<Employee> getAllEmployee() {
        return service.getAllEmployee();
    }

    // ✅ Get Employee By ID
    @GetMapping("/getById")
    public Employee getEmpById(@RequestParam int id) {
        return service.getEmpById(id);
    }

    // ✅ Update Employee
    @PutMapping("/update")
    public boolean updateEmployee(@RequestBody Employee employee) {
        return service.updateEmployee(employee);
    }

    // ✅ Delete Employee
    @DeleteMapping("/delete")
    public boolean deleteEmployee(@RequestParam int id) {
        return service.deleteEmployee(id);
    }
    
    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam int empId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword) {

        return service.changePassword(empId, oldPassword, newPassword, confirmPassword);
    }
}
