package entity;


import net.sf.oval.constraint.*;
import util.oval.department.NameCheck;

public class Department {

    private Long id;

    @CheckWith(value = NameCheck.class, message = "department with such name already exists")
    @MatchPattern(message = "at least one letter or number must be", pattern = "^[A-Za-z0-9\\\\u0400-\\\\u04FF]{1,}$")
    @Length(max = 20, message = "max char length is 20")
    @NotNull
    @NotEmpty(message = "cannot be empty")
    private String name;

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
