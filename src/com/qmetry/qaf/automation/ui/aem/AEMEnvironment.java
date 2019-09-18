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
