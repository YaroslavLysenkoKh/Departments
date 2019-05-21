package com.util.spring;

import com.command.Controller;
import com.filter.EncodingRequestFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.*;
import java.util.EnumSet;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.scan("com");
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new Controller(ctx));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        servletContext.addListener(new ContextLoaderListener(ctx));
//        loadDefaultFilters(servletContext);
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
