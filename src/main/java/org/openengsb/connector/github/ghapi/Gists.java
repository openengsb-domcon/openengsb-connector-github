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

/**
 * ghapi
 * A Java wrapper for the GitHub API
 * 
 * Copyright (c) 2010 Idlesoft.
 * 
 * Licensed under the New BSD License.
 */

package org.openengsb.connector.github.ghapi;

public class Gists extends APIAbstract {

    public Gists(GitHubAPI a) {
        super(a);
    }

    /**
     * Get a Gist's Metadata
     * 
     * @param gistId
     * @return
     */
    public Response get_metadata(String gistId) {
        return httpGet("https://gist.github.com/api/v1/json/" + encode(gistId));
    }

    /**
     * Get a Gist's Content
     * 
     * @param gistId
     * @param filename
     * @return
     */
    public Response get_content(String gistId, String filename) {
        return httpGet("https://gist.github.com/raw/" + encode(gistId) + "/" + encode(filename));
    }

    /**
     * List a User's Public Gists
     * 
     * @param username
     * @return
     */
    public Response list_gists(String username) {
        return httpGet("https://gist.github.com/api/v1/json/gists/" + encode(username));
    }
}