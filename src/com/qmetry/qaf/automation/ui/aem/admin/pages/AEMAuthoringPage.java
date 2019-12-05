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
package com.qmetry.qaf.automation.ui.aem.admin.pages;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;
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

	@FindBy(locator = WAIT_INDICATOR_LOC_KEY)
	private QAFWebElement waitIndicator;

	public AEMAuthoringPage() {
		this(getBundle().getString("env.aem.baseurl"));
	}

	public AEMAuthoringPage(String pageUrl) {
		this.pageUrl = AEMEnvironment.BASE_URL.value() + pageUrl;
		PageRepository.setDefaults();
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
		if (closeBtn.isPresent() && closeBtn.isDisplayed()) {
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
