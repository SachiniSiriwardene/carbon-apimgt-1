/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.apimgt.api.model.subscription;

import org.wso2.carbon.apimgt.api.InMemorySubscriptionValidationConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entity for keeping API related information.
 */
public class API implements CacheableEntity<String> {

    private int apiId = -1;
    private String provider = null;
    private String name = null;
    private String version = null;
    private String context = null;
    private String policy = null;

    private List<URLMapping> resources = new ArrayList<>();

    public void addResource(URLMapping urlMapping) {

        resources.add(urlMapping);
    }

    public boolean removeResource(URLMapping urlMapping) {

        return resources.remove(urlMapping);
    }

    public List<URLMapping> getAllResources() {

        return resources;
    }

    public int getApiId() {

        return apiId;
    }

    public void setApiId(int apiId) {

        this.apiId = apiId;
    }

    public String getContext() {

        return context;
    }

    public void setContext(String context) {

        this.context = context;
    }

    public String getProvider() {

        return provider;
    }

    public void setProvider(String provider) {

        this.provider = provider;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getVersion() {

        return version;
    }

    public void setVersion(String version) {

        this.version = version;
    }

    public String getPolicy() {

        return policy;
    }

    public void setPolicy(String policy) {

        this.policy = policy;
    }

    public String getCacheKey() {

        return context + InMemorySubscriptionValidationConstants.DELEM_PERIOD + version;
    }
}
