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

package org.openengsb.connector.github.internal;

public class User extends APIAbstract {

    public User(GitHubAPI a) {
        super(a);
    }

    /**
     * Searches GitHub for users matching supplied query
     * 
     * @param query
     * @return a Response object containing status code and search results
     */
    public Response search(String query) {
        return httpGet("https://github.com/api/v2/json/user/search/" + encode(query));
    }

    /**
     * Fetches info for a given user
     * 
     * @param username
     * @return a Response object containing status code and user information
     */
    public Response info(String username) {
        return httpGet("https://github.com/api/v2/json/user/show/" + encode(username));
    }

    /**
     * Fetch a list of people a user is following
     * 
     * @param username
     * @return a Response object containing status code and following list
     */
    public Response following(String username) {
        return httpGet("https://github.com/api/v2/json/user/show/" + encode(username) + "/following");
    }

    /**
     * Fetch a list of a user's followers
     * 
     * @param username
     * @return a Response object containing status code and list of followers
     */
    public Response followers(String username) {
        return httpGet("https://github.com/api/v2/json/user/show/" + encode(username) + "/followers");
    }

    /**
     * Follows a given user
     * 
     * @param username
     * @return a Response object containing status code and response body
     */
    public Response follow(String username) {
        return httpPost("https://github.com/api/v2/json/user/follow/" + encode(username), "");
    }

    /**
     * Unfollows a given user
     * 
     * @param username
     * @return a Response object containing status code and response body
     */
    public Response unfollow(String username) {
        return httpPost("https://github.com/api/v2/json/user/unfollow/" + encode(username), "");
    }

    /**
     * Fetches a list of repositories a user is watching
     * 
     * @param username
     * @return a Response object containing status code and list of watched
     *         repositories
     */
    public Response watching(String username) {
        return httpGet("https://github.com/api/v2/json/repos/watched/" + encode(username));
    }

    /**
     * Fetches a user's public activity feed
     * 
     * @param username
     * @return a Response object containing status code and public activity feed
     */
    public Response activity(String username) {
        return httpGet("https://github.com/" + encode(username) + ".json");
    }

    /**
     * Fetches a user's private activity feed
     * 
     * @param username
     * @param token
     * @return a Response object containing status code and private activity
     *         feed
     */
    public Response private_activity() {
        return httpGet("https://github.com/" + encode(api.api.login) + ".private.json");
    }

    /**
     * Fetches GitHub's public timeline
     * 
     * @return a Response object containing status code and timeline feed
     */
    public Response timeline() {
        return httpGet("https://github.com/timeline.json");
    }
}