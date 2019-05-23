package comm.util.spring;

import comm.command.Controller;
import comm.filter.EncodingRequestFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ApplicationConfiguration.class);
//        ctx.scan("comm", "net.sf");
        servletContext.addListener(new ContextLoaderListener(ctx));

        //create dispatcher context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new Controller(dispatcherContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

        loadDefaultFilters(servletContext);
    }

    private void addEncodingFilter(ServletContext servletContext) {
        FilterRegistration encodingFilter = servletContext.addFilter("encodingFilter", EncodingRequestFilter.class);
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("encodingRequest", "true");
        encodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, "/*");
    }

    protected void loadDefaultFilters(ServletContext servletContext) {
        addEncodingFilter(servletContext);
    }
}
