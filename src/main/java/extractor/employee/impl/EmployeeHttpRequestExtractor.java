package extractor.employee.impl;

import entity.Employee;
import extractor.employee.EmployeeRequestExtractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeHttpRequestExtractor implements EmployeeRequestExtractor {
    @Override
    public Employee extract(HttpServletRequest request) throws IOException, ServletException {
        Employee employee = new Employee();
        employee.setId(Long.parseLong(request.getParameter("employeeId")));
        employee.setEmail(request.getParameter("email"));
        employee.setPassword(request.getParameter("password"));
        employee.setSalary(Integer.parseInt(request.getParameter("salary")));
        employee.setDepartmentId(Long.parseLong(request.getParameter("departmentId")));
        try {
            employee.setBirthDate(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("birthDate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
