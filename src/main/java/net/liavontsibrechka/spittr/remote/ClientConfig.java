package net.liavontsibrechka.spittr.remote;

import net.liavontsibrechka.spittr.Spitter;
import net.liavontsibrechka.spittr.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.util.List;

@Configuration
public class ClientConfig {
    @Autowired
    SpitterService spitterService;

    @Bean
    public RmiProxyFactoryBean spitterService() {
        RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
        rmiProxy.setServiceUrl("rmi://localhost/SpitterService");
        rmiProxy.setServiceInterface(SpitterService.class);
        return rmiProxy;
    }

    @Bean
    public HessianProxyFactoryBean hessiamSpitterService() {
        HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
        proxy.setServiceUrl("http://localhost:8080/Spitter/spitter.service");
        proxy.setServiceInterface(SpitterService.class);
        return proxy;
    }

    public List<Spittle> getSpittles(String userName) {
        Spitter spitter = spitterService.getSpitter(userName);
        return spitterService.getSpittlesForSpitter(spitter);
    }
}