/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.automation.testcases;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MuleEvent;
import org.mule.jenkins.model.BuildInfo;
import org.mule.jenkins.model.JobInfo;


public class GetJobInfoTestCases extends JenkinsTestParent {
	
    @Before
	public void setUp() {
    	
		testObjects = (HashMap<String,Object>) context.getBean("getJobInfoTestData");
    	
		try {
			
			flow = lookupFlowConstruct("create-job");
	        response = flow.process(getTestEvent(testObjects));
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
     
	}
    
    @After
	public void tearDown() {
		
		try {
			
	    flow = lookupFlowConstruct("delete-job");
		flow.process(getTestEvent(testObjects));

		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				fail();
		}
		
	}
    
    @Category({SanityTests.class})
	@Test
	public void testGetJobInfo() {
		
		try {
			
	    flow = lookupFlowConstruct("get-job-info");
		response = flow.process(getTestEvent(testObjects));
			
		JobInfo jobInfo =  (JobInfo) response.getMessage().getPayload();
		
        assertEquals(jobInfo.getDisplayName(), testObjects.get("jobName").toString());
	 	
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				fail();
		}
		
	}

}