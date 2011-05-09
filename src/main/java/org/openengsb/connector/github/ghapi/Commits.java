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

public class Commits extends APIAbstract {

    public Commits(GitHubAPI a) {
        super(a);
    }

    /**
     * Fetches a list of commits for a given branch of a repository
     * 
     * @param owner
     * @param repositoryName
     * @param branch
     * @return a Response object containing the list of commits
     */
    public Response list(String owner, String repositoryName, String branch) {
        return httpGet("https://github.com/api/v2/json/commits/list/" + encode(owner) + "/" + encode(repositoryName)
                + "/" + encode(branch));
    }

    /**
     * Fetches a list of commits that modified a given file
     * 
     * @param owner
     * @param repositoryName
     * @param branch
     * @param path
     * @return a Response object containing the list of commits
     */
    public Response list_for_file(String owner, String repositoryName, String branch, String path) {
        return httpGet("https://github.com/api/v2/json/commits/list/" + encode(owner) + "/" + encode(repositoryName)
                + "/" + encode(branch) + "/" + encode(path));
    }

    /**
     * Fetches details of a commit, given it's SHA hash
     * 
     * @param owner
     * @param repositoryName
     * @param sha
     * @return a Response object containing the information for the commit
     */
    public Response commit(String owner, String repositoryName, String sha) {
        return httpGet("https://github.com/api/v2/json/commits/show/" + encode(owner) + "/" + encode(repositoryName)
                + "/" + encode(sha));
    }
}