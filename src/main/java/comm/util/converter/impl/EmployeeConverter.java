package comm.util.converter.impl;

import comm.dto.EmployeeDto;
import comm.entity.Employee;
import comm.service.departments.DepartmentService;
import comm.util.converter.DtoConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EmployeeConverter implements DtoConverter<EmployeeDto, Employee> {
    @Autowired
    DepartmentService departmentService;

    @Override
    public Employee convert(EmployeeDto model) {
        Employee employee = new Employee();
        if (!StringUtils.isEmpty(model.getId()))
            employee.setId(Long.parseLong(model.getId()));
        employee.setEmail(model.getEmail());
        employee.setDepartment(departmentService.getById(model.getDepartmentId()));
        employee.setSalary(model.getSalary());
        setEmployeeDate(employee, model);
        return employee;
    }

    private void setEmployeeDate(Employee employee, EmployeeDto dto) {
        try {
            DateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date newDate = targetFormat.parse(targetFormat.format(new SimpleDateFormat("yyyy-MM-dd").
                    parse(dto.getBirthDate())));
            employee.setBirthDate(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}