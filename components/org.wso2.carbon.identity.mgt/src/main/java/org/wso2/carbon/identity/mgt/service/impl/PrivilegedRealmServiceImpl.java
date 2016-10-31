/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.mgt.service.impl;

import org.wso2.carbon.identity.mgt.common.CarbonRealmServiceImpl;
import org.wso2.carbon.identity.mgt.service.PrivilegedRealmService;
import org.wso2.carbon.identity.mgt.store.AuthorizationStore;
import org.wso2.carbon.identity.mgt.store.CredentialStore;
import org.wso2.carbon.identity.mgt.store.IdentityStore;
import org.wso2.carbon.identity.mgt.store.PrivilegedCredentialStore;
import org.wso2.carbon.identity.mgt.store.PrivilegedIdentityStore;

/**
 * Extended Realm Service Implementation.
 *
 * @param <T1>
 * @param <T2>
 */
public class PrivilegedRealmServiceImpl<T1 extends PrivilegedIdentityStore, T2 extends PrivilegedCredentialStore>
        extends CarbonRealmServiceImpl implements PrivilegedRealmService {


    public PrivilegedRealmServiceImpl(IdentityStore identityStore, CredentialStore credentialStore,
                                      AuthorizationStore authorizationStore) {
        super(identityStore, credentialStore, authorizationStore);
    }

    @Override
    public T1 getIdentityStore() {
        return null;
    }

    @Override
    public T2 getCredentialStore() {
        return null;
    }
}
