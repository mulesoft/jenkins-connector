
package org.mule.module.connectivity;

import javax.annotation.Generated;
import org.mule.api.ConnectionManager;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessInterceptor;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.module.adapters.JenkinsConnectorConnectionIdentifierAdapter;
import org.mule.module.process.ManagedConnectionProcessInterceptor;
import org.mule.module.process.ProcessCallbackProcessInterceptor;
import org.mule.module.process.RetryProcessInterceptor;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public class ManagedConnectionProcessTemplate<P >implements ProcessTemplate<P, JenkinsConnectorConnectionIdentifierAdapter>
{

    private final ProcessInterceptor<P, JenkinsConnectorConnectionIdentifierAdapter> processInterceptor;

    public ManagedConnectionProcessTemplate(ConnectionManager<JenkinsConnectorConnectionKey, JenkinsConnectorConnectionIdentifierAdapter> connectionManager, MuleContext muleContext) {
        ProcessInterceptor<P, JenkinsConnectorConnectionIdentifierAdapter> processCallbackProcessInterceptor = new ProcessCallbackProcessInterceptor<P, JenkinsConnectorConnectionIdentifierAdapter>();
        ProcessInterceptor<P, JenkinsConnectorConnectionIdentifierAdapter> managedConnectionProcessInterceptor = new ManagedConnectionProcessInterceptor<P>(processCallbackProcessInterceptor, connectionManager, muleContext);
        ProcessInterceptor<P, JenkinsConnectorConnectionIdentifierAdapter> retryProcessInterceptor = new RetryProcessInterceptor<P, JenkinsConnectorConnectionIdentifierAdapter>(managedConnectionProcessInterceptor, muleContext, connectionManager.getRetryPolicyTemplate());
        processInterceptor = retryProcessInterceptor;
    }

    public P execute(ProcessCallback<P, JenkinsConnectorConnectionIdentifierAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, messageProcessor, event);
    }

    public P execute(ProcessCallback<P, JenkinsConnectorConnectionIdentifierAdapter> processCallback, Filter filter, MuleMessage message)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, filter, message);
    }

}
