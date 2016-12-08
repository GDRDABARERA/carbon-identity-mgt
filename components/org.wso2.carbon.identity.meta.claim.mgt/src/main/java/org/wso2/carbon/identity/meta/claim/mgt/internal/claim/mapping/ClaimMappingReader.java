/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.meta.claim.mgt.internal.claim.mapping;

import org.wso2.carbon.identity.meta.claim.mgt.exception.ClaimMappingReaderException;
import org.wso2.carbon.identity.meta.claim.mgt.util.ClaimMgtConstants;
import org.wso2.carbon.identity.mgt.exception.CarbonIdentityMgtConfigException;
import org.wso2.carbon.identity.mgt.util.FileUtil;
import org.wso2.carbon.identity.mgt.util.IdentityMgtConstants;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Claim Mapping Reader.
 */
public class ClaimMappingReader {

    public ClaimMappingReader() {

    }

    private static ClaimMappingFile buildClaimMappings() throws ClaimMappingReaderException {
        ClaimMappingFile claimConfig = null;

        Path file = Paths.get(IdentityMgtConstants.getCarbonHomeDirectory().toString(), "conf", "identity",
                ClaimMgtConstants.CLAIM_MAPPING_FILE);
        try {
            claimConfig = FileUtil.readConfigFile(file, ClaimMappingFile.class);
        } catch (CarbonIdentityMgtConfigException e) {
            throw new ClaimMappingReaderException("Couldn't read the claim-mapping.yml file successfully.", e);
        }

        return claimConfig;
    }

    /**
     * Provides the claim mappings of a given dialect.
     *
     * @return Map(application claim : root claim URI)
     */
    public static Map<String, Map<String, String>> getClaimMappings() throws ClaimMappingReaderException {
        ClaimMappingFile claimMappingFile = buildClaimMappings();
        List<ClaimMappingEntry> claimMappingEntryList = claimMappingFile.getClaimMapping();
        return claimMappingEntryList.stream().filter(Objects::nonNull)
                .filter(claimMappingEntry -> !claimMappingEntry.getMappings().isEmpty()).collect(Collectors
                        .toMap(claimMappingEntry -> claimMappingEntry.getMappingDialectURI(),
                                claimMappingEntry -> getMappings(claimMappingEntry)));

    }

    private static Map<String, String> getMappings(ClaimMappingEntry claimMappingEntry) {
        return claimMappingEntry.getMappings().entrySet().stream().collect(Collectors
                .toMap(p -> appendDialect(claimMappingEntry.getMappingDialectURI(), p.getKey()), Map.Entry::getValue));

    }

    private static String appendDialect(String dialect, String claim) {
        if (dialect.isEmpty()) {
            return claim;
        }
        //In case claim dialect in not followed by '/', add it.
        if (!dialect.endsWith("/")) {
            dialect = dialect + "/";
        }
        return dialect + claim;
    }

}