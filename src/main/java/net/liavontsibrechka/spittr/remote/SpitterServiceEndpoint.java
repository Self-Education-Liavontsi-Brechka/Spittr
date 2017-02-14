package net.liavontsibrechka.spittr.remote;

import net.liavontsibrechka.spittr.Spittle;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "SpitterService")
// Should extend if no JAX-WS exporter defined
// public class SpitterServiceEndpoint extends SpringBeanAutowiringSupport {
public class SpitterServiceEndpoint {
    @Autowired
    SpitterService spitterService;

    @WebMethod
    public void addSpittle(Spittle spittle) {
        spitterService.saveSpittle(spittle);
    }
}
