
package org.mule.module.process;

import java.util.List;
import javax.annotation.Generated;
import org.mule.api.ConnectionManager;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.UnableToAcquireConnectionException;
import org.mule.api.UnableToReleaseConnectionException;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessInterceptor;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.module.adapters.JenkinsConnectorConnectionIdentifierAdapter;
import org.mule.module.connectivity.JenkinsConnectorConnectionKey;
import org.mule.module.processors.AbstractConnectedProcessor;
import org.mule.module.processors.AbstractExpressionEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public class ManagedConnectionProcessInterceptor<T >
    extends AbstractExpressionEvaluator
    implements ProcessInterceptor<T, JenkinsConnectorConnectionIdentifierAdapter>
{

    private static Logger logger = LoggerFactory.getLogger(ManagedConnectionProcessInterceptor.class);
    private final ConnectionManager<JenkinsConnectorConnectionKey, JenkinsConnectorConnectionIdentifierAdapter> connectionManager;
    private final MuleContext muleContext;
    private final ProcessInterceptor<T, JenkinsConnectorConnectionIdentifierAdapter> next;

    public ManagedConnectionProcessInterceptor(ProcessInterceptor<T, JenkinsConnectorConnectionIdentifierAdapter> next, ConnectionManager<JenkinsConnectorConnectionKey, JenkinsConnectorConnectionIdentifierAdapter> connectionManager, MuleContext muleContext) {
        this.next = next;
        this.connectionManager = connectionManager;
        this.muleContext = muleContext;
    }

    public T execute(ProcessCallback<T, JenkinsConnectorConnectionIdentifierAdapter> processCallback, JenkinsConnectorConnectionIdentifierAdapter object, MessageProcessor messageProcessor, MuleEvent event)
        throws Exception
    {
        JenkinsConnectorConnectionIdentifierAdapter connection = null;
        JenkinsConnectorConnectionKey key = null;
        if ((messageProcessor!= null)&&((messageProcessor instanceof AbstractConnectedProcessor)&&(((AbstractConnectedProcessor) messageProcessor).getConnectionName()!= null))) {
            final String _transformedConnectionName = ((String) evaluateAndTransform(muleContext, event, AbstractConnectedProcessor.class.getDeclaredField("_connectionNameType").getGenericType(), null, ((AbstractConnectedProcessor) messageProcessor).getConnectionName()));
            final String _transformedJenkinsUrl = ((String) evaluateAndTransform(muleContext, event, AbstractConnectedProcessor.class.getDeclaredField("_jenkinsUrlType").getGenericType(), null, ((AbstractConnectedProcessor) messageProcessor).getJenkinsUrl()));
            final String _transformedUsername = ((String) evaluateAndTransform(muleContext, event, AbstractConnectedProcessor.class.getDeclaredField("_usernameType").getGenericType(), null, ((AbstractConnectedProcessor) messageProcessor).getUsername()));
            final String _transformedPassword = ((String) evaluateAndTransform(muleContext, event, AbstractConnectedProcessor.class.getDeclaredField("_passwordType").getGenericType(), null, ((AbstractConnectedProcessor) messageProcessor).getPassword()));
            key = new JenkinsConnectorConnectionKey(_transformedConnectionName, _transformedJenkinsUrl, _transformedUsername, _transformedPassword);
        } else {
            key = connectionManager.getDefaultConnectionKey();
        }
        try {
            if (logger.isDebugEnabled()) {
                logger.debug(("Attempting to acquire connection using "+ key.toString()));
            }
            connection = connectionManager.acquireConnection(key);
            if (connection == null) {
                throw new UnableToAcquireConnectionException();
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug((("Connection has been acquired with [id="+ connection.getConnectionIdentifier())+"]"));
                }
            }
            return next.execute(processCallback, connection, messageProcessor, event);
        } catch (Exception e) {
            if (processCallback.getManagedExceptions()!= null) {
                for (Class exceptionClass: ((List<Class> ) processCallback.getManagedExceptions())) {
                    if (exceptionClass.isInstance(e)) {
                        if (logger.isDebugEnabled()) {
                            logger.debug((((("An exception ( "+ exceptionClass.getName())+") has been thrown. Destroying the connection with [id=")+ connection.getConnectionIdentifier())+"]"));
                        }
                        try {
                            connectionManager.destroyConnection(key, connection);
                            connection = null;
                        } catch (Exception innerException) {
                            logger.error(innerException.getMessage(), innerException);
                        }
                    }
                }
            }
            throw e;
        } finally {
            try {
                if (connection!= null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug((("Releasing the connection back into the pool [id="+ connection.getConnectionIdentifier())+"]"));
                    }
                    connectionManager.releaseConnection(key, connection);
                }
            } catch (Exception e) {
                throw new UnableToReleaseConnectionException(e);
            }
        }
    }

    public T execute(ProcessCallback<T, JenkinsConnectorConnectionIdentifierAdapter> processCallback, JenkinsConnectorConnectionIdentifierAdapter object, Filter filter, MuleMessage message)
        throws Exception
    {
        throw new UnsupportedOperationException();
    }

}
