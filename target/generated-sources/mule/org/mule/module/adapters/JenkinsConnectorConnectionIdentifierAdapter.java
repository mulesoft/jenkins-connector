
package org.mule.module.adapters;

import javax.annotation.Generated;
import org.mule.api.Connection;
import org.mule.module.JenkinsConnector;


/**
 * A <code>JenkinsConnectorConnectionIdentifierAdapter</code> is a wrapper around {@link JenkinsConnector } that implements {@link org.mule.api.Connection} interface.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public class JenkinsConnectorConnectionIdentifierAdapter
    extends JenkinsConnectorProcessAdapter
    implements Connection
{


    public String getConnectionIdentifier() {
        return super.connectionId();
    }

}
