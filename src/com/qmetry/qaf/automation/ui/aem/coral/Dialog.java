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
