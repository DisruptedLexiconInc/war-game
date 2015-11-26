package com.hive.jfx.wargame.restclient.util;

import javafx.concurrent.Task;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hive.jfx.wargame.exception.RemoteServiceException;

public class JsonPostTask<T> extends Task<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPostTask.class);

    private final GenericType<T> genericType;
    private final Object object;
    private final WebTarget webTarget;

    protected JsonPostTask(final Object object, final GenericType<T> genericType, WebTarget webTarget) {
        this.object = object;
        this.genericType = genericType;
        this.webTarget = webTarget;
    }

    public static <T> T post(final Object object, final GenericType<T> genericType, WebTarget webTarget, boolean synchronous) throws RemoteServiceException {
        final JsonPostTask<T> retrievalTask = new JsonPostTask<>(object, genericType, webTarget);

        return retrievalTask.call();
    }

    @Override
    protected T call() throws RemoteServiceException {
        try {
            T thing = webTarget.request().post(Entity.json(object), genericType);

            return thing;
        } catch (ResponseProcessingException e) {
            RemoteServiceException rse = (RemoteServiceException) e.getCause();
            LOGGER.debug(rse.getMessage(), e);
            throw rse;
        } catch (Exception e) {
            LOGGER.debug(e.getMessage(), e);
            throw new RemoteServiceException(e);
        }
    }

}
