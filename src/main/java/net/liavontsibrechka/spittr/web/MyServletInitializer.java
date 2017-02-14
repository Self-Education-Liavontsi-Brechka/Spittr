package net.liavontsibrechka.spittr.web;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MyServletInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        ServletRegistration.Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
//        myServlet.addMapping("/custom");
//
//        FilterRegistration.Dynamic myFilter = servletContext.addFilter("myFilter", MyFilter.class);
//        myFilter.addMappingForUrlPatterns(null, false, "/custom/*");
//
//        DispatcherServlet dispatcherServlet = new DispatcherServlet();
//        ServletRegistration.Dynamic registration = servletContext.addServlet("appServlet", dispatcherServlet);
//        registration.addMapping("/");
//        registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads"));
    }
}
