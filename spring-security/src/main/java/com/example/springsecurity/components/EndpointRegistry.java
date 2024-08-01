package com.example.springsecurity.components;

import com.example.springsecurity.models.Endpoint;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class EndpointRegistry {

    private final ApplicationContext applicationContext;
    public static Endpoint[] ENDPOINTS;

    public EndpointRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

        handlerMethods.forEach((requestMappingInfo, handlerMethod) -> {
            Set<RequestMethod> methods = requestMappingInfo.getMethodsCondition().getMethods();
            Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();
            List<Endpoint> list = new ArrayList<>();
            for (RequestMethod method : methods) {
                for (String pattern : patterns) {
                    System.out.println("Method: " + method + " URL: " + pattern);
                    list.add(new Endpoint(HttpMethod.valueOf(method.name()), pattern));
                }
            }
            ENDPOINTS = list.toArray(new Endpoint[0]);
        });
    }
}
