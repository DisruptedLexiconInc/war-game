package com.hive.jfx.wargame.restclient.util;

import javafx.concurrent.Task;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hive.jfx.wargame.exception.RemoteServiceException;

public class JsonGetTask<T> extends Task<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonGetTask.class.getName());

    private final GenericType<T> genericType;
    private final WebTarget webTarget;

    protected JsonGetTask(final GenericType<T> genericType, WebTarget webTarget) {
        this.genericType = genericType;
        this.webTarget = webTarget;
    }

    public static <T> T get(final GenericType<T> genericType, WebTarget webTarget) throws RemoteServiceException {
        final JsonGetTask<T> retrievalTask = new JsonGetTask<>(genericType, webTarget);

        return retrievalTask.call();
    }

    @Override
    protected T call() throws RemoteServiceException {
        try {
            LOGGER.trace("Making get call to: {}", webTarget.getUri());

            return webTarget.request().get(genericType);
        } catch (ResponseProcessingException e) {
            RemoteServiceException rse = (RemoteServiceException) e.getCause();
            // rse.getErrorResponse().setStatus(Integer.toString(e.getResponse().getStatus()));
            LOGGER.debug(rse.getMessage(), e);
            throw rse;
        } catch (Exception e) {
            LOGGER.debug(e.getMessage(), e);
            throw new RemoteServiceException(e);
        }
    }

}
