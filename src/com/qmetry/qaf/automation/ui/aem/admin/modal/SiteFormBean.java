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
package com.qmetry.qaf.automation.ui.aem.admin.modal;

import com.google.gson.Gson;
import com.qmetry.qaf.automation.data.BaseFormDataBean;

/**
 * @author chirag.jayswal
 *
 */
public class SiteFormBean extends BaseFormDataBean {

	private String label;
	private String title;
	private String destPath;
	private boolean isLiveCopy;
	private String[] languages;
	private String[] chapterPages;
	private String bluePrintPath;
	private String[] rolloutConfigs;
	private String siteOwner;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	public boolean isLiveCopy() {
		return isLiveCopy;
	}

	public void setLiveCopy(boolean isLiveCopy) {
		this.isLiveCopy = isLiveCopy;
	}

	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

	public String[] getChapterPages() {
		return chapterPages;
	}

	public void setChapterPages(String[] chapterPages) {
		this.chapterPages = chapterPages;
	}

	public String getBluePrintPath() {
		return bluePrintPath;
	}

	public void setBluePrintPath(String bluePrintPath) {
		this.bluePrintPath = bluePrintPath;
	}

	public String[] getRolloutConfigs() {
		return rolloutConfigs;
	}

	public void setRolloutConfigs(String[] rolloutConfigs) {
		this.rolloutConfigs = rolloutConfigs;
	}

	public String getSiteOwner() {
		return siteOwner;
	}

	public void setSiteOwner(String siteOwner) {
		this.siteOwner = siteOwner;
	}

	public void setLanguages(String languages) {
		this.languages = new Gson().fromJson(languages, String[].class);
	}

	public void setChapterPages(String chapterPages) {
		this.chapterPages = new Gson().fromJson(chapterPages, String[].class);
	}

	public void setRolloutConfigs(String rolloutConfigs) {
		this.rolloutConfigs = new Gson().fromJson(rolloutConfigs, String[].class);
	}

}
