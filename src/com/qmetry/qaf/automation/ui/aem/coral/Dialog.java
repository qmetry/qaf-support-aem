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

import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.DIALOG_CLOSE_BTN_LOC_KEY;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.DIALOG;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;

/**
 * @author chirag.jayswal
 *
 */
public class Dialog extends Container {

	@FindBy(locator = DIALOG_CLOSE_BTN_LOC_KEY)
	private Button closeBtn;

	/**
	 * Use this constructor to get current visible dialog. If no visible dialog
	 * exist, it will return false in {@link #isPresent()} or throw element not
	 * found for other operations
	 * 
	 */
	public Dialog() {
		super(DIALOG.locator());
	}

	public Dialog(String locator) {
		super(locator);
	}

	public Dialog(QAFExtendedWebElement parent, String locator) {
		super(parent, locator);
	}

	public void dismiss() {
		closeBtn.click();
	}

}
