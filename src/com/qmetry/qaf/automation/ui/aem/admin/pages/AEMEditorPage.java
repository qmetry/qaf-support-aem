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

import static com.qmetry.qaf.automation.ui.aem.AEMEnvironment.EDITOR_URL;
import com.qmetry.qaf.automation.ui.aem.admin.pages.PageLocators.EditorPageLocators;
import com.qmetry.qaf.automation.ui.aem.coral.ActionBar;
import com.qmetry.qaf.automation.ui.aem.coral.ActionBarItem;
import com.qmetry.qaf.automation.ui.aem.coral.Container;
import com.qmetry.qaf.automation.ui.aem.coral.CoralUIComponent;
import com.qmetry.qaf.automation.ui.aem.coral.SidePanel;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.annotations.PageIdentifier;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;;

/**
 * 
 * @author chirag.jayswal
 *
 */
public class AEMEditorPage extends AEMAuthoringPage implements EditorPageLocators {

	@PageIdentifier
	@FindBy(locator = "key="+EDITOR_CONTENT_PANEL_LOC_KEY)
	private Container contentPanel;

	private ActionBar<EditorActionBarItems> actionBar;

	@FindBy(locator = SIDE_PANEL_LOC_KEY)
	private EditorPageSidePanel sidePanel;

	@FindBy(locator = COMP_PLACEHOLDER_LOC_KEY)
	private Container componentPlaceholder;

	@FindBy(locator = EDITABLETOOLBAR_LOC_KEY)
	private Container editableToolBar;

	public AEMEditorPage() {
		super(EDITOR_URL.value());
		actionBar = new ActionBar<EditorActionBarItems>(contentPanel);
	}

	public ActionBar<EditorActionBarItems> getActionBar() {
		return actionBar;
	}

	public EditorPageSidePanel getSidePanel() {
		return sidePanel;
	}

	public EditorPageSidePanel openSidePanel() {
		if (!sidePanel.isOpened()) {
			getActionBar().getActionItem(EditorActionBarItems.SidepanelToggle).click();
		}
		return sidePanel;
	}

	public void closeSidePanel() {
		if (sidePanel.isOpened()) {
			getActionBar().getActionItem(EditorActionBarItems.SidepanelToggle).click();
		}
	}

	public enum EditorActionBarItems implements ActionBarItem<EditorActionBarItems> {
		SidepanelToggle("", "railLeft", SIDE_PANEL_TOGGLE_LOC_KEY),

		PublishRequest("", "",PUBLISH_REQ_LOC_KEY), 
		
		Edit,

		EditModes("", "chevronDown", ""),
		// EditModes sub actions
		EditModes_Edit("Edit", EditModes),

		Scaffolding("Scaffolding", EditModes),

		Developer("Developer", EditModes),

		Design("Design", EditModes),

		Timewarp("Timewarp", EditModes),

		Preview,

		Styles("", "brush", ""),
		// Styles sub actions
		Close("", "close", Styles),

		Check("", "check", Styles),

		Properties("", "properties", ""),
		// Properties sub actions
		StartWorkflow("Start Workflow", Properties),

		LockPage("Lock Page", Properties),
		
		PublishPage("Publish Page", Properties),
		
		UnpublishPage("Unpublish Page", Properties),

		ViewAsPublished("View as Published", Properties),

		ViewInAdmin("View in Admin", Properties),

		NoteAdd("", "noteAdd", "");

		private String label;
		private String icon;
		private String locator;
		private EditorActionBarItems parent;

		EditorActionBarItems() {
			this.label = name();
		}

		EditorActionBarItems(String label, String icon, String locator) {
			this.label = label;
			this.icon = icon;
			this.locator = locator;
		}

		EditorActionBarItems(String label, EditorActionBarItems parent) {
			this(label,"",parent);
		}

		EditorActionBarItems(String label, String icon, EditorActionBarItems parent) {
			this.label = label;
			this.icon = icon;
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
		public EditorActionBarItems getParent() {
			return parent;
		}

		@Override
		public String getLocator() {
			return locator;
		}
	}
	
	public class EditorPageSidePanel extends SidePanel{
		
		@FindBy(locator="CQ=coral-tab[title='Assets']")
		private CoralUIComponent assetsTab;
		
		@FindBy(locator="CQ=coral-tab[title='Components']")
		private CoralUIComponent componentsTab;
		
		@FindBy(locator="CQ=coral-tab[title='Content Tree']")
		private CoralUIComponent contentsTab;

		public void selectAssetsTab() {
			getButtonWithIcon("asset").click();
		}
		
		public EditorPageSidePanel(String locator) {
			super(locator);
		}
		
		public class AssetsTab extends Container{
			@FindBy(locator="CQ=#assetsearch")
			private CoralUIComponent filter;
			
			public AssetsTab(QAFExtendedWebElement parent, String locator) {
				super(parent, locator);
			}
			
			
			
		}
		
	}
}
