package net.liavontsibrechka.spittr.remote;

import net.liavontsibrechka.spittr.Spitter;
import net.liavontsibrechka.spittr.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;
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

    @Bean
    public HttpInvokerProxyFactoryBean httpSpitterService() {
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setServiceUrl("http://localhost:8080/Spitter/spitter.service");
        proxy.setServiceInterface(SpitterService.class);
        return proxy;
    }

    @Bean
    public JaxWsPortProxyFactoryBean jaxWsSpitterService() throws MalformedURLException {
        JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
        proxy.setWsdlDocumentUrl(new URL("http://localhost:8080/services/SpitterService?wsdl"));
        proxy.setServiceName("spitterService");
        proxy.setPortName("spitterServiceHttpPort");
        proxy.setServiceInterface(SpitterService.class);
        proxy.setNamespaceUri("http://spitter.com");
        return proxy;
    }

    public List<Spittle> getSpittles(String userName) {
        Spitter spitter = spitterService.getSpitter(userName);
        return spitterService.getSpittlesForSpitter(spitter);
    }
}