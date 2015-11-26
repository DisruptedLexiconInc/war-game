package com.hive.jfx.wargame.restclient.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hive.jfx.wargame.exception.RemoteServiceException;

public class JsonPutTask<T> extends Task<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPutTask.class.getName());

    private final GenericType<T> genericType;
    private final Object object;
    private final WebTarget webTarget;

    protected JsonPutTask(final Object object, final GenericType<T> genericType, WebTarget webTarget) {
        this.object = object;
        this.genericType = genericType;
        this.webTarget = webTarget;
    }

    public static <T> ObservableList<T> put(final Object object, final GenericType<T> genericType, WebTarget webTarget, boolean synchronous)
                    throws RemoteServiceException {
        final ObservableList<T> rv = FXCollections.observableArrayList();

        final JsonPutTask<T> retrievalTask = new JsonPutTask<>(object, genericType, webTarget);

        if (!synchronous) {
            retrievalTask.setOnSucceeded(state -> rv.add((T) state.getSource().getValue()));
            new Thread(retrievalTask).start();
        } else {
            retrievalTask.call();
        }
        return rv;
    }

    @Override
    protected T call() throws RemoteServiceException {
        try {
            T thing = webTarget.request().put(Entity.json(object), genericType);

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
