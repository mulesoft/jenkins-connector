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
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.jenkins.definition.JenkinsInfo;
import org.mule.jenkins.definition.JobInfo;

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


    public static JenkinsInfo getJenkinsInfo() throws JenkinsDeploymentException {
        setClientInfo();
        HttpGet method = new HttpGet(getUrl() + "/api/json");
        Gson gson = new Gson();
        JenkinsInfo info = null;

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new JenkinsDeploymentException("Build call failed: " + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            String jsonString = EntityUtils.toString(response.getEntity());

            info = gson.fromJson(jsonString, JenkinsInfo.class);




        } catch (Exception e) {
            throw new JenkinsDeploymentException(e.getMessage());
        }

       return info;
    }

    public static void setClientInfo(){
        client.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials(user, password));
        BasicScheme basicAuth = new BasicScheme();

        context.setAttribute("preemptive-auth", basicAuth);

        client.addRequestInterceptor(new PreemptiveAuth(), 0);


    }

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

        if(username.isEmpty()){throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, "",  "Username property cannot be empty");}
        if(password.isEmpty()){throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, "","Password property cannot be empty");}

        setUser(username);
        setPassword(password);
        setUrl(jenkinsURL);
        connected = true;
    }

    public static JobInfo getJenkinsJobInfo(String jobName) throws JenkinsDeploymentException {
        setClientInfo();
        HttpGet method = new HttpGet(getUrl() + "/job/" + jobName + "/api/json");
        Gson gson = new Gson();
        JobInfo info = null;

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new JenkinsDeploymentException("Build call failed: " + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }

            String jsonString = EntityUtils.toString(response.getEntity());

            info = gson.fromJson(jsonString, JobInfo.class);

        } catch (Exception e) {
            throw new JenkinsDeploymentException(e.getMessage());
        }
        return info;
    }

    public static void buildWithParameters(String jobName, Map<String,String> params) throws JenkinsDeploymentException {
        setClientInfo();
        HttpGet method = new HttpGet(getUrl() + "/job/" + jobName + "/buildWithParameters" + extractParams(params));

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new JenkinsDeploymentException("Build call failed: " + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }


        } catch (Exception e) {
            throw new JenkinsDeploymentException(e.getMessage());

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

    public static void build(String jobName) throws JenkinsDeploymentException {

        setClientInfo();
        HttpGet method = new HttpGet(getUrl() + "/job/" + jobName + "/build");

        try {
            // Execute the method.
            HttpResponse response = client.execute(method, context);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new JenkinsDeploymentException("Build call failed: " + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
            }


        } catch (Exception e) {
            throw new JenkinsDeploymentException(e.getMessage());

        }
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
}
