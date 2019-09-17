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

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

/**
 * @author chirag.jayswal
 *
 */
public class ByCQ extends By {

	private static final String CORAL_QUERY_PREFIX = "var lst = []; var d=document||window.document; var w= window.parent||window; $=w.$||$; ";
	private static final String CORAL_QUERY = "$(d).find(arguments[0]).each( function(i,item) { lst.push(item); } ); return lst;";
	private static final String CORAL_CHILD_QUERY = "$(d).find(arguments[0]).find(arguments[1]).each( function(i,item) { lst.push(item); } ); return lst;";

	private static final String TXT_EQ_EXT = "$.expr[':'].textEquals = function(el, i, m) {" 
			+ "    var searchText = m[3];"
			+ "    var match = $(el).text().trim().match(\"^\" + searchText + \"$\");"
			+ "    return match && match.length > 0;"
			+ "}";
	
	private String selector;
	private String ext="";
	
	public ByCQ(String selector) {
		this.selector = selector;
		initExt();
	}

	@SuppressWarnings("unchecked")
	public List<WebElement> findElements(SearchContext context) {
		Object res;
		if (context instanceof RemoteWebElement) {
			res = ((JavascriptExecutor) ((RemoteWebElement) context).getWrappedDriver())
					.executeScript(ext + CORAL_CHILD_QUERY, context, selector);
		} else {
			res = ((JavascriptExecutor) context).executeScript(ext + CORAL_QUERY, selector);
		}

		return (List<WebElement>) res;
	}

	@Override
	public String toString() {
		return "Using Coral Query: " + selector;
	}
	
	private void initExt() {
		ext=CORAL_QUERY_PREFIX;
		if(selector.contains(":textEquals")) {
			ext = CORAL_QUERY_PREFIX  + TXT_EQ_EXT;
		}
	}
}
