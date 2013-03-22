
package org.mule.module.adapters;

import javax.annotation.Generated;
import org.mule.api.MetadataAware;
import org.mule.module.JenkinsConnector;


/**
 * A <code>JenkinsConnectorMetadataAdapater</code> is a wrapper around {@link JenkinsConnector } that adds support for querying metadata about the extension.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public class JenkinsConnectorMetadataAdapater
    extends JenkinsConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "Jenkins";
    private final static String MODULE_VERSION = "1.1-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.3.2";
    private final static String DEVKIT_BUILD = "UNNAMED.1372.db235f3";

    public String getModuleName() {
        return MODULE_NAME;
    }

    public String getModuleVersion() {
        return MODULE_VERSION;
    }

    public String getDevkitVersion() {
        return DEVKIT_VERSION;
    }

    public String getDevkitBuild() {
        return DEVKIT_BUILD;
    }

}
