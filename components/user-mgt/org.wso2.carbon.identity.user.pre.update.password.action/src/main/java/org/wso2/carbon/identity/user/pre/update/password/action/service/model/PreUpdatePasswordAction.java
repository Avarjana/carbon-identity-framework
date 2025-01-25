/*
 * Copyright (c) 2025, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.user.pre.update.password.action.service.model;

import org.wso2.carbon.identity.action.management.model.Action;
import org.wso2.carbon.identity.action.management.model.EndpointConfig;

/**
 * PreUpdatePasswordAction.
 */
public class PreUpdatePasswordAction extends Action {

    private final PasswordSharing passwordSharing;

    public PreUpdatePasswordAction(ResponseBuilder responseBuilder) {

        super(responseBuilder);
        this.passwordSharing = responseBuilder.passwordSharing;
    }

    public PreUpdatePasswordAction(RequestBuilder requestBuilder) {

        super(requestBuilder);
        this.passwordSharing = requestBuilder.passwordSharing;
    }

    public PasswordSharing getPasswordSharing() {

        return passwordSharing;
    }

    /**
     * Response Builder for PreUpdatePasswordAction.
     */
    public static class ResponseBuilder extends ActionResponseBuilder {

        private PasswordSharing passwordSharing;

        @Override
        public ResponseBuilder id(String id) {

            super.id(id);
            return this;
        }

        @Override
        public ResponseBuilder type(ActionTypes type) {

            super.type(type);
            return this;
        }

        @Override
        public ResponseBuilder name(String name) {

            super.name(name);
            return this;
        }

        @Override
        public ResponseBuilder description(String description) {

            super.description(description);
            return this;
        }

        @Override
        public ResponseBuilder status(Status status) {

            super.status(status);
            return this;
        }

        @Override
        public ResponseBuilder endpoint(EndpointConfig endpoint) {

            super.endpoint(endpoint);
            return this;
        }

        public ResponseBuilder passwordSharing(PasswordSharing passwordSharing) {

            this.passwordSharing = passwordSharing;
            return this;
        }

        @Override
        public PreUpdatePasswordAction build() {

            return new PreUpdatePasswordAction(this);
        }
    }

    /**
     * Request Builder for PreUpdatePasswordAction.
     */
    public static class RequestBuilder extends ActionRequestBuilder {

        private PasswordSharing passwordSharing;

        public RequestBuilder(Action action) {

            name(action.getName());
            description(action.getDescription());
            endpoint(action.getEndpoint());
            rule(action.getActionRule());
        }

        @Override
        public RequestBuilder name(String name) {

            super.name(name);
            return this;
        }

        @Override
        public RequestBuilder description(String description) {

            super.description(description);
            return this;
        }

        @Override
        public RequestBuilder endpoint(EndpointConfig endpoint) {

            super.endpoint(endpoint);
            return this;
        }

        public RequestBuilder passwordSharing(PasswordSharing passwordSharing) {

            this.passwordSharing = passwordSharing;
            return this;
        }

        @Override
        public PreUpdatePasswordAction build() {

            return new PreUpdatePasswordAction(this);
        }
    }
}
