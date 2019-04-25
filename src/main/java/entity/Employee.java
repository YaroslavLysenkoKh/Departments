package entity;

import net.sf.oval.constraint.*;

import java.util.Date;
import java.util.Objects;

public class Employee {
    @NotNull
    @NotEmpty
    private Long id;
    @NotNull
    @NotEmpty
    @Length(min = 5)
    @MatchPattern(message = "email:mail must be like this: email.kh@department.com", pattern = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\." +
            "(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\." +
            "[0-9]{1,3}))(:[0-9]{1,5})?$")

    private String email;
    @NotNull
    @NotEmpty
    @MatchPattern(message = "minimum eight characters, at least one letter and one number", pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;
    @NotNull
    @NotEmpty
    @MatchPattern(message = "salary: cannot be less or equals than 0", pattern = "^\\d*$")
    @Min(value = 1)
    private Integer salary;
    @NotNull
    @NotEmpty
    @DateRange(max = "today", min = "1900-01-01", message = "birth date: cannot be after current date")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalary() {
        return salary;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setSalary(Integer salary) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId()) &&
                Objects.equals(getEmail(), employee.getEmail()) &&
                Objects.equals(getPassword(), employee.getPassword()) &&
                Objects.equals(getSalary(), employee.getSalary()) &&
                Objects.equals(getBirthDate(), employee.getBirthDate()) &&
                Objects.equals(departmentId, employee.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getSalary(), getBirthDate(), departmentId);
    }
}
