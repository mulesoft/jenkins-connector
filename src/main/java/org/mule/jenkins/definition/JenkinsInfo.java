package org.mule.jenkins.definition;

import java.io.Serializable;
import java.util.List;

public class JenkinsInfo implements Serializable {
    private Object assignedLabels[];
    private String mode;
    private String nodeDescription;
    private String nodeName;
    private String numExecutors;
    private String description;
    private Job jobs[];
    private Object overallLoad;

    public Object[] getAssignedLabels() {
        return assignedLabels;
    }

    public void setAssignedLabels(Object[] assignedLabels) {
        this.assignedLabels = assignedLabels;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNodeDescription() {
        return nodeDescription;
    }

    public void setNodeDescription(String nodeDescription) {
        this.nodeDescription = nodeDescription;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNumExecutors() {
        return numExecutors;
    }

    public void setNumExecutors(String numExecutors) {
        this.numExecutors = numExecutors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Job[] getJobs() {
        return jobs;
    }

    public void setJobs(Job[] jobs) {
        this.jobs = jobs;
    }

    public Object getOverallLoad() {
        return overallLoad;
    }

    public void setOverallLoad(Object overallLoad) {
        this.overallLoad = overallLoad;
    }
}
