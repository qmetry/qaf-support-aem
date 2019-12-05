/*******************************************************************************
 * Copyright (c) 2019 Infostretch Corporation
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.qmetry.qaf.automation.ui.aem.admin.utils;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

import javax.ws.rs.core.NewCookie;

import org.hamcrest.Matchers;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.step.WsStep;
import com.qmetry.qaf.automation.ui.aem.AEMEnvironment;
import com.qmetry.qaf.automation.util.StringUtil;
import com.qmetry.qaf.automation.util.Validator;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author chirag.jayswal
 *
 */
public class AEMAuthonicator {

	public static String getToken() {

		String token = getBundle().getString("aem.login-token");
		if (StringUtil.isBlank(token)) {
			token = createNewToken();
			getBundle().setProperty("aem.login-token", token);
		}
		return token;
	}

	public static void clearToken() {
		getBundle().clearProperty("aem.login-token");
	}

	private static String createNewToken() {
		String request = AEMEnvironment.LOGIN_REQ_CALL.value();

		ClientResponse res = WsStep.userRequests(request);
		Validator.assertThat(res.getStatus(), Matchers.equalTo(200));

		for (NewCookie coockie : res.getCookies()) {
			if (coockie.getName().equalsIgnoreCase(AEMEnvironment.LOGIN_TOKEN_NAME.value())) {
				return coockie.getValue();
			}
		}
		return "";
	}
	

	public static void singOut() {
		CommonStep.get(AEMEnvironment.BASE_URL.value()+"/system/sling/logout.html");
		clearToken();
	}
}
