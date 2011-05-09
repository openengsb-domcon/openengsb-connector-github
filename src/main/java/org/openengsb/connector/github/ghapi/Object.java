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

public class Object extends APIAbstract {

    public Object(GitHubAPI a) {
        super(a);
    }

    /**
     * Fetches the contents of the tree given the tree SHA
     * 
     * @param owner
     * @param repositoryName
     * @param sha
     * @return a Response object containing the contents of the tree
     */
    public Response tree(String owner, String repositoryName, String sha) {
        return httpGet("https://github.com/api/v2/json/tree/show/" + encode(owner) + "/" + encode(repositoryName) + "/"
                + encode(sha));
    }

    /**
     * Fetches data about a blob given it's tree SHA and path
     * 
     * @param owner
     * @param repositoryName
     * @param treeSha
     * @param path
     * @return a Response object containing the blob information
     */
    public Response blob(String owner, String repositoryName, String treeSha, String path) {
        return httpGet("https://github.com/api/v2/json/blob/show/" + encode(owner) + "/" + encode(repositoryName) + "/"
                + encode(treeSha) + "/" + encode(path));
    }

    /**
     * Fetches a list of all blobs for a certain tree SHA
     * 
     * @param owner
     * @param repositoryName
     * @param treeSha
     * @return a Response object containing the list of blobs
     */
    public Response list_blobs(String owner, String repositoryName, String treeSha) {
        return httpGet("https://github.com/api/v2/json/blob/all/" + encode(owner) + "/" + encode(repositoryName) + "/"
                + encode(treeSha));
    }

    /**
     * Get the raw data of a blob
     * 
     * @param owner
     * @param repositoryName
     * @param sha
     * @return a Response object containing the raw data
     */
    public Response raw(String owner, String repositoryName, String sha) {
        return httpGet("https://github.com/api/v2/json/blob/show/" + encode(owner) + "/" + encode(repositoryName) + "/"
                + encode(sha));
    }
}