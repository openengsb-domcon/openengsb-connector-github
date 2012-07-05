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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

import java.rmi.RemoteException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.openengsb.core.api.ekb.PersistInterface;
import org.openengsb.domain.issue.Field;
import org.openengsb.domain.issue.Issue;
import org.openengsb.domain.issue.IssueAttribute;
import org.openengsb.domain.issue.Priority;
import org.openengsb.domain.issue.Status;
import org.openengsb.domain.issue.Type;

public class GithubServiceUT {

    private GithubService githubClient;
    private String repository = "ENTER_YOUR_TESTREPO_HERER_TO_RUN_TEST";
    private String repositoryOwner = "ENTER_YOUR_TESTOWNER_HERER_TO_RUN_TEST";

    @Before
    public void setUp() throws Exception {
        githubClient = new GithubService("id");
        githubClient.setRepository(repository);
        githubClient.setRepositoryOwner(repositoryOwner);
        githubClient.setGithubPassword("ENTER_YOUR_PWD_HERER_TO_RUN_TEST");
        githubClient.setGithubUser("ENTER_YOUR_ID_HERER_TO_RUN_TEST");
        PersistInterface persistInterface = mock(PersistInterface.class);
        githubClient.setPersistInterface(persistInterface);
    }

    @Test
    public void testCreateIssueAndLoginWithWrongUserdata_shouldFail() throws RemoteException {
        githubClient.setGithubPassword("wrongPWD");
        Issue issue = createIssue("id1");
        int oldNumber = githubClient.getIssues().size();
        
        githubClient.createIssue(issue);
        
        assertThat(githubClient.getIssues().size(), is(oldNumber));
    }

    @Test
    public void testCreateIssue_shouldCreateIssue() throws Exception {
        Issue issue = createIssue("id1");
        int oldNumber = githubClient.getIssues().size();
        
        githubClient.createIssue(issue);
        
        assertThat(githubClient.getIssues().size(), is(oldNumber + 1));
    }

    @Test
    public void testAddCommentWithIncorrectLogin_shouldFail() throws Exception {
        githubClient.setGithubPassword("wrongPWD");
        int oldNumber = githubClient.getComments(1).size();
        
        githubClient.addComment("1", "TestComment");
        
        assertThat(githubClient.getComments(1).size(), is(oldNumber));
    }

    @Test
    public void testAddComment_shouldAddComment() throws Exception {
        int oldNumber = githubClient.getComments(1).size();
        
        githubClient.addComment("1", "TestComment");
        
        assertThat(githubClient.getComments(1).size(), is(oldNumber + 1));
        
    }

    @Test
    public void testUpdateIssue_shouldUpdateIsse() throws Exception {
        HashMap<IssueAttribute, String> changes = new HashMap<IssueAttribute, String>();
        changes.put(Field.STATUS, "closed");
        changes.put(Field.DESCRIPTION, "updated des");
        changes.put(Field.SUMMARY, "updated summary");
        changes.put(Field.COMPONENT, "1,2");
        
        githubClient.updateIssue("5", "ChangeComment", changes);
        
        Issue tmp = githubClient.getIssue("5");
        assertThat(tmp.getDescription(), is("updated des"));
        assertThat(tmp.getSummary(), is("updated summary"));
        assertThat(tmp.getStatus(), is(Status.CLOSED));

        int k = 0;
        for (String i : tmp.getComponents()) {
            if (i.equals("1") || i.equals("2") || i.equals("plsLabel")) {
                k++;
            }
        }
        assertThat(k, is(2));

        changes.clear();
        changes.put(Field.DESCRIPTION, "commentbla");
        changes.put(Field.SUMMARY, "bla");
        changes.put(Field.COMPONENT, "plsLabel");
        githubClient.updateIssue("5", "ChangeComment", changes);
        
    }
    @Test
    public void testUpdateIssueWithIncorrectLogin_shouldFail() throws Exception {
        githubClient.setGithubPassword("wrongPWD");
        HashMap<IssueAttribute, String> changes = new HashMap<IssueAttribute, String>();
        changes.put(Field.STATUS, "closed");
        changes.put(Field.DESCRIPTION, "updated des");
        changes.put(Field.SUMMARY, "updated summary");
        
        githubClient.updateIssue("5", "ChangeComment", changes);
        
        Issue tmp = githubClient.getIssue("5");
        assertThat(tmp.getDescription(), is("commentbla"));
        assertThat(tmp.getSummary(), is("bla"));
    }
   
    @Test
    public void testGetIssue_shouldGetIssue() throws Exception {
        Issue i = githubClient.getIssue("3");
        
        assertThat(i.getDescription(), is("Comment_Of_TestIssue03"));
        assertThat(i.getSummary(), is("TestIssue03"));
        assertThat(i.getId(), is("3"));
        assertThat(i.getStatus(), is(Status.NEW));
    }
    
    @Test
    public void testAddAndRemoveComponent_shouldAddAndRemoveComponent() throws Exception {
        int oldSize = githubClient.getLabels().size();
        
        githubClient.addComponent("TestComponent");
        
        assertThat(githubClient.getLabels().size(), is(oldSize + 1));
        githubClient.removeComponent("TestComponent");
        assertThat(githubClient.getLabels().size(), is(oldSize));
    }
    
    private Issue createIssue(String id) {
        Issue issue = new Issue();
        issue.setId(id);
        issue.setSummary("summary");
        issue.setDescription("description");
        issue.setReporter("reporter");
        issue.setOwner("owner");
        issue.setPriority(Priority.NONE);
        issue.setStatus(Status.NEW);
        issue.setDueVersion("versionID1");
        issue.setType(Type.BUG);
        return issue;
    }
}
