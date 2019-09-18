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

import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.GRID_ROW_CELL_LOC_KEY;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.GRID_ROW_LOC_KEY;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.GRID_ROW_SELECT_LOC_KEY;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.GRID;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.GRID_ROW_WITH_COLCONTENT_FORMAT;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.GRID_ROW_WITH_CONTENT_FORMAT;

import java.util.List;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;;
/**
 * Basic coral-ui grid implementation.
 * @author chirag.jayswal
 *
 */
public class Grid extends Container {
	
	@FindBy(locator=GRID_ROW_LOC_KEY)
	List<GridRow> rows;

	public Grid() {
		super(GRID.locator());
	}
	public Grid(String locator) {
		super(locator);
	}
	public Grid(QAFExtendedWebElement parent, String locator) {
		super(parent, locator);
	}
	
	public GridRow getRowWithContent(String text) {
		return new GridRow(this, GRID_ROW_WITH_CONTENT_FORMAT.locator(text));
	}
	
	/**
	 * 
	 * @param text
	 * @param cellIndex "1-indexed", meaning that the counting starts at 1
	 * @return
	 */
	public GridRow getRowWithCellContent(String text, int cellIndex) {
		return new GridRow(this, GRID_ROW_WITH_COLCONTENT_FORMAT.locator(text,cellIndex));
	}
	
	
	public class GridRow extends CoralUIComponent{
		@FindBy(locator=GRID_ROW_SELECT_LOC_KEY)
		private CoralUIComponent rowSelectionElement;
		
		@FindBy(locator=GRID_ROW_CELL_LOC_KEY)
		List<CoralUIComponent> cells;
		
		public GridRow(QAFExtendedWebElement parent, String locator) {
			super(parent, locator);
		}
		
		public void select() {
			if(!isSelected()) {
				rowSelectionElement.click();
			}
		}
		
		@Override
		public boolean isSelected() {
			return "true".equalsIgnoreCase(getAttribute("selected"));
		}
	}

}
