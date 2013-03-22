
package org.mule.module.config;

import javax.annotation.Generated;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/jenkins</code>.
 * 
 */
@Generated(value = "Mule DevKit Version 3.3.2", date = "2013-03-22T10:39:58-03:00", comments = "Build UNNAMED.1372.db235f3")
public class JenkinsNamespaceHandler
    extends NamespaceHandlerSupport
{


    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        registerBeanDefinitionParser("config", new JenkinsConnectorConfigDefinitionParser());
        registerBeanDefinitionParser("get-jenkins-node-info", new GetJenkinsNodeInfoDefinitionParser());
        registerBeanDefinitionParser("get-job-info", new GetJobInfoDefinitionParser());
        registerBeanDefinitionParser("build-with-parameters", new BuildWithParametersDefinitionParser());
        registerBeanDefinitionParser("build", new BuildDefinitionParser());
        registerBeanDefinitionParser("get-queue-info", new GetQueueInfoDefinitionParser());
        registerBeanDefinitionParser("create-job", new CreateJobDefinitionParser());
        registerBeanDefinitionParser("copy-job", new CopyJobDefinitionParser());
        registerBeanDefinitionParser("delete-job", new DeleteJobDefinitionParser());
        registerBeanDefinitionParser("enable-job", new EnableJobDefinitionParser());
        registerBeanDefinitionParser("disable-job", new DisableJobDefinitionParser());
        registerBeanDefinitionParser("get-job-build-info", new GetJobBuildInfoDefinitionParser());
        registerBeanDefinitionParser("get-job-build-log", new GetJobBuildLogDefinitionParser());
    }

}
