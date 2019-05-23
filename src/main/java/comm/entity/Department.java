package comm.entity;

import comm.util.oval.department.NameCheck;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CheckWith(value = NameCheck.class, message = "department with such name already exists")
    @MatchPattern(message = "at least one letter or number must be", pattern = "^[A-Za-z0-9\\\\u0400-\\\\u04FF]{1,}$")
    @Length(max = 20, message = "max char length is 20")
    @NotNull
    @Column(name = "name")
    @NotEmpty(message = "cannot be empty")
    private String name;

    @OneToMany(mappedBy = "department", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Employee> employeeList;

    public Department() {
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }
}
