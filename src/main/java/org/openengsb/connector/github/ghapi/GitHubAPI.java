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

public class GitHubAPI {
    public APIAbstract api = new APIAbstract(this);
    public Repository repo;
    public User user;
    public Commits commits;
    public Issues issues;
    public org.openengsb.connector.github.ghapi.Object object;
    public Gists gists;

    public GitHubAPI() {
        loadAPIs();
    }

    public GitHubAPI loadAPIs() {
        repo = new Repository(this);
        user = new User(this);
        commits = new Commits(this);
        issues = new Issues(this);
        object = new org.openengsb.connector.github.ghapi.Object(this);
        gists = new Gists(this);

        return this;
    }

    public void authenticate(String login, String password) {
        api.login(login, password);
    }

    public void goStealth() {
        api.login(null, null);
    }

    public String getLogin() {
        return api.login;
    }
}