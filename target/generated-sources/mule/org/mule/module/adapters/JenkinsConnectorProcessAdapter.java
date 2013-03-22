
package org.mule.module.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.process.ProcessAdapter;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.module.JenkinsConnector;


/**
 * A <code>JenkinsConnectorProcessAdapter</code> is a wrapper around {@link JenkinsConnector } that enables custom processing strategies.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public class JenkinsConnectorProcessAdapter
    extends JenkinsConnectorLifecycleAdapter
    implements ProcessAdapter<JenkinsConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, JenkinsConnectorCapabilitiesAdapter> getProcessTemplate() {
        final JenkinsConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,JenkinsConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, JenkinsConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, JenkinsConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
