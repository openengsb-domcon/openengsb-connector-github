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

import org.openengsb.core.api.descriptor.ServiceDescriptor;
import org.openengsb.core.api.descriptor.ServiceDescriptor.Builder;
import org.openengsb.core.common.AbstractConnectorProvider;

public class GithubConnectorProvider extends AbstractConnectorProvider {

    @Override
    public ServiceDescriptor getDescriptor() {
        Builder builder = ServiceDescriptor.builder(strings);
        builder.id(this.id);
        builder.name("service.name").description("service.description");

        builder.attribute(builder.newAttribute().id(Constants.GITHUB_USER).name(Constants.GITHUB_USER_NAME)
                .description(Constants.GITHUB_USER_DESCRIPTION).build());
        builder.attribute(builder.newAttribute().id(Constants.GITHUB_PWD).name(Constants.GITHUB_PWD_NAME)
                .description(Constants.GITHUB_PWD_DESCRIPTION).defaultValue("").asPassword().build());
        builder.attribute(builder.newAttribute().id(Constants.GITHUB_REPO).name(Constants.GITHUB_REPO_NAME)
                .description(Constants.GITHUB_REPO_DESCRIPTION).defaultValue("").required().build());
        builder.attribute(builder.newAttribute().id(Constants.GITHUB_REPO_OWNER).name(Constants.GITHUB_REPO_OWNER_NAME)
                .description(Constants.GITHUB_REPO_OWNER_DESCRIPTION).defaultValue("").required().build());

        return builder.build();
    }

}
