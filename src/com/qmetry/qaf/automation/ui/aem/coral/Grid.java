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
