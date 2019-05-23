package comm.extractor.employee;

import comm.entity.Employee;
import comm.extractor.RequestExtractor;
import comm.service.departments.DepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EmployeeHttpRequestExtractor implements RequestExtractor<Employee> {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Employee extract(HttpServletRequest request, HttpServletResponse response) {
        Employee employee = new Employee();
        String id = request.getParameter("employeeId");
        if (!StringUtils.isEmpty(id))
            employee.setId(Long.parseLong(id));
        employee.setEmail(request.getParameter("email"));
        employee.setDepartment(departmentService.getById(Long.parseLong(request.getParameter("departmentId"))));
        setSalary(employee, request);
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

    private void setSalary(Employee employee, HttpServletRequest request) {
        String salary = request.getParameter("salary");
        if (StringUtils.isBlank(salary)) {
            employee.setSalary(new BigDecimal("0"));
        } else {
            if (salary.matches("(^\\d*$)|(^-?\\d*\\.\\d{2}$)")) {
                employee.setSalary(new BigDecimal(salary));
            } else {
                employee.setSalary(null);
            }
        }
    }
}
