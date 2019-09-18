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

import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.ACTION_BAR;

import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.StringUtil;

/**
 * Represents <a href=
 * "https://helpx.adobe.com/experience-manager/6-3/sites/developing/using/reference-materials/coral-ui/coralui3/Coral.ActionBar.html">Coral.ActionBar</a>
 * that can contain arbitrary items.
 * 
 * @author chirag.jayswal
 *
 */
public class ActionBar<T extends ActionBarItem<T>> extends Container {

	public ActionBar() {
		this(ACTION_BAR.locator());
	}

	public ActionBar(QAFExtendedWebElement parent) {
		this(parent, ACTION_BAR.locator());
	}

	public ActionBar(String locator) {
		super(locator);
	}

	public ActionBar(QAFExtendedWebElement parent, String locator) {
		super(parent, locator);
	}

	public CoralUIComponent getActionItem(T t) {
		if (null != t.getParent()) {
			getActionItem(t.getParent()).click();
		}
		CoralUIComponent actionItem = StringUtil.isNotBlank(t.getLocator()) ? getItemByLocator(t.getLocator())
				: StringUtil.isNotBlank(t.getIcon()) ? getItemWithIcon(t.getIcon()) : getItemByText(t.getLabel());

		// need to check under more items?
		if ((!actionItem.isPresent() || !actionItem.isDisplayed())&& getItemWithIcon("more").isPresent()) {
			getItemWithIcon("more").click();
		}
		return actionItem;
	}
	
	public void perform(T t) {
		getActionItem(t).click();
	}
	
}
