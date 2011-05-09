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

public class Repository extends APIAbstract {

    public Repository(GitHubAPI a) {
        super(a);
    }

    /**
     * Searches GitHub for repositories matching supplied query
     * 
     * @param query
     * @return a Response object containing status code and search results
     */
    public Response search(String query) {
        return httpGet("https://github.com/api/v2/json/repos/search/" + encode(query));
    }

    /**
     * Fetches info for a given repository
     * 
     * @param owner
     * @param repositoryName
     * @return a Response object containing status code and repository
     *         information
     */
    public Response info(String owner, String repositoryName) {
        return httpGet("https://github.com/api/v2/json/repos/show/" + encode(owner) + "/" + encode(repositoryName));
    }

    /**
     * Lists repositories of a given user
     * 
     * @param target
     * @return a Response object containing status code and list of repositories
     */
    public Response list(String target) {
        return httpGet("https://github.com/api/v2/json/repos/show/" + encode(target));
    }

    /**
     * Watches a given repository
     * 
     * @param owner
     * @param repositoryName
     * @return a Response object containing status code and updated repository
     *         information
     */
    public Response watch(String owner, String repositoryName) {
        return httpGet("https://github.com/api/v2/json/repos/watch/" + encode(owner) + "/" + encode(repositoryName));
    }

    /**
     * Unwatches a given repository
     * 
     * @param owner
     * @param repositoryName
     * @return a Response object containing status code and updated repository
     *         information
     */
    public Response unwatch(String owner, String repositoryName) {
        return httpGet("https://github.com/api/v2/json/repos/unwatch/" + encode(owner) + "/" + encode(repositoryName));
    }

    /**
     * Forks a given repository
     * 
     * @param owner
     * @param repositoryName
     * @return a Response object containing status code and information about
     *         the new fork
     */
    public Response fork(String owner, String repositoryName) {
        return httpGet("https://github.com/api/v2/json/repos/fork/" + encode(owner) + "/" + encode(repositoryName));
    }

    /**
     * Fetches list of collaborators for a repository
     * 
     * @param owner
     * @param repositoryName
     * @return a Response object containing status code and list of
     *         collaborators
     */
    public Response collaborators(String owner, String repositoryName) {
        return httpGet("https://github.com/api/v2/json/repos/show/" + encode(owner) + "/" + encode(repositoryName)
                + "/collaborators");
    }

    /**
     * Fetches list of forks for a repository
     * 
     * @param owner
     * @param repositoryName
     * @return a Response object containing status code and list of forks
     */
    public Response network(String owner, String repositoryName) {
        return httpGet("https://github.com/api/v2/json/repos/show/" + encode(owner) + "/" + encode(repositoryName)
                + "/network");
    }

    /**
     * Fetches a list of languages used in a repository
     * 
     * @param owner
     * @param repositoryName
     * @return a Response object containing status code and list of languages
     */
    public Response languages(String owner, String repositoryName) {
        return httpGet("https://github.com/api/v2/json/repos/show/" + encode(owner) + "/" + encode(repositoryName)
                + "/languages");
    }

    /**
     * Fetches a list of tags on a repository
     * 
     * @param owner
     * @param repositoryName
     * @return a Response object containing status code and list of tags
     */
    public Response tags(String owner, String repositoryName) {
        return httpGet("https://github.com/api/v2/json/repos/show/" + encode(owner) + "/" + encode(repositoryName)
                + "/tags");
    }

    /**
     * Fetches a list of branches for a repository
     * 
     * @param owner
     * @param repositoryName
     * @return a Response object containing status code and list of branches
     */
    public Response branches(String owner, String repositoryName) {
        return httpGet("https://github.com/api/v2/json/repos/show/" + encode(owner) + "/" + encode(repositoryName)
                + "/branches");
    }
}