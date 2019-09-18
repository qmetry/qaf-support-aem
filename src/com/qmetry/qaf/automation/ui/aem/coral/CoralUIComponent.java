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
package com.qmetry.qaf.automation.ui.aem.coral;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.Response;

import com.qmetry.qaf.automation.ui.webdriver.CommandTracker;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebComponent;
import com.qmetry.qaf.automation.util.LocatorUtil;

/**
 * Represents <a href=
 * "https://helpx.adobe.com/experience-manager/6-5/sites/developing/using/reference-materials/coral-ui/coralui3/Coral.Component.html">Coral.Component</a>
 * - Base class for every coral component.
 * 
 * @author chirag.jayswal
 *
 */
public class CoralUIComponent extends QAFWebComponent {

	public CoralUIComponent(QAFExtendedWebDriver driver) {
		super(driver);
	}

	public CoralUIComponent(String locator) {
		super(locator);
	}

	public CoralUIComponent(QAFExtendedWebElement parent, String locator) {
		super(parent, locator);
	}

	@Override
	public String getId() {
		if (id == null || id == "-1") {
			beforeCommand(this, new CommandTracker(DriverCommand.FIND_ELEMENT, new HashMap<String, Object>()));
		}
		return super.getId();
	}
	

	protected Response execute(String command, Map<String, ?> parameters) {
		getId();
		return super.execute(command, parameters);
	}

	@Override
	protected void initLoc(String locator) {
		this.locator = locator;
	}

	protected By getBy() {
		// make locator transformation lazy so that can consider default values
		if ((null == by) && StringUtils.isNotBlank(this.locator)) {
			super.initLoc(CoralLocators.Repository.get(this.locator));
			by = LocatorUtil.getBy(this.locator);
		}

		return by;
	}

	public Object getProperty(String property) {
		return executeScript("get('" + property + "');");
	}

	public void setProperty(String property, Object value) {
		executeScript("get('" + property + "'," + value + ");");
	}

	public QAFExtendedWebElement getParentElement(){
		return parentElement;
	}
	
	static {
		//register locator strategy if not registered yet...
		System.setProperty("CQ", "com.qmetry.qaf.automation.ui.aem.coral.ByCQ");
	}
}
