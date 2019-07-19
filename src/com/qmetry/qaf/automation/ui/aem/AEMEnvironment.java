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
package com.qmetry.qaf.automation.ui.aem;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

/**
 * @author chirag.jayswal
 *
 */
public enum AEMEnvironment {
	BASE_URL("env.aem.baseurl"), USER_NAME("aem.user"), PASSWORD("aem.password"),

	// optional
	LOGIN_TOKEN_NAME("aem.login.token.name", "login-token"), LOGIN_ENDPOINT("aem.login.endpoint",
			"/libs/granite/core/content/login.html/j_security_check"), EDITOR_URL("aem.editor.url",
					"/editor.html/content/"), SITES_URL("aem.editor.url", "/sites.html/content/"),

	LOGIN_REQ_CALL("aem.login.requestcall", "{'baseUrl':'${env.aem.baseurl}',"
			+ "'endpoint':'/libs/granite/core/content/login.html/j_security_check'," + "'method':'POST',"
			+ "'form-parameters':{" + "		'j_username':'${aem.user}'," + "		'j_password':'${aem.password}',"
			+ "		'j_validate':'true'," + "		'_charset_':'utf-8'" + "	}," + "parameters':{"
			+ "		'aem.login.endpoint':'/libs/granite/core/content/login.html/j_security_check'" + " }" + "}");

	private String key;
	private String defVal;

	private AEMEnvironment(String key) {
		this.key = key;
	}

	private AEMEnvironment(String key, String defVal) {
		this.key = key;
		this.defVal = defVal;
	}

	public String value() {
		return getBundle().getString(key, defVal);
	}

}
