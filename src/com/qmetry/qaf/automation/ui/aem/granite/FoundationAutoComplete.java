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
package com.qmetry.qaf.automation.ui.aem.granite;

import java.util.List;

import org.openqa.selenium.Keys;

import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.*;
import com.qmetry.qaf.automation.ui.aem.coral.Container;
import com.qmetry.qaf.automation.ui.aem.coral.CoralUIComponent;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

/**
 * Represents <a href=
 * "https://helpx.adobe.com/experience-manager/6-5/sites/developing/using/reference-materials/granite-ui/api/jcr_root/libs/granite/ui/components/coral/foundation/clientlibs/foundation/js/autocomplete/index.html"
 * >foundation-autocomplete</a>
 * 
 * @author chirag.jayswal
 */
public class FoundationAutoComplete extends Container {

	@FindBy(locator = "css=input")
	private CoralUIComponent input;

	@FindBy(locator = FOUNDATION_LIST_ITEM_LOC_KEY)
	public List<CoralUIComponent> pickerListItems;

	public FoundationAutoComplete(String locator) {
		super(locator);
	}

	public FoundationAutoComplete(QAFExtendedWebElement parent, String locator) {
		super(parent, locator);
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		input.sendKeys(keysToSend);
		if (pickerListItems.size() > 0) {
			pickerListItems.get(0).click();
		} else {
			input.sendKeys(Keys.ENTER);
		}
	}

	@Override
	public void clear() {
		input.clear();
	}

	public CoralUIComponent getInput() {
		return input;
	}

}
