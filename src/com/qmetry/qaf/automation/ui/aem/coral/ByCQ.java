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
			+ "};";
	
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
