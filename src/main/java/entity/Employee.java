package entity;

import net.sf.oval.constraint.*;
import util.oval.employee.EmailCheck;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Employee {

    private Long id;
    @CheckWith(value = EmailCheck.class, message = "Employee with such email already exists")
    @Length(min = 5, max = 45)
    @MatchPattern(message = "mail must be like this: email.kh@department.com", pattern = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\." +
            "(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\." +
            "[0-9]{1,3}))(:[0-9]{1,5})?$")
    @NotNull
    @NotEmpty(message = "cannot be empty")
    private String email;
    @MatchPattern(message = "only numbers", pattern = "^\\d*$")
    @Min(value = 1, message = "cannot be empty and less or equals than 0")
    @NotNull(message = "only numbers allowed")
    @Length()
    private BigDecimal salary;
    @DateRange(max = "today", message = "cannot be after today date", format = "dd-MM-yyyy")
    @NotNull(message = "cannot be empty")
    @NotEmpty(message = "cannot be empty")
    private Date birthDate;
    @NotNull
    @NotEmpty
    private Long departmentId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId()) &&
                Objects.equals(getEmail(), employee.getEmail()) &&
                Objects.equals(getSalary(), employee.getSalary()) &&
                Objects.equals(getBirthDate(), employee.getBirthDate()) &&
                Objects.equals(getDepartmentId(), employee.getDepartmentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getSalary(), getBirthDate(), getDepartmentId());
    }
}
