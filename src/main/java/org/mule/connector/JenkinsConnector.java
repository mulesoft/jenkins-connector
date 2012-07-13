/**
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 **/
        
/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.connector;

import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Processor;
import org.mule.jenkins.Helper;
import org.mule.jenkins.JenkinsDeploymentException;
import org.mule.jenkins.definition.JenkinsInfo;
import org.mule.jenkins.definition.JobInfo;


import java.util.Map;

/**
 * Provides the ability to interact with Jenkins API REST.
 * <p>
 * Operations allowed:
 * <ul>
 *     <li>Retrieve Jenkins node information</li>
 *     <li>Retrieve Job information by jobName</li>
 *     <li>Perform build</li>
 *     <li>Perform parametrized build</li>
 * </ul>
 *  <p>
 *
 * @author MuleSoft, Inc.
 */
@Connector( name="Jenkins-Connector", schemaVersion="1.0-SNAPSHOT")
public class JenkinsConnector
{
    /**
     * This is the Jenkins server URL
     */
    @Configurable
    private String jenkinsURL;

    /**
     * Set property
     *
     * @param jenkinsURL Jenkins host URL
     */
    public void setJenkinsURL(String jenkinsURL)
    {
        this.jenkinsURL = jenkinsURL;

    }

    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    public void connect(@ConnectionKey String username, String password)
            throws ConnectionException {


        Helper.setConnectionInfo(username, password, jenkinsURL);

    }

    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
        /*
         * CODE FOR CLOSING A CONNECTION GOES IN HERE
         */
    }

    /**
     * Connected if http client info is set properly
     */
    @ValidateConnection
    public boolean isConnected() {
        return Helper.isConnected();
    }

    /**
     * connection id
     */
    @ConnectionIdentifier
    public String connectionId() {
        return "001";
    }

    /**
     * Retrieve jenkins server node information
     *
     * {@sample.xml ../../../doc/JenkinsConnector-connector.xml.sample jenkinsconnector:jenkins-node-info}
     *
     * @return Jenkins node info
     */
    @Processor
    public JenkinsInfo getJenkinsNodeInfo() throws JenkinsDeploymentException {
        return Helper.getJenkinsInfo();

    }

    /**
     * Retrieves Jenkins job information using job name to find it
     *
     * {@sample.xml ../../../doc/JenkinsConnector-connector.xml.sample jenkinsconnector:jenkins-job-info}
     *
     * @param jobName Name of the job to retrieve info
     *
     * @return Jenkins job info
     */
    @Processor
    public JobInfo getJobInfo(String jobName) throws JenkinsDeploymentException {
        return Helper.getJenkinsJobInfo(jobName);

    }

    /**
     * Perform a parametrized job build using a map
     *
     * {@sample.xml ../../../doc/JenkinsConnector-connector.xml.sample jenkinsconnector:jenkins-job-build-with-parameters}
     *
     * @param jobName Name of the job to retrieve info
     * @param params Build parameters map
     *
     */
    @Processor
    public void buildWithParameters(String jobName, Map<String,String> params) throws JenkinsDeploymentException {
        Helper.buildWithParameters(jobName, params);
    }

    /**
     * Perform a job build
     *
     * {@sample.xml ../../../doc/JenkinsConnector-connector.xml.sample jenkinsconnector:jenkins-job-build}
     *
     * @param jobName Name of the job to retrieve info
     *
     */
    @Processor
    public void build(String jobName) throws JenkinsDeploymentException {
        Helper.build(jobName);
    }
}