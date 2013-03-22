
package org.mule.module.connectivity;

import javax.annotation.Generated;


/**
 * A tuple of connection parameters
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public class JenkinsConnectorConnectionKey {

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

    public JenkinsConnectorConnectionKey(String connectionName, String jenkinsUrl, String username, String password) {
        this.connectionName = connectionName;
        this.jenkinsUrl = jenkinsUrl;
        this.username = username;
        this.password = password;
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

    public int hashCode() {
        int hash = 1;
        hash = ((hash* 31)+ this.connectionName.hashCode());
        return hash;
    }

    public boolean equals(Object obj) {
        return (((obj instanceof JenkinsConnectorConnectionKey)&&(this.connectionName!= null))&&this.connectionName.equals(((JenkinsConnectorConnectionKey) obj).connectionName));
    }

}
