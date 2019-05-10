package extractor.employee;

import entity.Employee;
import extractor.RequestExtractor;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeHttpRequestExtractor implements RequestExtractor<Employee> {
    @Override
    public Employee extract(HttpServletRequest request) {
        Employee employee = new Employee();
        String id = request.getParameter("employeeId");
        if (!StringUtils.isEmpty(id))
            employee.setId(Long.parseLong(id));
        employee.setEmail(request.getParameter("email"));
        String salary = request.getParameter("salary");
        if (StringUtils.isBlank(salary)) {
            employee.setSalary(0);
        } else {
            employee.setSalary(Integer.parseInt(salary));
        }
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
