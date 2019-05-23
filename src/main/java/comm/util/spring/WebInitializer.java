package comm.util.spring;

import comm.command.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(ApplicationConfiguration.class);
        ctx.scan("comm", "net.sf");
        servletContext.addListener(new ContextLoaderListener(ctx));

        //create dispatcher context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new Controller(dispatcherContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

        //register filters
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("endcodingFilter", new CharacterEncodingFilter());
        filterRegistration.setInitParameter("encoding", "UTF-8");
        filterRegistration.setInitParameter("forceEncoding", "true");
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
    }
}
