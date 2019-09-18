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

import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.BUTTON_BY_CLS_FORMAT;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.BUTTON_BY_ICON_FORMAT;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.BUTTON_BY_LABEL_FORMAT;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.ITEM_BY_ICON_FORMAT;

import java.text.MessageFormat;

import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

/**
 * @author chirag.jayswal
 *
 */
public class Container extends CoralUIComponent {

	public Container(String locator) {
		super(locator);
	}

	public Container(QAFExtendedWebElement parent, String locator) {
		super(parent, locator);
	}

	public Button getButtonWithIcon(String icon) {
		Button button = new Button(this, BUTTON_BY_ICON_FORMAT.locator(icon));
		button.setDescription("Button with icon " + icon + " in " + getDescription());

		return button;
	}

	/**
	 * Get button with label.
	 * 
	 * @param label
	 * @return button with provided label
	 */
	public Button getButton(String label) {
		Button button = new Button(this, BUTTON_BY_LABEL_FORMAT.locator(label));
		button.setDescription("Button with lable " + label + " in " + getDescription());

		return button;
	}

	public Button getButtonByClass(String cls) {
		Button button = new Button(this, BUTTON_BY_CLS_FORMAT.locator(cls));
		button.setDescription("Button with lable " + cls + " in " + getDescription());

		return button;
	}

	public CoralUIComponent getItemWithIcon(String icon) {
		CoralUIComponent item = new CoralUIComponent(this, ITEM_BY_ICON_FORMAT.locator(icon));
		item.setDescription("Item with icon " + icon + " in " + getDescription());

		return item;
	}

	public CoralUIComponent getItemByLocator(String locator) {
		CoralUIComponent item = new CoralUIComponent(this, locator);
		return item;
	}

	public CoralUIComponent getItemByText(String text) {
		CoralUIComponent item = new CoralUIComponent(this, MessageFormat
				.format("[\"CQ=button:contains(''{0}''):visible\", \"CQ=:textEquals(''{0}''):visible\"]", text));
		item.setDescription("Item with text " + text + " in " + getDescription());

		return item;

	}

	
}
