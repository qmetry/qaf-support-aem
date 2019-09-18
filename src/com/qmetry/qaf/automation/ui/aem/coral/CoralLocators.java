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

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

import java.text.MessageFormat;
import java.util.Properties;

/**
 * @author chirag.jayswal
 *
 */
public interface CoralLocators {

	public static final String ICON_LOC_KEY = "coralui.icon.loc";
	public static final String ACTION_BAR_LOC_KEY = "coralui.actionbar.loc";

	public static final String DIALOG_LOC_KEY = "coralui.dialog.loc";
	public static final String DIALOG_CLOSE_BTN_LOC_KEY = "coralui.dialog.close.loc";

	public static final String GRID_LOC_KEY = "coralui.grid.loc";
	public static final String GRID_SELECTALL_LOC_KEY = "coralui.grid.selectall.loc";

	public static final String GRID_ROW_LOC_KEY = "coralui.grid.row.loc";
	public static final String GRID_ROW_CELL_LOC_KEY = "coralui.grid.row.cell.loc";

	public static final String GRID_ROW_SELECT_LOC_KEY = "coralui.grid.row.select.loc";
	public static final String GRID_ROW_WITH_CONTENT_LOC_KEY = "coralui.grid.row.withcontent.loc";
	public static final String GRID_ROW_WITH_COLCONTENT_LOC_KEY = "coralui.grid.row.withcolcontent.loc";

	public static final String BUTTON_BY_ICON_LOC_FORMAT_KEY = "coralui.button.byicon.loc.format";
	public static final String BUTTON_BY_LABEL_LOC_FORMAT_KEY = "coralui.button.bylabel.loc.format";
	public static final String BUTTON_BY_CLS_LOC_FORMAT_KEY = "coralui.button.byclass.loc.format";
	public static final String CYCLEBUTTON_BY_LABEL_LOC_FORMAT_KEY = "coralui.cyclebutton.bylabel.loc.format";

	public static final String ITEM_BY_ICON_LOC_FORMAT_KEY = "coralui.item.byicon.loc.format";

	public static final String LIST_ITEM_LOC_KEY = "coralui.list.item.loc";
	public static final String LIST_ITEM_LABEL_LOC_FORMAT_KEY = "coralui.list.item.bylabel.loc.format";
	
	public static final String FOUNDATION_LIST_ITEM_LOC_KEY = "coralui.foundation.list.item.loc";
	public static final String FOUNDATION_LIST_ITEM_LABEL_LOC_FORMAT_KEY = "coralui.foundation.list.item.bylabel.loc.format";
	public static final String SELECT_LIST_LOC_KEY = "coralui.select.selectlist.loc";

	public static final String WAIT_INDICATOR_LOC_KEY = "coralui.wait.loc";

	public static final Properties DEF_VALUES = new Properties();

	public enum Repository {

		DIALOG(DIALOG_LOC_KEY, "{\"locator\":\"CQ=coral-dialog:visible\",\"desc\":\"Dialog\"}"),

		DIALOG_CLOSE_BTN(DIALOG_CLOSE_BTN_LOC_KEY, "{\"locator\":\"CQ=[coral-close]:visible,[aria-label=close]:visible\",\"desc\":\"Dialog\"}"),

		// https://helpx.adobe.com/experience-manager/6-3/sites/developing/using/reference-materials/coral-ui/coralui3/Coral.Table.html

		GRID(GRID_LOC_KEY, "{\"locator\":\"CQ=[role=grid]\",\"desc\":\"Grid\"}"),

		GRID_ROW(GRID_ROW_LOC_KEY,
				"{\"locator\":\"CQ=[role=row]:not(:has([role=columnheader]))\",\"desc\":\"Grid row\"}"),

		GRID_ROW_CELL(GRID_ROW_CELL_LOC_KEY, "{\"locator\":\"CQ=[role=gridcell]\",\"desc\":\"Grid row\"}"),

		// [coral-table-select] - Select/unselect all table items.
		GRID_SELALL(GRID_SELECTALL_LOC_KEY,
				"{\"locator\":\"CQ=[coral-table-select]\",\"desc\":\"Grid Select All Option\"}"),

		// [coral-table-rowselect] - Select/unselect the table item.
		GRID_ROW_SEL(GRID_ROW_SELECT_LOC_KEY,
				"{\"locator\":\"CQ=[coral-table-rowselect]\",\"desc\":\"Grid row selection option\"}"),

