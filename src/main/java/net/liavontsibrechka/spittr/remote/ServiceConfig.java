package net.liavontsibrechka.spittr.remote;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
}