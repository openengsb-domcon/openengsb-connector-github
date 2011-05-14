/**
 * Licensed to the Austrian Association for Software Tool Integration (AASTI)
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. The AASTI licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openengsb.connector.github.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains methods which convert the response of REST to the corresponding git object
 */
public final class GithubHelper {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubService.class);
    
    private GithubHelper() {
        
    }
    
    public static GithubIssue processSingleIssueResponse(String response) {
        LOGGER.info("processing: " + response);
        String tmp = response.substring(response.indexOf("{\"gravatar_id\":\"") + "{\"gravatar_id\":\"".length(),
                response.lastIndexOf("\"}"));

        GithubIssue c = new GithubIssue();
        int index = tmp.indexOf("\",\"");
        c.setGravatarId(tmp.substring(0, index));
        tmp = tmp.substring(index + "\",\"position\":".length());
        index = tmp.indexOf(",\"");
        c.setPosition(Double.valueOf(tmp.substring(0, index)));
        tmp = tmp.substring(index + ",\"number\":".length());
        index = tmp.indexOf(",\"");
        c.setNumber(Integer.valueOf(tmp.substring(0, index)));
        tmp = tmp.substring(index + ",\"votes\":".length());
        index = tmp.indexOf(",\"");
        c.setVotes(Integer.valueOf(tmp.substring(0, index)));
        tmp = tmp.substring(index + ",\"created_at\":\"".length());
        index = tmp.indexOf("\",\"");
        c.setCreatedAt(tmp.substring(0, index));
        tmp = tmp.substring(index + "\",\"comments\":".length());
        index = tmp.indexOf(",\"");
        c.setComments(Integer.valueOf(tmp.substring(0, index)));
        tmp = tmp.substring(index + ",\"body\":\"".length());
        index = tmp.indexOf("\",\"");
        c.setBody(tmp.substring(0, index));
        tmp = tmp.substring(index + "\",\"title\":\"".length());
        index = tmp.indexOf("\",\"");
        c.setTitle(tmp.substring(0, index));
        tmp = tmp.substring(index + "\",\"updated_at\":\"".length());
        index = tmp.indexOf("\",\"");
        c.setUpdatedAt(tmp.substring(0, index));
        
        int tempIndex = response.indexOf("\",\"closed_at\":\"");
        if (tempIndex > -1) {
            tmp = tmp.substring(index + "\",\"closed_at\":\"".length());
            index = tmp.indexOf("\",\"");
            c.setClosedAt(tmp.substring(0, index));
        }
        
        tmp = tmp.substring(index + "\",\"html_url\":\"".length());
        index = tmp.indexOf("\",\"");
        c.setHtmlUrl(tmp.substring(0, index));
        tmp = tmp.substring(index + "\",\"user\":\"".length());
        index = tmp.indexOf("\",\"");
        c.setUser(tmp.substring(0, index));

        tmp = tmp.substring(index + "\",\"labels\":[".length());
        index = tmp.indexOf("],\"");

        Vector<String> labels = new Vector<String>();
        if (!tmp.startsWith("]")) {
            String[] tempArray = tmp.substring(0, index).replace("\"", "").split(",");
            for (String k : tempArray) {
                labels.add(k);
            }
        }
        c.setLabels(labels);

        tmp = tmp.substring(index + "],\"state\":\"".length(), tmp.length());
        c.setState(tmp);

        LOGGER.info("Processed: " + c.getTitle());
        
        return c;
    }
    
    public static List<String> processLabels(String labels) {
        LOGGER.info("processing: " + labels);
        List<String> labelList = new ArrayList<String>();
        
        labels = labels.substring(labels.indexOf(":[\"") + ":[\"".length(), labels.length() - 4);
        String[] tmp = labels.split("\",\"");
        for (String temp : tmp) {
            labelList.add(temp);
        }
        
        return labelList;
    }
    
    public static Vector<GithubIssue> processIssueResponse(String response) {
        LOGGER.info("processing: " + response);
        Vector<GithubIssue> listOfIssues = new Vector<GithubIssue>();
        String[] v = response.split(",\\{\"gravatar_id\":\"");
        v[0] = v[0].substring(29);
        for (String i : v) {
            listOfIssues.add(GithubHelper.processSingleIssueResponse("{\"gravatar_id\":\"" + i));
        }
        return listOfIssues;
    }
}
