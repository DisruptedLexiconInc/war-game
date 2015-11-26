package com.hive.jfx.wargame.restclient.util;

import java.util.Collection;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hive.jfx.wargame.exception.RemoteServiceException;

public class JsonGetListTask<T> extends Task<Collection<T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonGetListTask.class.getName());

    private final GenericType<List<T>> genericType;
    private final WebTarget webTarget;

    protected JsonGetListTask(final GenericType<List<T>> genericType, WebTarget webTarget) {
        this.genericType = genericType;
        this.webTarget = webTarget;
    }

    public static <T> ObservableList<T> get(final GenericType<List<T>> genericType, WebTarget webTarget, boolean synchronous) throws RemoteServiceException {
        final ObservableList<T> rv = FXCollections.observableArrayList();

        final JsonGetListTask<T> retrievalTask = new JsonGetListTask<>(genericType, webTarget);

        if (!synchronous) {
            retrievalTask.setOnSucceeded(state -> rv.addAll((Collection<T>) state.getSource().getValue()));
            new Thread(retrievalTask).start();
        } else {
            rv.addAll(retrievalTask.call());
        }
        return rv;
    }

    @Override
    protected Collection<T> call() throws RemoteServiceException {
        try {
            List<T> list = webTarget.request().get(genericType);
            return list;
        } catch (ResponseProcessingException e) {
            RemoteServiceException rse = new RemoteServiceException(e.getMessage(), e.getCause());
            LOGGER.warn(rse.getMessage(), e);
            throw rse;
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw new RemoteServiceException(e);
        }
    }

}
