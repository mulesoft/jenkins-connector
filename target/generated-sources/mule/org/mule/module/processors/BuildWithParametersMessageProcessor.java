
package org.mule.module.processors;

import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.mule.api.MessagingException;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.api.process.ProcessAdapter;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.config.i18n.CoreMessages;
import org.mule.module.JenkinsConnector;
import org.mule.module.connectivity.JenkinsConnectorConnectionManager;


/**
 * BuildWithParametersMessageProcessor invokes the {@link org.mule.module.JenkinsConnector#buildWithParameters(java.lang.String, java.util.Map)} method in {@link JenkinsConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public class BuildWithParametersMessageProcessor
    extends AbstractMessageProcessor<Object>
    implements Disposable, Initialisable, Startable, Stoppable, MessageProcessor
{

    protected Object jobName;
    protected String _jobNameType;
    protected Object params;
    protected Map<String, String> _paramsType;

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    public void start()
        throws MuleException
    {
    }

    public void stop()
        throws MuleException
    {
    }

    public void dispose() {
    }

    /**
     * Set the Mule context
     * 
     * @param context Mule context to set
     */
    public void setMuleContext(MuleContext context) {
        super.setMuleContext(context);
    }

    /**
     * Sets flow construct
     * 
     * @param flowConstruct Flow construct to set
     */
    public void setFlowConstruct(FlowConstruct flowConstruct) {
        super.setFlowConstruct(flowConstruct);
    }

    /**
     * Sets params
     * 
     * @param value Value to set
     */
    public void setParams(Object value) {
        this.params = value;
    }

    /**
     * Sets jobName
     * 
     * @param value Value to set
     */
    public void setJobName(Object value) {
        this.jobName = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws MuleException
     */
    public MuleEvent process(final MuleEvent event)
        throws MuleException
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(JenkinsConnectorConnectionManager.class, true, event);
            final String _transformedJobName = ((String) evaluateAndTransform(getMuleContext(), event, BuildWithParametersMessageProcessor.class.getDeclaredField("_jobNameType").getGenericType(), null, jobName));
            final Map<String, String> _transformedParams = ((Map<String, String> ) evaluateAndTransform(getMuleContext(), event, BuildWithParametersMessageProcessor.class.getDeclaredField("_paramsType").getGenericType(), null, params));
            ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class> getManagedExceptions() {
                    return null;
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    ((JenkinsConnector) object).buildWithParameters(_transformedJobName, _transformedParams);
                    return null;
                }

            }
            , this, event);
            return event;
        } catch (MessagingException messagingException) {
            messagingException.setProcessedEvent(event);
            throw messagingException;
        } catch (Exception e) {
            throw new MessagingException(CoreMessages.failedToInvoke("buildWithParameters"), event, e);
        }
    }

}
