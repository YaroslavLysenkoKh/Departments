package comm.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class EmployeeDto {

    private String employeeId;

    private String email;

    private Date birthDate;

    private Long departmentId;

    private BigDecimal salary;


    public String getId() {
        return employeeId;
    }

    public void setId(String id) {
        this.employeeId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
