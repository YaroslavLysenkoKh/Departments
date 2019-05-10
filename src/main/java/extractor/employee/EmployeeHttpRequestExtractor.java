package extractor.employee;

import entity.Employee;
import extractor.RequestExtractor;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static command.Command.FORWARD_ERROR_PAGE;

public class EmployeeHttpRequestExtractor implements RequestExtractor<Employee> {
    @Override
    public Employee extract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = new Employee();
        String id = request.getParameter("employeeId");
        if (!StringUtils.isEmpty(id))
            employee.setId(Long.parseLong(id));
        employee.setEmail(request.getParameter("email"));
        String salary = request.getParameter("salary");
        if (StringUtils.isBlank(salary)) {
            employee.setSalary(new BigDecimal("0"));
        } else {
            try {
                employee.setSalary(new BigDecimal(salary));
            } catch (NumberFormatException | NullPointerException exception) {
                request.setAttribute("exception", exception);
                request.getRequestDispatcher(FORWARD_ERROR_PAGE).forward(request, response);
            }
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