		// [role=row]:has([role=gridcell]:nth-child(4):contains(text))
		GRID_ROW_WITH_CONTENT_FORMAT(GRID_ROW_WITH_CONTENT_LOC_KEY,
				"'{'\"locator\":\"CQ=[role=row]:has([role=gridcell]:contains(''{0}''))\",\"desc\":\"Grid row with content {0}\"'}'"), GRID_ROW_WITH_COLCONTENT_FORMAT(
						GRID_ROW_WITH_COLCONTENT_LOC_KEY,
						"'{'\"locator\":\"CQ=[role=row]:has([role=gridcell]:nth-child({1}):contains(''{0}''))\",\"desc\":\"Grid row with content {0} in cell {1}\"'}'"),

		ICON(ICON_LOC_KEY, "{\"locator\":\"CQ=coral-icon\",\"desc\":\"Icon\"}"),

		ACTION_BAR(ACTION_BAR_LOC_KEY, "{\"locator\":\"CQ=action-bar,coral-actionbar\",\"desc\":\"Action Bar\"}"),
		
		WAIT_INDICATOR(WAIT_INDICATOR_LOC_KEY, "{\"locator\":\"CQ=coral-wait:visible\",\"desc\":\"Wait indicator\"}"),


		// formats
		BUTTON_BY_ICON_FORMAT(BUTTON_BY_ICON_LOC_FORMAT_KEY,
				"'{'\"locator\":\"CQ=button:has([icon=''{0}'']:visible)\",\"desc\":\"Button with Icon {0}\"'}'"),

		CYCLEBUTTON_BY_LABEL_FORMAT(CYCLEBUTTON_BY_LABEL_LOC_FORMAT_KEY,
				"'{'\"locator\":\"CQ=coral-cyclebutton:contains(''{0}''):visible\",\"desc\":\"Button with Label {0}\"'}'"),
		
		BUTTON_BY_LABEL_FORMAT(
						BUTTON_BY_LABEL_LOC_FORMAT_KEY,
						"'{'\"locator\":\"CQ=button:contains(''{0}''):visible\",\"desc\":\"Button with Label {0}\"'}'"),

		BUTTON_BY_CLS_FORMAT(BUTTON_BY_CLS_LOC_FORMAT_KEY,
				"'{'\"locator\":\"CQ=button:hasClass(''{0}'']:visible)\",\"desc\":\"Button with class {0}\"'}'"),

		ITEM_BY_ICON_FORMAT(ITEM_BY_ICON_LOC_FORMAT_KEY,
				"'{'\"locator\":\"CQ=[icon=''{0}'']:parent\",\"desc\":\"Item with Icon {0}\",\"scroll\":\"Always\"'}'"),

		SELECT_LIST(SELECT_LIST_LOC_KEY, "{\"locator\":\"CQ=[role='listbox']\",\"desc\":\"List Box\"}"),

		LIST_ITEM(LIST_ITEM_LOC_KEY,
				"{\"locator\":[\"CQ=[role='option'],[coral-list-item]\",\"CQ=.coral-SelectList-item\" ],\"desc\":\"List Item\", \"scroll\":\"Always\"}"),

		LIST_ITEM_LABEL_FORMAT(LIST_ITEM_LABEL_LOC_FORMAT_KEY,
				"'{'\"locator\":[\"CQ=[role=''option'']:contains(''{0}'')\",\"CQ=.coral-SelectList-item:contains(''{0}'')\" ],\"desc\":\"List Item {0}\",\"scroll\":\"Always\"'}'"),
		
		FOUNDATION_LIST_ITEM(FOUNDATION_LIST_ITEM_LOC_KEY,
				"{\"locator\":\"CQ=[coral-list-item]\",\"desc\":\"Foundation List Item\", \"scroll\":\"Always\"}"),
		
		FOUNDATION_LIST_ITEM_LABEL_FORMAT(FOUNDATION_LIST_ITEM_LABEL_LOC_FORMAT_KEY,
				"'{'\"locator\":\"CQ=[coral-list-item]:contains(''{0}'')\",\"desc\":\"List Item {0}\",\"scroll\":\"Always\"'}'");

		private String key;
		private String defVal;

		private Repository(String key, String defVal) {
			this.key = key;
			this.defVal = defVal;
			setDefault(key, defVal);
		}

		public String locator(Object... args) {
			if (null != args && args.length>0) {
				return MessageFormat.format(getBundle().getString(key, defVal), args);
			}
			return getBundle().getString(key, defVal);
		}

		public static String get(String key) {
			if (key.startsWith("{")) {
				return key;
			}
			return getBundle().getString(key, DEF_VALUES.getProperty(key, key));
		}
		
		public static void setDefault(String key, String defVal) {
			DEF_VALUES.put(key, defVal);
		}
	}
}
