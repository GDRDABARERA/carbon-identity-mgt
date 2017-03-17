/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.carbon.identity.mgt.test.osgi;


import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.mgt.test.osgi.util.IdentityMgtOSGiTestUtils;
import org.wso2.carbon.identity.recovery.password.AdminForcePasswordResetManager;
import java.nio.file.Paths;
import java.util.List;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;

/**
 * Test Class For Forced Password Reset
 */

@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class AdminForcedPasswordResetTest {

    @Configuration
    public Option[] createConfiguration() {

        List<Option> optionList = IdentityMgtOSGiTestUtils.getDefaultSecurityPAXOptions();

        optionList.add(systemProperty("java.security.auth.login.config")
                .value(Paths.get(IdentityMgtOSGiTestUtils.getCarbonHome(), "conf", "security",
                        "carbon-jaas.config").toString()));

        return optionList.toArray(new Option[optionList.size()]);
    }

    @Test(groups = {"getGeneratedOTP"})
    public void testGetGeneratedOTP() {
        String otp = AdminForcePasswordResetManager.getInstance().generateOTPValue();
        Assert.assertNotNull(otp, "Failed to Generate the OTP");

    }



}
