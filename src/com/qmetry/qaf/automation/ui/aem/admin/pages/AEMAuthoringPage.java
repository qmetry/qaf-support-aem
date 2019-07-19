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
package com.qmetry.qaf.automation.ui.aem.admin.pages;

import static com.qmetry.qaf.automation.ui.aem.admin.utils.AEMAuthonicator.getToken;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.WAIT_INDICATOR_LOC_KEY;

import org.openqa.selenium.Cookie;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.aem.AEMEnvironment;
import com.qmetry.qaf.automation.ui.aem.admin.pages.PageLocators.PageRepository;
import com.qmetry.qaf.automation.ui.aem.coral.CoralLocators;
import com.qmetry.qaf.automation.ui.aem.coral.CoralUIComponent;
import com.qmetry.qaf.automation.ui.aem.coral.Dialog;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

/**
 * @author chirag.jayswal
 *
 */
public class AEMAuthoringPage extends WebDriverBaseTestPage<WebDriverTestPage> {
	protected String pageUrl;
	protected  PageRepository p = new PageRepository();

	@FindBy(locator = WAIT_INDICATOR_LOC_KEY)
	private QAFWebElement waitIndicator;

	public AEMAuthoringPage() {
		this.pageUrl = pageProps.getString("env.aem.baseurl");
	}

	public AEMAuthoringPage(String pageUrl) {
		this.pageUrl = AEMEnvironment.BASE_URL.value() + pageUrl;
	}

	public Dialog getDialog() {
		return new Dialog();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qmetry.qaf.automation.ui.AbstractTestPage#openPage(com.qmetry.qaf.
	 * automation.ui.api.PageLocator, java.lang.Object[])
	 */
	@Override
	protected void openPage(PageLocator locator, Object... args) {
		String target = "/";
		if (null != args && args.length > 0) {
			target = args[0].toString();
		}
		driver.get(pageUrl + target);
	}

	@Override
	protected void beforeLaunch(Object... args) {
		super.beforeLaunch(args);
		String cookieName = AEMEnvironment.LOGIN_TOKEN_NAME.value();
		if (null == parent && null == driver.manage().getCookieNamed(cookieName)) {
			driver.get(AEMEnvironment.BASE_URL.value());

			Cookie cookie = new Cookie(cookieName, getToken());
			driver.manage().addCookie(cookie);
		}
	}

	@Override
	protected void afterLaunch() {
		// needs to be moved as fallback
		Dialog d = new Dialog();
		if (d.isPresent() && d.isDisplayed()) {
			d.dismiss();
		}

		CoralUIComponent closeBtn = new CoralUIComponent(CoralLocators.Repository.DIALOG_CLOSE_BTN.locator());
		if (closeBtn.isPresent()) {
			closeBtn.click();
		}
	}

	@Override
	public void waitForPageToLoad() {
		waitIndicator.waitForNotPresent();
		waitForAjaxToComplete();
		super.waitForPageToLoad();
	}

	public void launchPage(Object... args) {
		super.launchPage(null, args);
	}
}
