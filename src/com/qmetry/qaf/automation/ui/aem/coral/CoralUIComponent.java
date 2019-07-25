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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.Response;

import com.qmetry.qaf.automation.ui.webdriver.CommandTracker;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebComponent;
import com.qmetry.qaf.automation.util.LocatorUtil;

/**
 * Represents <a href=
 * "https://helpx.adobe.com/experience-manager/6-5/sites/developing/using/reference-materials/coral-ui/coralui3/Coral.Component.html">Coral.Component</a>
 * - Base class for every coral component.
 * 
 * @author chirag.jayswal
 *
 */
public class CoralUIComponent extends QAFWebComponent {

	public CoralUIComponent(QAFExtendedWebDriver driver) {
		super(driver);
	}

	public CoralUIComponent(String locator) {
		super(locator);
	}

	public CoralUIComponent(QAFExtendedWebElement parent, String locator) {
		super(parent, locator);
	}

	@Override
	public String getId() {
		if (id == null || id == "-1") {
			beforeCommand(this, new CommandTracker(DriverCommand.FIND_ELEMENT, new HashMap<String, Object>()));
		}
		return super.getId();
	}
	

	protected Response execute(String command, Map<String, ?> parameters) {
		getId();
		return super.execute(command, parameters);
	}

	@Override
	protected void initLoc(String locator) {
		this.locator = locator;
	}

	protected By getBy() {
		// make locator transformation lazy so that can consider default values
		if ((null == by) && StringUtils.isNotBlank(this.locator)) {
			super.initLoc(CoralLocators.Repository.get(this.locator));
			by = LocatorUtil.getBy(this.locator);
		}

		return by;
	}

	public Object getProperty(String property) {
		return executeScript("get('" + property + "');");
	}

	public void setProperty(String property, Object value) {
		executeScript("get('" + property + "'," + value + ");");
	}

	public QAFExtendedWebElement getParentElement(){
		return parentElement;
	}
	
	static {
		//register locator strategy if not registered yet...
		System.setProperty("CQ", "com.qmetry.qaf.automation.ui.aem.coral.ByCQ");
	}
}
