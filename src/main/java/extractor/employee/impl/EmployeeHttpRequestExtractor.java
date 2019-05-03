package extractor.employee.impl;

import entity.Employee;
import extractor.employee.EmployeeRequestExtractor;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeHttpRequestExtractor implements EmployeeRequestExtractor {
    @Override
    public Employee extract(HttpServletRequest request) {
        Employee employee = new Employee();
        String id = request.getParameter("employeeId");
        if (id.length() > 0)
            employee.setId(Long.parseLong(id));
        employee.setEmail(request.getParameter("email"));
        employee.setPassword(request.getParameter("password"));
        employee.setSalary(Integer.parseInt(request.getParameter("salary")));
        employee.setDepartmentId(Long.parseLong(request.getParameter("departmentId")));
        setEmployeeDate(employee, request);
        return employee;
    }

    private void setEmployeeDate(Employee employee, HttpServletRequest request) {
        try {
            DateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date newDate = targetFormat.parse(targetFormat.format(new SimpleDateFormat("yyyy-MM-dd").
                    parse(request.getParameter("birthDate"))));
            employee.setBirthDate(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
