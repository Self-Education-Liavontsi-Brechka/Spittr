package net.liavontsibrechka.spittr.remote;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class ServiceConfig {
    @Bean
    public RmiServiceExporter rmiExporter(SpitterService spitterService) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(spitterService);
        exporter.setServiceName("SpitterService");
        exporter.setServiceInterface(SpitterService.class);
        exporter.setRegistryHost("rmi.spitter.com");
        exporter.setRegistryPort(1199);
        return exporter;
    }

    @Bean
    public HessianServiceExporter hessianExportedSpitterService(SpitterService spitterService) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(spitterService);
        exporter.setServiceInterface(SpitterService.class);
        return exporter;
    }

    @Bean
    public HttpInvokerServiceExporter httpExportedSpitterService(SpitterService spitterService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(spitterService);
        exporter.setServiceInterface(SpitterService.class);
        return exporter;
    }

    @Bean
    public SimpleJaxWsServiceExporter jaxWsExporter() {
        SimpleJaxWsServiceExporter simpleJaxWsServiceExporter = new SimpleJaxWsServiceExporter();
        simpleJaxWsServiceExporter.setBaseAddress("http://localhost:8888/services/");
        return simpleJaxWsServiceExporter;
    }
}
