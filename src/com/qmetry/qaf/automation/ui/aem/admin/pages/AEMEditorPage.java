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

import static com.qmetry.qaf.automation.ui.aem.AEMEnvironment.EDITOR_URL;

import java.text.MessageFormat;
import java.util.List;

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
	@FindBy(locator = EDITOR_CONTENT_PANEL_LOC_KEY)
	private Container contentPanel;

	private ActionBar<EditorActionBarItems> actionBar;

	@FindBy(locator = SIDE_PANEL_LOC_KEY)
	private EditorPageSidePanel sidePanel;

	//@FindBy(locator = COMP_PLACEHOLDER_LOC_KEY)
	@FindBy(locator = EDITABLE_COMP_LOC_KEY)
	private EditableComponent componentPlaceholder;

	@FindBy(locator = EDITABLETOOLBAR_LOC_KEY)
	private Container editableToolBar;

	@FindBy(locator = EDITABLE_COMP_LOC_KEY)
	private List<EditableComponent> editableComponents;

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

	public List<EditableComponent> getEditableComponents() {
		return editableComponents;
	}

	public EditableComponent getEditableComponents(String dataPath) {
		return new EditableComponent(MessageFormat.format(EDITABLE_COMP_BY_DATA_PATAH_LOC_FORMAT, dataPath));
	}
	
	public EditableComponent getComponentPlaceholder() {
		return componentPlaceholder;
	}

	public class EditableComponent extends Container {
		public EditableComponent(String locator) {
			super(locator);
		}

		public Container getEditableToolBar() {
			click();
			return editableToolBar;
		}
		
		public void perform(ComponentActions action) {
			CoralUIComponent actionItem = getEditableToolBar().getItemByLocator("CQ=[data-action="+action.name()+"]");
			actionItem.click();
		}
	}

	public enum ComponentActions{EDIT,CONFIGURE,COPY,CUT,DELETE,INSERT,GROUP,LABELS;}

	public enum EditorActionBarItems implements ActionBarItem<EditorActionBarItems> {
		SidepanelToggle("", "railLeft", SIDE_PANEL_TOGGLE_LOC_KEY),

		PublishRequest("", "", PUBLISH_REQ_LOC_KEY),

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
			this(label, "", parent);
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

	public class EditorPageSidePanel extends SidePanel {

		@FindBy(locator = "CQ=coral-tab[title='Assets']")
		private AssetsTab assetsTab;

		@FindBy(locator = "CQ=coral-tab[title='Components']")
		private CoralUIComponent componentsTab;

		@FindBy(locator = "CQ=coral-tab[title='Content Tree']")
		private CoralUIComponent contentsTab;

		public AssetsTab selectAssetsTab() {
			selectTab(assetsTab);
			// getButtonWithIcon("asset").click();
			return assetsTab;
		}

		public CoralUIComponent selectComponentsTab() {
			selectTab(componentsTab);
			return componentsTab;
		}

		public CoralUIComponent selectContentsTab() {
			selectTab(contentsTab);
			return contentsTab;
		}

		private void selectTab(CoralUIComponent tab) {
			if (!isOpened()) {
				openSidePanel();
			}
			tab.click();
		}

		public EditorPageSidePanel(String locator) {
			super(locator);
		}

		public class AssetsTab extends Container {
			@FindBy(locator = "CQ=#assetsearch")
			private CoralUIComponent filter;

			public AssetsTab(QAFExtendedWebElement parent, String locator) {
				super(parent, locator);
			}
		}
	}
}
