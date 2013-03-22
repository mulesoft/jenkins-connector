
package org.mule.module.connectivity;

import javax.annotation.Generated;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.mule.api.Capabilities;
import org.mule.api.Capability;
import org.mule.api.ConnectionManager;
import org.mule.api.MetadataAware;
import org.mule.api.MuleContext;
import org.mule.api.config.MuleProperties;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.process.ProcessAdapter;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.retry.RetryPolicyTemplate;
import org.mule.config.PoolingProfile;
import org.mule.module.JenkinsConnector;
import org.mule.module.adapters.JenkinsConnectorConnectionIdentifierAdapter;


/**
 * A {@code JenkinsConnectorConnectionManager} is a wrapper around {@link JenkinsConnector } that adds connection management capabilities to the pojo.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public class JenkinsConnectorConnectionManager implements Capabilities, ConnectionManager<JenkinsConnectorConnectionKey, JenkinsConnectorConnectionIdentifierAdapter> , MetadataAware, MuleContextAware, Initialisable, ProcessAdapter<JenkinsConnectorConnectionIdentifierAdapter>
{

    /**
     * 
     */
    private String connectionName;
    /**
     * 
     */
    private String jenkinsUrl;
    /**
     * 
     */
    private String username;
    /**
     * 
     */
    private String password;
    /**
     * Mule Context
     * 
     */
    protected MuleContext muleContext;
    /**
     * Flow Construct
     * 
     */
    protected FlowConstruct flowConstruct;
    /**
     * Connector Pool
     * 
     */
    private GenericKeyedObjectPool connectionPool;
    protected PoolingProfile connectionPoolingProfile;
    protected RetryPolicyTemplate retryPolicyTemplate;
    private final static String MODULE_NAME = "Jenkins";
    private final static String MODULE_VERSION = "1.1-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.3.2";
    private final static String DEVKIT_BUILD = "UNNAMED.1372.db235f3";

    /**
     * Sets muleContext
     * 
     * @param value Value to set
     */
    public void setMuleContext(MuleContext value) {
        this.muleContext = value;
    }

    /**
     * Retrieves muleContext
     * 
     */
    public MuleContext getMuleContext() {
        return this.muleContext;
    }

    /**
     * Sets flowConstruct
     * 
     * @param value Value to set
     */
    public void setFlowConstruct(FlowConstruct value) {
        this.flowConstruct = value;
    }

    /**
     * Retrieves flowConstruct
     * 
     */
    public FlowConstruct getFlowConstruct() {
        return this.flowConstruct;
    }

    /**
     * Sets connectionPoolingProfile
     * 
     * @param value Value to set
     */
    public void setConnectionPoolingProfile(PoolingProfile value) {
        this.connectionPoolingProfile = value;
    }

    /**
     * Retrieves connectionPoolingProfile
     * 
     */
    public PoolingProfile getConnectionPoolingProfile() {
        return this.connectionPoolingProfile;
    }

    /**
     * Sets retryPolicyTemplate
     * 
     * @param value Value to set
     */
    public void setRetryPolicyTemplate(RetryPolicyTemplate value) {
        this.retryPolicyTemplate = value;
    }

    /**
     * Retrieves retryPolicyTemplate
     * 
     */
    public RetryPolicyTemplate getRetryPolicyTemplate() {
        return this.retryPolicyTemplate;
    }

    /**
     * Sets username
     * 
     * @param value Value to set
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Retrieves username
     * 
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets jenkinsUrl
     * 
     * @param value Value to set
     */
    public void setJenkinsUrl(String value) {
        this.jenkinsUrl = value;
    }

    /**
     * Retrieves jenkinsUrl
     * 
     */
    public String getJenkinsUrl() {
        return this.jenkinsUrl;
    }

    /**
     * Sets password
     * 
     * @param value Value to set
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Retrieves password
     * 
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets connectionName
     * 
     * @param value Value to set
     */
    public void setConnectionName(String value) {
        this.connectionName = value;
    }

    /**
     * Retrieves connectionName
     * 
     */
    public String getConnectionName() {
        return this.connectionName;
    }

    public void initialise() {
        GenericKeyedObjectPool.Config config = new GenericKeyedObjectPool.Config();
        if (connectionPoolingProfile!= null) {
            config.maxIdle = connectionPoolingProfile.getMaxIdle();
            config.maxActive = connectionPoolingProfile.getMaxActive();
            config.maxWait = connectionPoolingProfile.getMaxWait();
            config.whenExhaustedAction = ((byte) connectionPoolingProfile.getExhaustedAction());
            config.timeBetweenEvictionRunsMillis = 1800000;
            config.minEvictableIdleTimeMillis = 1800000;
        }
        connectionPool = new GenericKeyedObjectPool(new JenkinsConnectorConnectionFactory(this), config);
        if (retryPolicyTemplate == null) {
            retryPolicyTemplate = muleContext.getRegistry().lookupObject(MuleProperties.OBJECT_DEFAULT_RETRY_POLICY_TEMPLATE);
        }
    }

    public JenkinsConnectorConnectionIdentifierAdapter acquireConnection(JenkinsConnectorConnectionKey key)
        throws Exception
    {
        return ((JenkinsConnectorConnectionIdentifierAdapter) connectionPool.borrowObject(key));
    }

    public void releaseConnection(JenkinsConnectorConnectionKey key, JenkinsConnectorConnectionIdentifierAdapter connection)
        throws Exception
    {
        connectionPool.returnObject(key, connection);
    }

    public void destroyConnection(JenkinsConnectorConnectionKey key, JenkinsConnectorConnectionIdentifierAdapter connection)
        throws Exception
    {
        connectionPool.invalidateObject(key, connection);
    }

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

    @Override
    public<P >ProcessTemplate<P, JenkinsConnectorConnectionIdentifierAdapter> getProcessTemplate() {
        return new ManagedConnectionProcessTemplate(this, muleContext);
    }

    public JenkinsConnectorConnectionKey getDefaultConnectionKey() {
        return new JenkinsConnectorConnectionKey(getConnectionName(), getJenkinsUrl(), getUsername(), getPassword());
    }

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
