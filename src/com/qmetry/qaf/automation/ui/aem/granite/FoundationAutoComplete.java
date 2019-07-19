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
