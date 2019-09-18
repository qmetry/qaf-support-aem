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

import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.SELECT_LIST_LOC_KEY;

import java.util.List;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

/**
 * Component represents <a href=
 * "https://helpx.adobe.com/experience-manager/6-5/sites/developing/using/reference-materials/coral-ui/coralui3/Coral.Select.html">coral-select</a>.
 * 
 * @see also {@link SelectList}
 * 
 * @author chirag.jayswal
 *
 */
public class TagSelector extends CoralUIComponent {

	@FindBy(locator = "CQ=input")
	private CoralUIComponent input;

	@FindBy(locator = SELECT_LIST_LOC_KEY)
	private SelectList selectList;

	public TagSelector(String locator) {
		super(locator);
	}

	public TagSelector(QAFExtendedWebElement parent, String locator) {
		super(parent, locator);
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		for (CharSequence s : keysToSend) {
			input.sendKeys(s);
			//getItems().get(0).click();
			select(s.toString());
		}
	}

	public void select(String label) {
		selectList.select(label);
	}

	public List<CoralUIComponent> getItems() {
		return selectList.getItems();
	}
}
