package br.com.livro.rest;

import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {

    // Não é mais necessario por conta da implementação das intefaces MessageBodyWriter<Object>, MessageBodyReader<Object>
    // para utilizar o Gson
    //@Override
    //public Set<Object> getSingletons() {
    //    Set<Object> singletons = new HashSet<>();
    //    singletons.add(new JettisonFeature());
    //    return singletons;
    //}

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.provider.packages", "br.com.livro");
        return properties;
    }
}
