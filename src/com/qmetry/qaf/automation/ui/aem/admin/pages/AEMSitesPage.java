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
package com.qmetry.qaf.automation.ui.aem.admin.pages;

import static com.qmetry.qaf.automation.ui.aem.AEMEnvironment.SITES_URL;
import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.CYCLEBUTTON_BY_LABEL_FORMAT;

import com.qmetry.qaf.automation.ui.aem.admin.pages.PageLocators.SitesPageLocators;
import com.qmetry.qaf.automation.ui.aem.coral.ActionBar;
import com.qmetry.qaf.automation.ui.aem.coral.ActionBarItem;
import com.qmetry.qaf.automation.ui.aem.coral.Button;
import com.qmetry.qaf.automation.ui.aem.coral.Grid;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.annotations.PageIdentifier;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

/**
 * 
 * //https://aemserver/editor.html/content/campaigns/kptest/master/aem-target-prod/exp-a/prevention-titletext.html
 * 
 * @author chirag.jayswal
 *
 */
public class AEMSitesPage extends AEMAuthoringPage implements SitesPageLocators {

	@PageIdentifier
	@FindBy(locator = PAGES_VIEW_TARGET_LOC_KEY)
	private QAFWebElement pagesViewTarget;

	@FindBy(locator = GRID_TITLEBAR_LOC_KEY)
	private ActionBar<TitleBarActions> gridTitleBar;

	@FindBy(locator = APPS_BUTTON_LOC_KEY)
	private Button appsBtn;

	private Grid grid;

	public AEMSitesPage() {
		super(SITES_URL.value());
		grid = new Grid();
	}

	/**
	 * page level action bar available on selection of grid row
	 * 
	 * @return
	 */
	public ActionBar<SitePageActions> getActionBar() {
		return new ActionBar<SitePageActions>();
	}

	public Grid getGrid() {
		return grid;
	}

	public ActionBar<TitleBarActions> getGridTitleBar() {
		return gridTitleBar;
	}

	public void deletePage(String title) {
		getGrid().getRowWithContent(title).select();
		getActionBar().getActionItem(SitePageActions.Delete).click();
		getDialog().getButton("Delete").click();
	}

	public AEMEditorPage editPage(String title) {
		getGrid().getRowWithContent(title).select();
		getActionBar().getActionItem(SitePageActions.Edit).click();

		AEMEditorPage editorPage = new AEMEditorPage();
		editorPage.waitForPageToLoad();
		return editorPage;
	}

	public enum TitleBarActions implements ActionBarItem<TitleBarActions> {

		RailTogle("", "railLeft", ""),

		// RailTogle sub actions
		ContentOnly("Content Only", RailTogle),

		Timeline("Timeline", RailTogle),

		References("References", RailTogle),

		Filter("Filter", RailTogle),

		AnalyticsTimeframe("", "", ADMIN_ANALYTICS_TIMEFRAME_LOC_KEY),

		// Analytics_Timeframe sub actions
		Last30DaysData("Last 30 Days Data", AnalyticsTimeframe),

		Last90DaysData("Last 90 Days Data", AnalyticsTimeframe),

		ThisYearData("This Year's Data", AnalyticsTimeframe),

		Create,

		// Create sub actions
		Page("Page", Create),

		Site("Site", Create),

		LiveCopy("Live Copy", Create),

		Launch("Launch", Create),

		LanguageCopy("Language Copy", Create),

		Catalog("Catalog", Create),

		CSVReport("CSV Report", Create),

		GridViewSwitcher("", "", CYCLEBUTTON_BY_LABEL_FORMAT.locator("List View")),

		// GridViewSwitcher sub actions
		ListView("List View", GridViewSwitcher),

		CardView("Card View", GridViewSwitcher),

		ColumnView("Column Copy", GridViewSwitcher),

		ViewSettings("View Settings", GridViewSwitcher);

		String label;
		String locator;
		String icon;

		TitleBarActions parent = null;

		TitleBarActions() {
			this.label = name();
		}

		TitleBarActions(String label, String icon, String locator) {
			this.label = label;
			this.icon = icon;
			this.locator = locator;
		}

		TitleBarActions(String label, TitleBarActions parent) {
			this.label = label;
			this.parent = parent;
		}

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public String getIcon() {
			return icon;
		}

		@Override
		public TitleBarActions getParent() {
			return parent;
		}

		@Override
		public String getLocator() {
			return locator;
		}
	}

	public enum SitePageActions implements ActionBarItem<SitePageActions> {
		Create, Edit, Properties, Copy, Lock, Move, Delete,

		ManagePublish("Manage Publication"),

		QuickPublish("Quick Publish"),

		PublishRequest("Publish Request"),

		// Create sub actions
		Page("Page", Create),

		Site("Site", Create),

		LiveCopy("Live Copy", Create),

		Launch("Launch", Create),

		LanguageCopy("Language Copy", Create),

		Catalog("Catalog", Create),

		CSVReport("CSV Report", Create),

		Version("Live Copy", Create),

		Workflow("Workflow", Version);

		String label;
		SitePageActions parent = null;

		SitePageActions() {
			this.label = name();
		}

		SitePageActions(String label) {
			this(label, null);
		}

		SitePageActions(String label, SitePageActions parent) {
			this.label = label;
			this.parent = parent;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String getIcon() {
			return "";
		}

		@Override
		public SitePageActions getParent() {
			return parent;
		}

		@Override
		public String getLocator() {
			return null;
		}
	}

}
