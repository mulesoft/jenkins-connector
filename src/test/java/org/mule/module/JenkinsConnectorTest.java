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

//import org.mule.api.MuleEvent;
//import org.mule.construct.Flow;
//import org.mule.jenkins.model.BuildInfo;
//import org.mule.jenkins.model.JenkinsInfo;
//import org.mule.jenkins.model.JenkinsQueueInfo;
//import org.mule.jenkins.model.JobInfo;
//import org.mule.tck.FunctionalTestCase;
//import org.mule.tck.AbstractMuleTestCase;

//import org.junit.Test;

//public class JenkinsConnectorTest extends FunctionalTestCase
//{
//    @Override
//    protected String getConfigResources()
 //   {
//        return "mule-config.xml";
//    }

//    @Test
//    public void testJenkinsInfo() throws Exception
//    {
//        runFlowAndExpect("testFlow", JenkinsInfo.class);
//    }

//    @Test
//    public void testCreateJob() throws Exception
//    {
//        runFlowAndExpect("testCreateNewJob", JobInfo.class);
//    }

//    @Test
//    public void testCopyJob() throws Exception
//    {
//        runFlowAndExpect("testCopy", JobInfo.class);
//    }

//    @Test
//    public void testJobInfo() throws Exception
//    {
//        runFlowAndExpect("testFlowJob", JobInfo.class);
//    }

    //@Test
//    public void testJobBuildWithParams() throws Exception
 //   {
//        runFlowAndExpect("testFlowBuildWitParams", org.mule.transport.NullPayload.class);
//    }
//
//    @Test
//    public void testQueueInfo() throws Exception
 //   {
 //   runFlowAndExpect("testFlowQueue", JenkinsQueueInfo.class);
 //   }

//    @Test
//    public void testEnableDisableJob() throws Exception
//    {
//        runFlowAndExpect("testEnableDisableJob", org.mule.transport.NullPayload.class);
//    }

//    @Test
 //   public void testDeleteJob() throws Exception
 //   {
 //       runFlowAndExpect("testDeleteJob", org.mule.transport.NullPayload.class);
 //   }

 //   @Test
 //   public void testGetBuildInfo() throws Exception
 //   {
 //       runFlowAndExpect("testGetBuildInfo", BuildInfo.class);
 //   }

 //   @Test
 //   public void testGetLog() throws Exception
  //  {
  //      runFlowAndExpect("testGetLog", String.class);
 //   }

    /**
    * Run the flow specified by name and assert equality on the expected output
    *
    * @param flowName The name of the flow to run
    * @param expect The expected output
    */
//    protected <T> void runFlowAndExpect(String flowName, T expect) throws Exception
//    {
//        Flow flow = lookupFlowConstruct(flowName);
//        MuleEvent event = AbstractMuleTestCase.getTestEvent(null);
//        MuleEvent responseEvent = flow.process(event);
//        System.out.println(responseEvent);

//        assertEquals(expect, responseEvent.getMessage().getPayload().getClass());
//    }

    /**
    * Run the flow specified by name using the specified payload and assert
    * equality on the expected output
    *
    * @param flowName The name of the flow to run
    * @param expect The expected output
    * @param payload The payload of the input event
    */
 //   protected <T, U> void runFlowWithPayloadAndExpect(String flowName, T expect, U payload) throws Exception
//    {
 //       Flow flow = lookupFlowConstruct(flowName);
//        MuleEvent event = AbstractMuleTestCase.getTestEvent(payload);
 //       MuleEvent responseEvent = flow.process(event);//
//
 //       assertEquals(expect, responseEvent.getMessage().getPayload());
  //  }

    /**
     * Retrieve a flow by name from the registry
     *
     * @param name Name of the flow to retrieve
     */
//    protected Flow lookupFlowConstruct(String name)
 //   {
  //      return (Flow) AbstractMuleTestCase.muleContext.getRegistry().lookupFlowConstruct(name);
 //   }
//}
