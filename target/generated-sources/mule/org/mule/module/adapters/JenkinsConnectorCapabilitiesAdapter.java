
package org.mule.module.adapters;

import javax.annotation.Generated;
import org.mule.api.Capabilities;
import org.mule.api.Capability;
import org.mule.module.JenkinsConnector;


/**
 * A <code>JenkinsConnectorCapabilitiesAdapter</code> is a wrapper around {@link JenkinsConnector } that implements {@link org.mule.api.Capabilities} interface.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public class JenkinsConnectorCapabilitiesAdapter
    extends JenkinsConnector
    implements Capabilities
{


    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(Capability capability) {
        if (capability == Capability.LIFECYCLE_CAPABLE) {
            return true;
        }
        if (capability == Capability.CONNECTION_MANAGEMENT_CAPABLE) {
            return true;
        }
        return false;
    }

}
