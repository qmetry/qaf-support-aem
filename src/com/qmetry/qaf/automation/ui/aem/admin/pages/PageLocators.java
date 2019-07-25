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

import static com.qmetry.qaf.automation.ui.aem.coral.CoralLocators.Repository.setDefault;

import com.qmetry.qaf.automation.ui.aem.coral.CoralLocators;

/**
 * @author chirag.jayswal
 *
 */
public interface PageLocators {

	public interface EditorPageLocators {
		public static final String EDITOR_CONTENT_PANEL_LOC_KEY = "editor.contentpanel.loc";
		public static final String SIDE_PANEL_LOC_KEY = "editor.side.panel.loc";
		public static final String SIDE_PANEL_TOGGLE_LOC_KEY = "editor.side.panel.toggle.loc";
		public static final String PUBLISH_REQ_LOC_KEY = "editor.publish.request.loc";
		public static final String COMP_PLACEHOLDER_LOC_KEY = "editor.component.placeholder.loc";
		public static final String EDITABLETOOLBAR_LOC_KEY = "editor.editable.toolbar.loc";
		public static final String EDITABLE_COMP_LOC_KEY = "editor.editable.comp.loc";
		public static final String EDITABLE_COMP_BY_DATA_PATAH_LOC_FORMAT = "editor.editable.comp.by.datapath.loc";
	}

	public interface SitesPageLocators {
		public static final String ADMIN_ANALYTICS_TIMEFRAME_LOC_KEY = "siteadmin.admin.analytics.timeframe.loc";
		public static final String PAGES_VIEW_TARGET_LOC_KEY = "siteadmin.admin.pages.view.target.loc";
		public static final String GRID_TITLEBAR_LOC_KEY = "siteadmin.admin.grid.titalbar.loc";
		public static final String APPS_BUTTON_LOC_KEY = "siteadmin.admin.apps.button.loc";
	}

	public interface CreatePageFormLocators {
		public static final String NAME_LOC_KEY = "createpage.name.field.loc";
		public static final String TITLE_LOC_KEY = "createpage.title.field.loc";
		public static final String TAGS_LOC_KEY = "createpage.tags.field.loc";
		public static final String PAGE_TITLE_LOC_KEY = "createpage.pagetitle.field.loc";
		public static final String NAV_TITLE_LOC_KEY = "createpage.navtitle.field.loc";
		public static final String ALIAS_LOC_KEY = "createpage.alias.field.loc";
		public static final String ADVANCE_TAB_LOC_KEY = "createpage.alias.field.loc";
	}

	// to set default values for locators, one can override it by providing in
	// properties file.
	static class PageRepository {

		public static void setDefaults() {
			if (!CoralLocators.DEF_VALUES.containsKey(SitesPageLocators.ADMIN_ANALYTICS_TIMEFRAME_LOC_KEY)) {

				setDefault(SitesPageLocators.ADMIN_ANALYTICS_TIMEFRAME_LOC_KEY,
						"{\"locator\":\"css=.cq-siteadmin-admin-analytics-timeframe\",\"desc\":\"siteadmin admin analytics timeframe drop down\"}");

				setDefault(SitesPageLocators.PAGES_VIEW_TARGET_LOC_KEY,
						"{\"locator\":\"CQ=[data-shell-collectionpage-view-target='.cq-siteadmin-admin-childpages']\",\"desc\":\"siteadmin page\"}");

				setDefault(SitesPageLocators.GRID_TITLEBAR_LOC_KEY,
						"{\"locator\":\"CQ=betty-titlebar\",\"desc\":\"siteadmin page grid title bar\"}");

				setDefault(SitesPageLocators.APPS_BUTTON_LOC_KEY,
						"{\"locator\":\"CQ=button:has([icon=apps])\",\"desc\":\"Apps button\"}");

				// editor page
				setDefault(EditorPageLocators.EDITOR_CONTENT_PANEL_LOC_KEY,
						"{\"locator\":\"css=.editor-panel\",\"desc\":\"Editor page content\"}");

				setDefault(EditorPageLocators.SIDE_PANEL_LOC_KEY,
						"{\"locator\":\"css=.sidepanel\",\"desc\":\"Side panel\"}");

				setDefault(EditorPageLocators.SIDE_PANEL_TOGGLE_LOC_KEY,
						"{\"locator\":\"css=.toggle-sidepanel\",\"desc\":\"Side panel toggle button\"}");

				setDefault(EditorPageLocators.PUBLISH_REQ_LOC_KEY,
						"{\"locator\":\"CQ=[title='Publish Request']\",\"desc\":\"Publish Request Action item\"}");

				setDefault(EditorPageLocators.COMP_PLACEHOLDER_LOC_KEY,
						"{\"locator\":\"css=.cq-Overlay--placeholder\",\"desc\":\"Component Placeholder\"}");

				setDefault(EditorPageLocators.EDITABLETOOLBAR_LOC_KEY,
						"{\"locator\":\"CQ=#EditableToolbar:visible\",\"desc\":\"Editable Toolbar\"}");

				setDefault(EditorPageLocators.EDITABLE_COMP_LOC_KEY,
						"{\"locator\":\"css=.cq-droptarget\",\"desc\":\"Editable Component\"}");
				
				setDefault(EditorPageLocators.EDITABLE_COMP_BY_DATA_PATAH_LOC_FORMAT,
						"'{'\"locator\":\"CQ=.cq-droptarget[data-path$=''{0}'']\",\"desc\":\"Editable Component with data-path {0}\"'}'");

				// Create Page Form Locators
				setDefault(CreatePageFormLocators.NAME_LOC_KEY,
						"{\"locator\":\"name=pageName\",\"desc\":\"Name field\"}");

				setDefault(CreatePageFormLocators.TITLE_LOC_KEY,
						"{\"locator\":\"name=./jcr:title\",\"desc\":\"Title field\"}");

				setDefault(CreatePageFormLocators.TAGS_LOC_KEY,
						"{\"locator\":\"CQ=.foundation-field-edit:has(label:contains(Tags))\",\"desc\":\"Tags field\"}");

				setDefault(CreatePageFormLocators.PAGE_TITLE_LOC_KEY,
						"{\"locator\":\"name=./pageTitle\",\"desc\":\"Page-title field\"}");

				setDefault(CreatePageFormLocators.NAV_TITLE_LOC_KEY,
						"{\"locator\":\"name=./navTitle\",\"desc\":\"Navigation Title field\"}");

				setDefault(CreatePageFormLocators.ALIAS_LOC_KEY,
						"{\"locator\":\"name=./sling:alias\",\"desc\":\"Alias field\"}");

				setDefault(CreatePageFormLocators.ADVANCE_TAB_LOC_KEY,
						"{\"locator\":\"link=ADVANCED\",\"desc\":\"Advance  tab\"}");
				
				
				
			}
		}
	}

}
