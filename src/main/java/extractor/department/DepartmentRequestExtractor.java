package extractor.department;

import entity.Department;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface DepartmentRequestExtractor {
    Department extract(HttpServletRequest request) throws IOException, ServletException;
}
