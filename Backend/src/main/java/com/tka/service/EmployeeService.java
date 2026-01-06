package com.tka.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tka.dao.EmployeeDao;
import com.tka.entity.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao dao;

    // Save/Register
    public boolean saveEmployee(Employee employee) {
        return dao.save(employee);
    }

    // Login
    public Employee login(String username, String password) {
        return dao.login(username, password);
    }

    // Get all employees
    public List<Employee> getAllEmployee() {
        return dao.getAllEmployee();
    }

    // Get employee by ID
    public Employee getEmpById(int id) {
        return dao.getEmpById(id);
    }

    // Update employee
    public boolean updateEmployee(Employee employee) {
        return dao.updateEmployee(employee);
    }

    // Delete employee
    public boolean deleteEmployee(int id) {
        return dao.deleteEmployee(id);
    }
    
    
    public String changePassword(int empId, String oldPassword, String newPassword, String confirmPassword) {

        Employee emp = dao.getEmpById(empId);

        if (emp == null) {
            return "Employee not found";
        }

        // Old password check
        if (!emp.getPassword().equals(oldPassword)) {
            return "Old password is incorrect";
        }

        // New & confirm match
        if (!newPassword.equals(confirmPassword)) {
            return "New password and confirm password do not match";
        }

        emp.setPassword(newPassword);

        boolean updated = dao.updateEmployee(emp);

        if (updated) {
            return "Password changed successfully";
        } else {
            return "Password update failed";
        }
    }
}
