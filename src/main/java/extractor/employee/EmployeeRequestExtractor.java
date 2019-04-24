package extractor.employee;

import entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface EmployeeRequestExtractor {
    Employee extract(HttpServletRequest request) throws IOException, ServletException;
}
