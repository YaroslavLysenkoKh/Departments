package comm.entity;

import comm.util.oval.employee.EmailCheck;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long employeeId;
    @CheckWith(value = EmailCheck.class, message = "Employee with such email already exists")
    @Length(min = 5, max = 45)
    @MatchPattern(message = "mail must be like this: email.kh@department.com", pattern = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\." +
            "(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\." +
            "[0-9]{1,3}))(:[0-9]{1,5})?$")
    @NotNull
    @NotEmpty(message = "cannot be empty")
    @Column(name = "email")
    private String email;
    @MatchPattern(message = "only numbers", pattern = "(^\\d*$)|(^-?\\d*\\.\\d{2}$)")
    @Min(value = 1, message = "cannot be empty and less or equals than 0")
    @NotNull(message = "only numbers allowed")
    @Length()
    @Column(name = "salary")
    private BigDecimal salary;
    @DateRange(max = "today", message = "cannot be after today date", format = "dd-MM-yyyy")
    @NotNull(message = "cannot be empty")
    @NotEmpty(message = "cannot be empty")
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @ManyToOne
    @JoinColumn(name = "id_department")
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

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

    public Long getId() {
        return employeeId;
    }

    public void setId(Long id) {
        this.employeeId = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Employee() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId()) &&
                Objects.equals(getEmail(), employee.getEmail()) &&
                Objects.equals(getSalary(), employee.getSalary()) &&
                Objects.equals(getBirthDate(), employee.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getSalary(), getBirthDate());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + employeeId +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", birthDate=" + birthDate +
                ", department=" + department +
                '}';
    }
}
