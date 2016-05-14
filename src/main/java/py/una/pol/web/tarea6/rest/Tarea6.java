package py.una.pol.web.tarea6.rest;

import py.una.pol.web.tarea6.rest.interceptor.AuthenticationFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Tarea6 extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<Class<?>>(2);
        set.add(JacksonConfig.class);
        set.add(CustomerService.class);
        set.add(ItemService.class);
        set.add(ProviderService.class);
        set.add(UserService.class);
        set.add(AuthenticationFilter.class);
        return set;
    }
}
