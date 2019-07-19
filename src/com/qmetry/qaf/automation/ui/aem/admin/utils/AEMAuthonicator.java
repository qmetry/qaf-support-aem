/*******************************************************************************
 * QMetry Automation Framework provides a powerful and versatile platform to author 
 * Automated Test Cases in Behavior Driven, Keyword Driven or Code Driven approach
 *                
 * Copyright 2016 Infostretch Corporation
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT
 * OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE
 *
 * You should have received a copy of the GNU General Public License along with this program in the name of LICENSE.txt in the root folder of the distribution. If not, see https://opensource.org/licenses/gpl-3.0.html
 *
 * See the NOTICE.TXT file in root folder of this source files distribution 
 * for additional information regarding copyright ownership and licenses
 * of other open source software / files used by QMetry Automation Framework.
 *
 * For any inquiry or need additional information, please contact support-qaf@infostretch.com
 *******************************************************************************/
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
		CommonStep.get("/system/sling/logout.html");
		clearToken();
	}
}
