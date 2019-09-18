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
