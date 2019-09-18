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

import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.LIST_ITEM_LOC_KEY;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.ITEM_BY_ICON_FORMAT;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.LIST_ITEM_LABEL_FORMAT;

import java.util.List;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

/**
 * Component represents <a href=
 * "https://helpx.adobe.com/experience-manager/6-5/sites/developing/using/reference-materials/coral-ui/coralui3/Coral.SelectList.html">coral-selectlist</a>.
 * 
 * @see also {@link Select} - which represents dropdown.
 * 
 * @author chirag.jayswal
 *
 */
public class SelectList extends CoralUIComponent {

	@FindBy(locator = LIST_ITEM_LOC_KEY)
	private List<CoralUIComponent> items;

	public SelectList(String locator) {
		super(locator);
	}

	public SelectList(QAFExtendedWebElement parent, String locator) {
		super(parent, locator);
	}

	public void select(String label) {
		CoralUIComponent option = new CoralUIComponent(this, LIST_ITEM_LABEL_FORMAT.locator(label));
		//option.executeScript("scrollIntoView()") no need code to scroll, it will get scrolled because element meta-data has scroll.
		option.click();
	}
	
	public void selectByIcon(String icon) {
		CoralUIComponent option = new CoralUIComponent(this, ITEM_BY_ICON_FORMAT.locator(icon));
		option.click();
	}
	
	public List<CoralUIComponent> getItems() {
		return items;
	}
}
