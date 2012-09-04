/**
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 **/
        
/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.module;

import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Processor;
import org.mule.api.ConnectionException;

import org.mule.api.annotations.param.Optional;
import org.mule.jenkins.Helper;
import org.mule.jenkins.JenkinsConnectorException;
import org.mule.jenkins.JenkinsDeploymentException;
import org.mule.jenkins.model.BuildInfo;
import org.mule.jenkins.model.JenkinsInfo;
import org.mule.jenkins.model.JenkinsQueueInfo;
import org.mule.jenkins.model.JobInfo;



import java.util.Map;

/**
 * Provides the ability to interact with Jenkins API REST.
 * <p>
 *
 * @author MuleSoft, Inc.
 */
@Connector( name="jenkins", schemaVersion="1.0")
public class JenkinsConnector
{
    /**
     * This is the Jenkins server URL
     */
    @Configurable
    private String jenkinsURL;

    /**
     * username
     */
    @Configurable
    @Optional
    private String username;

    /**
     * password
     */
    @Configurable
    @Optional
    private String password;

    /**
     * Set jenkins url
     *
     * @param jenkinsURL Jenkins host URL
     */
    public void setJenkinsURL(String jenkinsURL)
    {
        this.jenkinsURL = jenkinsURL;

    }

    /**
     * Set username
     *
     * @param username jenkins server user
     */
    public void setUsername(String username)
    {
        this.username = username;

    }
    /**
     * Set password
     *
     * @param password Jenkins server password
     */
    public void setPassword(String password)
    {
        this.password = password;

    }
    /**
     * Connect
     *
     * @param connectionName an String identification for the connection
     *
     * @throws ConnectionException
     */
    @Connect
    public void connect(@ConnectionKey String connectionName)  throws ConnectionException {

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
     * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:get-jenkins-node-info}
     *
     * @return Jenkins node info
     */
    @Processor
    public JenkinsInfo getJenkinsNodeInfo() throws JenkinsConnectorException {
        return Helper.getJenkinsInfo();

    }

    /**
     * Retrieves Jenkins job information using job name to find it
     *
     * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:get-job-info}
     *
     * @param jobName Name of the job to retrieve info
     *
     * @return Jenkins job info
     */
    @Processor
    public JobInfo getJobInfo(String jobName) throws JenkinsConnectorException {
        return Helper.getJenkinsJobInfo(jobName);

    }

    /**
     * Perform a parametrized job build using a map
     *
     * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:build-with-parameters}
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
     * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:build}
     *
     * @param jobName Name of the job to retrieve info
     *
     */
    @Processor
    public void build(String jobName) throws JenkinsDeploymentException {
        Helper.build(jobName);
    }

    /**
     * Retrieves Jenkins Queue information, is the current build activity
     *
     * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:get-queue-info}
     *
     *
     * @return Jenkins queue job info
     */
    @Processor
    public JenkinsQueueInfo getQueueInfo() throws JenkinsConnectorException {
        return Helper.getQueueInfo();

    }

    /**
     * Create new job using basic configuration
     *
     * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:create-job}
     *
     * @param jobName Name of the job to create
     *
     * @return The created Jenkins job info if exits, an empty object if creation failed
     */
    @Processor
    public JobInfo createJob(String jobName) throws JenkinsConnectorException {
        return Helper.createJob(jobName);

    }

    /**
     * Create new job using another job as a copy
     *
     * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:copy-job}
     *
     * @param newJobName Name of the job to create
     * @param fromJobName Name of the job to copy
     *
     * @return The created Jenkins job info if exits, an empty object if creation failed
     */
    @Processor
    public JobInfo copyJob(String newJobName, String fromJobName) throws JenkinsConnectorException {
        return Helper.copyFromJob(newJobName, fromJobName);

    }


    /**
     * Delete job
     *
     * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:delete-job}
     *
     * @param jobName Name of the job to delete
     *
     */
    @Processor
    public void deleteJob(String jobName) throws JenkinsConnectorException {
        Helper.delete(jobName);
    }

    /**
    * Enable job
    *
    * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:enable-job}
    *
    * @param jobName Name of the job to enable
    *
    */
    @Processor
    public void enableJob(String jobName) throws JenkinsConnectorException {
        Helper.enableJob(jobName);
    }

    /**
     * Disable job
     *
     * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:disable-job}
     *
     * @param jobName Name of the job to disable
     *
     */
    @Processor
    public void disableJob(String jobName) throws JenkinsConnectorException {
        Helper.disableJob(jobName);
    }

    /**
     * Get job build info
     *
     * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:get-job-build-info}
     *
     * @param jobName Job name
     * @param buildNumber Build number
     *
     * @return Build info representation object
     */
    @Processor
    public BuildInfo getJobBuildInfo(String jobName, int buildNumber) throws JenkinsConnectorException {
        return Helper.getJobBuildInfo(jobName, buildNumber);
    }

    /**
     * Get job build console log text
     *
     * {@sample.xml ../../../doc/Jenkins-connector.xml.sample jenkins:get-job-build-log}
     *
     * @param jobName Job name
     * @param buildNumber Build number
     *
     * @return String with the log output for the requested Job
     */
    @Processor
    public String getJobBuildLog(String jobName, String buildNumber) throws JenkinsConnectorException {
        return Helper.getJobBuildLog(jobName, buildNumber);
    }
}
