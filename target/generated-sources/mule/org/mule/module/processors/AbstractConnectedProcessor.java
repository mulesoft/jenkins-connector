
package org.mule.module.processors;

import javax.annotation.Generated;

@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public abstract class AbstractConnectedProcessor
    extends AbstractExpressionEvaluator
{

    protected Object connectionName;
    protected String _connectionNameType;
    protected Object jenkinsUrl;
    protected String _jenkinsUrlType;
    protected Object username;
    protected String _usernameType;
    protected Object password;
    protected String _passwordType;

    /**
     * Sets username
     * 
     * @param value Value to set
     */
    public void setUsername(Object value) {
        this.username = value;
    }

    /**
     * Retrieves username
     * 
     */
    public Object getUsername() {
        return this.username;
    }

    /**
     * Sets jenkinsUrl
     * 
     * @param value Value to set
     */
    public void setJenkinsUrl(Object value) {
        this.jenkinsUrl = value;
    }

    /**
     * Retrieves jenkinsUrl
     * 
     */
    public Object getJenkinsUrl() {
        return this.jenkinsUrl;
    }

    /**
     * Sets password
     * 
     * @param value Value to set
     */
    public void setPassword(Object value) {
        this.password = value;
    }

    /**
     * Retrieves password
     * 
     */
    public Object getPassword() {
        return this.password;
    }

    /**
     * Sets connectionName
     * 
     * @param value Value to set
     */
    public void setConnectionName(Object value) {
        this.connectionName = value;
    }

    /**
     * Retrieves connectionName
     * 
     */
    public Object getConnectionName() {
        return this.connectionName;
    }

}
