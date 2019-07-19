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
