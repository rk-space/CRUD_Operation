package com.tka.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.entity.Employee;

@Repository
public class EmployeeDao {

    @Autowired
    private SessionFactory sf;

    // ✅ Register/Save Employee
    public boolean save(Employee emp) {
        Transaction tx = null;
        boolean isRegistered = false;
        try (Session session = sf.openSession()) {
            tx = session.beginTransaction();
            session.save(emp);
            tx.commit();
            isRegistered = true;
        } catch (Exception e) {
            e.printStackTrace();
            isRegistered = false;
        }
        return isRegistered;
    }

    // ✅ Login
    public Employee login(String email, String password) {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "FROM Employee WHERE email = :email AND password = :password", Employee.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();
        }
    }

    // ✅ Get All Employees
    public List<Employee> getAllEmployee() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Employee", Employee.class).list();
        }
    }

    // ✅ Get Employee by ID
    public Employee getEmpById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Employee.class, id);
        }
    }

    // ✅ Update Employee
    public boolean updateEmployee(Employee emp) {
        Transaction tx = null;
        boolean isUpdated = false;
        try (Session session = sf.openSession()) {
            tx = session.beginTransaction();
            session.update(emp);
            tx.commit();
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    // ✅ Delete Employee
    public boolean deleteEmployee(int id) {
        Transaction tx = null;
        boolean isDeleted = false;
        try (Session session = sf.openSession()) {
            tx = session.beginTransaction();
            Employee emp = session.get(Employee.class, id);
            if (emp != null) {
                session.delete(emp);
                tx.commit();
                isDeleted = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
}
