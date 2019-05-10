package extractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface RequestExtractor<T> {
    T extract(HttpServletRequest request) throws IOException, ServletException;
}
