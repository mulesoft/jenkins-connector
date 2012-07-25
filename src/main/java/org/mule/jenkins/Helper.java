/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.jenkins;

import com.google.gson.Gson;

import org.apache.http.*;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.jenkins.model.BuildInfo;
import org.mule.jenkins.model.JenkinsInfo;
import org.mule.jenkins.model.JenkinsQueueInfo;
import org.mule.jenkins.model.JobInfo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class Helper {

    static DefaultHttpClient client = new DefaultHttpClient();
    static BasicHttpContext context = new BasicHttpContext();
    private static String user;
    private static String password;
    private static String url;
    private static boolean connected = false;
    private static boolean bNoCredentials = false;

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Helper.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Helper.password = password;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Helper.url = url;
    }

    public static void setClientInfo(){
        client.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials(user, password));
        BasicScheme basicAuth = new BasicScheme();

        context.setAttribute("preemptive-auth", basicAuth);

        client.addRequestInterceptor(new PreemptiveAuth(), 0);
    }

    public static void setConnectionInfo(String username, String password, String jenkinsURL) throws ConnectionException {

        //Check URL
        try{
            URL url = new URL(jenkinsURL);
            URLConnection conn = url.openConnection();
            conn.connect();
        }catch(MalformedURLException e){
            throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, "", "Jenkins URL is not well formed. Please check your configuration", e);
        }
        catch (IOException e){
            throw new ConnectionException(ConnectionExceptionCode.CANNOT_REACH, "", "Could not connect to Jenkins. Please check your configuration", e);
        }
        /////

        setUrl(jenkinsURL);

        if(username != null && password != null){

            if(username.isEmpty()){throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, "",  "Username property cannot be empty");}
            if(password.isEmpty()){throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, "","Password property cannot be empty");}

            setUser(username);
            setPassword(password);

            setClientInfo();
        } else if(username != null && password == null){
                throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, "","password property cannot be empty if username provided");
        }else if(username == null && password != null){
                throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, "","username property cannot be empty if password provided");
        }

        connected = true;
    }

    public static JenkinsInfo getJenkinsInfo() throws JenkinsConnectorException {
        HttpGet method = new HttpGet(getUrl() + "/api/json");
        Gson gson = new Gson();
        JenkinsInfo info = null;

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 200) {
                EntityUtils.consume(response.getEntity());
                throw new JenkinsConnectorException("getJenkinsInfo response status error ", "", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            String jsonString = EntityUtils.toString(response.getEntity());

            info = gson.fromJson(jsonString, JenkinsInfo.class);

        } catch (Exception e) {
            throw new JenkinsConnectorException("getJenkinsInfo http request failed", "", "", e);
        }

        return info;
    }

    public static JobInfo getJenkinsJobInfo(String jobName) throws JenkinsConnectorException {
        HttpGet method = new HttpGet(getUrl() + "/job/" + jobName + "/api/json");
        Gson gson = new Gson();
        JobInfo info = null;

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 200) {
                EntityUtils.consume(response.getEntity());
                throw new JenkinsConnectorException("getJenkinsJobInfo response status error","", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            String jsonString = EntityUtils.toString(response.getEntity());

            info = gson.fromJson(jsonString, JobInfo.class);

        } catch (Exception e) {
            throw new JenkinsConnectorException("getJenkinsJobInfo http request failed", "", "", e);
        }
        return info;
    }

    public static JenkinsQueueInfo getQueueInfo() throws JenkinsConnectorException {
        HttpGet method = new HttpGet(getUrl() + "/queue/api/json");
        Gson gson = new Gson();
        JenkinsQueueInfo info = null;

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 200) {
                EntityUtils.consume(response.getEntity());
                throw new JenkinsConnectorException("getQueueInfo response status error","", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            String jsonString = EntityUtils.toString(response.getEntity());

            info = gson.fromJson(jsonString, JenkinsQueueInfo.class);

        } catch (Exception e) {
            throw new JenkinsConnectorException("getQueueInfo http request failed","", "", e);
        }

        return info;
    }

    public static void buildWithParameters(String jobName, Map<String,String> params) throws JenkinsDeploymentException {
        HttpGet method = new HttpGet(getUrl() + "/job/" + jobName + "/buildWithParameters" + extractParams(params));

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 200) {
                EntityUtils.consume(response.getEntity());
                if(response.getStatusLine().getStatusCode() == 500){
                    throw new JenkinsDeploymentException("Check Job configuration, you're trying to build with parameters a non parametrized job.", "", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
                }else{
                    throw new JenkinsDeploymentException("buildWithParameters response status error", "", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
                }
            }

            EntityUtils.consume(response.getEntity());

        } catch (Exception e) {
            throw new JenkinsDeploymentException("buildWithParameters http request failed","", "", e);
        }
    }



    public static void build(String jobName) throws JenkinsDeploymentException {
        HttpGet method = new HttpGet(getUrl() + "/job/" + jobName + "/build");

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 200) {
                EntityUtils.consume(response.getEntity());
                throw new JenkinsDeploymentException("build response status error" , "", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            EntityUtils.consume(response.getEntity());

        } catch (Exception e) {
            throw new JenkinsDeploymentException("build http request failed", "", "", e);

        }
    }

    public static JobInfo createJob(String jobName) throws JenkinsConnectorException {
        HttpPost method = new HttpPost(getUrl() + "/createItem?name=" + jobName + "");
        File configFile = new File(Helper.class.getClassLoader().getResource("config.xml").getPath());
        JobInfo rJobInfo = new JobInfo();

        try {
            FileEntity fileentity = new FileEntity(configFile, "application/xml");
            BufferedHttpEntity reqEntity = new BufferedHttpEntity(fileentity);
            method.setEntity(reqEntity);

            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 200) {
                EntityUtils.consume(response.getEntity());
                throw new JenkinsConnectorException("createJob response status error","", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            EntityUtils.consume(response.getEntity());

            rJobInfo = getJenkinsJobInfo(jobName);

        } catch (Exception e) {
            throw new JenkinsConnectorException("createJob http request failed", "", "",  e);
        }

        return rJobInfo;
    }


    public static void delete(String jobName) throws JenkinsConnectorException {
        HttpPost method = new HttpPost(getUrl() + "/job/" + jobName + "/doDelete");

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 302) {
                EntityUtils.consume(response.getEntity());
                throw new JenkinsDeploymentException("delete response status error" , "", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            EntityUtils.consume(response.getEntity());

        } catch (Exception e) {
            throw new JenkinsConnectorException("delete http request failed", "", "", e);

        }
    }

    public static void enableJob(String jobName) throws JenkinsConnectorException {
        HttpPost method = new HttpPost(getUrl() + "/job/" + jobName + "/enable");

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 302) {
                EntityUtils.consume(response.getEntity());
                throw new JenkinsDeploymentException("enableJob response status error" , "", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            EntityUtils.consume(response.getEntity());

        } catch (Exception e) {
            throw new JenkinsConnectorException("enableJob http request failed", "", "", e);

        }
    }

    public static void disableJob(String jobName) throws JenkinsConnectorException {
        HttpPost method = new HttpPost(getUrl() + "/job/" + jobName + "/disable");

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 302) {
                EntityUtils.consume(response.getEntity());
                throw new JenkinsDeploymentException("disableJob response status error" , "", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            EntityUtils.consume(response.getEntity());

        } catch (Exception e) {
            throw new JenkinsConnectorException("disableJob http request failed", "", "", e);

        }
    }

    public static BuildInfo getJobBuildInfo(String jobName, int buildNumber) throws JenkinsConnectorException {
        HttpGet method = new HttpGet(getUrl() + "/job/" + jobName + "/" + String.valueOf(buildNumber) + "/api/json");
        Gson gson = new Gson();
        BuildInfo info = null;

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 200) {
                EntityUtils.consume(response.getEntity());
                throw new JenkinsConnectorException("getJobBuildInfo response status error","", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            String jsonString = EntityUtils.toString(response.getEntity());

            info = gson.fromJson(jsonString, BuildInfo.class);

        } catch (Exception e) {
            throw new JenkinsConnectorException("getJobBuildInfo http request failed", "", "", e);
        }
        return info;
    }

    public static JobInfo copyFromJob(String newJobName, String fromJobName) throws JenkinsConnectorException {
        HttpPost method = new HttpPost(getUrl() + "/createItem?name=" + newJobName + "&mode=copy&from=" + fromJobName);
        File configFile = new File(Helper.class.getClassLoader().getResource("config.xml").getPath());
        JobInfo rJobInfo = new JobInfo();

        try {
            FileEntity fileentity = new FileEntity(configFile, "application/xml");
            BufferedHttpEntity reqEntity = new BufferedHttpEntity(fileentity);
            method.setEntity(reqEntity);

            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 302) {
                EntityUtils.consume(response.getEntity());
                throw new JenkinsConnectorException("copyFromJob response status error","", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            EntityUtils.consume(response.getEntity());

            rJobInfo = getJenkinsJobInfo(newJobName);

        } catch (Exception e) {
            throw new JenkinsConnectorException("copyFromJob http request failed", "", "",  e);
        }

        return rJobInfo;
    }

    /**
     * Preemptive authentication interceptor
     *
     */
    static class PreemptiveAuth implements HttpRequestInterceptor {

        /*
           * (non-Javadoc)
           *
           * @see org.apache.http.HttpRequestInterceptor#process(org.apache.http.HttpRequest,
           * org.apache.http.protocol.HttpContext)
           */
        public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
            // Get the AuthState
            AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);

            // If no auth scheme available yet, try to initialize it preemptively
            if (authState.getAuthScheme() == null) {
                AuthScheme authScheme = (AuthScheme) context.getAttribute("preemptive-auth");
                CredentialsProvider credsProvider = (CredentialsProvider) context
                        .getAttribute(ClientContext.CREDS_PROVIDER);
                HttpHost targetHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
                if (authScheme != null) {
                    Credentials creds = credsProvider.getCredentials(new AuthScope(targetHost.getHostName(), targetHost
                            .getPort()));
                    if (creds == null) {
                        throw new HttpException("No credentials for preemptive authentication");
                    }
                    authState.setAuthScheme(authScheme);
                    authState.setCredentials(creds);
                }
            }

        }

    }

    private static String extractParams(Map<String, String> params){
        boolean bFirst = true;
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String, String> param : params.entrySet()){

            if (bFirst){sb.append("?");bFirst=false;}else{sb.append("&");}

            sb.append(param.getKey());
            sb.append("=");
            sb.append(param.getValue());
        }

        return sb.toString();

    }

    public static boolean isConnected() {
        return connected;
    }
}
