package com.hive.jfx.wargame.restclient.util;

import javafx.concurrent.Task;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hive.jfx.wargame.exception.DataIntegrityException;
import com.hive.jfx.wargame.exception.RemoteServiceException;

public class JsonDeleteTask extends Task<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDeleteTask.class.getName());

    private WebTarget webTarget;

    protected JsonDeleteTask(WebTarget webTarget) {
        this.webTarget = webTarget;
    }

    public static void delete(WebTarget webTarget, boolean synchronous) throws DataIntegrityException {

        final JsonDeleteTask retrievalTask = new JsonDeleteTask(webTarget);

        if (!synchronous) {
            new Thread(retrievalTask).start();
        } else {
            try {
                retrievalTask.call();
            } catch (DataIntegrityException e) {
                LOGGER.error(e.getMessage(), e);
                throw e;
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    @Override
    protected Long call() throws RemoteServiceException {
        Response response = null;
        try {
            response = webTarget.request().delete();
            // Close the connection since we are done with it other than getting the response code.
            response.close();
            return new Long(response.getStatusInfo().getStatusCode());
        } catch (ResponseProcessingException e) {
            LOGGER.debug(e.getMessage(), e);
            if (response != null) {
                LOGGER.debug("Server returned status code of " + response.getStatus());
                response.close();
            }
            if (e.getCause() instanceof RemoteServiceException) {
                throw (RemoteServiceException) e.getCause();
            } else {
                throw new RemoteServiceException(e.getCause());
            }
        } catch (Exception e) {
            LOGGER.debug(e.getMessage(), e);
            if (response != null) {
                LOGGER.debug("Server returned status code of " + response.getStatus());
                response.close();
            }
            throw new RemoteServiceException(e);
        }
    }

}
