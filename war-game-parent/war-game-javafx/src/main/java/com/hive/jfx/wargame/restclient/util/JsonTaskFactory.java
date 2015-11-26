package com.hive.jfx.wargame.restclient.util;

import java.util.List;

import javafx.collections.ObservableList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hive.jfx.wargame.exception.DataIntegrityException;
import com.hive.jfx.wargame.exception.RemoteServiceException;

/**
 * Created by tlanglois on 6/25/15.
 */
@Component
public class JsonTaskFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonGetTask.class);

    @Value("${rest.base.url:http://localhost:8080/wargameServer}")
    private String baseUrl;

    @Value("${rest.synchronous:true}")
    private boolean synchronous;

    // @Autowired
    // AuthenticationManager authenticationManager;
    // @Autowired
    // ErrorResponseFilter errorResponseFilter;

    public <T> ObservableList<T> get(final GenericType<List<T>> genericType, final String endpoint, final String... pathVariables)
                    throws RemoteServiceException {
        try {
            return JsonGetListTask.get(genericType, buildWebTarget(endpoint, pathVariables), synchronous);
        } catch (RemoteServiceException e) {
            // errorResponseFilter.postErrorResponse(e);
            throw e;
        }
    }

    public <T> T getOne(final GenericType<T> genericType, final String endpoint, String... pathVariables) throws RemoteServiceException {
        try {
            return JsonGetTask.get(genericType, buildWebTarget(endpoint, pathVariables));
        } catch (RemoteServiceException e) {
            // errorResponseFilter.postErrorResponse(e);
            throw e;
        }
    }

    public <T> ObservableList<T> put(final Object object, final GenericType<T> genericType, final String endpoint, final String... pathVariables)
                    throws RemoteServiceException {
        try {
            return JsonPutTask.put(object, genericType, buildWebTarget(endpoint, pathVariables), synchronous);
        } catch (RemoteServiceException e) {
            // errorResponseFilter.postErrorResponse(e);
            throw e;
        }
    }

    public <T> T post(final Object object, final GenericType<T> genericType, final String endpoint, final String... pathVariables)
                    throws RemoteServiceException {
        try {
            return JsonPostTask.post(object, genericType, buildWebTarget(endpoint, pathVariables), synchronous);
        } catch (RemoteServiceException e) {
            // errorResponseFilter.postErrorResponse(e);
            throw e;
        }
    }

    public void delete(final String endpoint, final String... pathVariables) throws DataIntegrityException {
        try {
            JsonDeleteTask.delete(buildWebTarget(endpoint, pathVariables), synchronous);
        } catch (RemoteServiceException e) {
            // errorResponseFilter.postErrorResponse(e);
            throw e;
        }
    }

    private WebTarget buildWebTarget(String endpoint, String... pathVariables) {
        Client client = ClientBuilder.newBuilder().build();
        // client.register(errorResponseFilter);
        WebTarget webTarget = client.target(baseUrl + endpoint);
        // webTarget.register(new Authenticator(authenticationManager));
        int i = 0;
        for (String pathVariable : pathVariables) {
            webTarget = webTarget.path("/{var" + i + "}").resolveTemplate("var" + i, pathVariable);
        }

        return webTarget;
    }
}
