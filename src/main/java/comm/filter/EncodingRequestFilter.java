package comm.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter
public class EncodingRequestFilter implements Filter {

    private String encoding;


    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter("encodingRequest");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestEncoding = httpRequest.getCharacterEncoding();
        if (requestEncoding == null) {
            httpRequest.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //NOP
    }
}
