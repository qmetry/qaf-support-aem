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
	
}
