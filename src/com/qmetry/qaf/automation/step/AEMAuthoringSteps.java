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
package com.qmetry.qaf.automation.step;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;
import static com.qmetry.qaf.automation.ui.aem.AEMEnvironment.BASE_URL;
import static com.qmetry.qaf.automation.ui.aem.AEMEnvironment.PASSWORD;
import static com.qmetry.qaf.automation.ui.aem.AEMEnvironment.USER_NAME;

import java.net.URI;

import org.apache.sling.testing.clients.ClientException;
import org.apache.sling.testing.clients.SlingHttpResponse;
import org.hamcrest.Matchers;

import com.adobe.cq.testing.client.CQClient;
import com.qmetry.qaf.automation.core.AutomationError;
import com.qmetry.qaf.automation.ui.aem.admin.modal.PageFormBean;
import com.qmetry.qaf.automation.ui.aem.admin.pages.AEMEditorPage;
import com.qmetry.qaf.automation.ui.aem.admin.pages.AEMEditorPage.EditorActionBarItems;
import com.qmetry.qaf.automation.ui.aem.admin.pages.AEMSitesPage;
import com.qmetry.qaf.automation.ui.aem.admin.pages.AEMSitesPage.TitleBarActions;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.util.XPathUtils;

/**
 * Helper methods/common steps for AEM authoring.
 * 
 * @author chirag.jayswal
 *
 */
public class AEMAuthoringSteps {

	public static void createNewPage(PageFormBean newPageFormData) {
		AEMSitesPage sitesPage = new AEMSitesPage();
		sitesPage.launchPage(newPageFormData.getParentPath());
		sitesPage.getGridTitleBar().getActionItem(TitleBarActions.Page).click();
		newPageFormData.fillUiElements();
	}

	/**
	 * 
	 * @param title -  page title
	 * @param parentPath - path of the parent
	 * @param templatePath - template to be used
	 * @return path of the new create page
	 */
	@QAFTestStep(description="create {title} under {parent-path} using {template-path}")
	public static String createNewPage(String title, String parentPath, String templatePath) {
		String pagePath = "";
		try {
			SlingHttpResponse resp = getClient().createPage(null, title, parentPath, templatePath);
			
			Validator.assertThat(resp.getSlingStatusAsInt(), Matchers.equalTo(200));
			Validator.assertThat(resp.getSlingMessage(), Matchers.equalToIgnoringCase("Page created"));
			pagePath = getValue(resp, "//*[@id=\"Path\"]");
		} catch (ClientException e) {
			throw new AutomationError("Unable to create new page " + title + " under " + parentPath, e);
		}
		return pagePath;
	}

	public static void activatePage(String path) {
		AEMEditorPage editorPage = new AEMEditorPage();
		editorPage.launchPage(path);
		editorPage.getActionBar().getActionItem(EditorActionBarItems.PublishPage).click();	}
	
	/**
	 * Publish page which you are editing. 
	 */
	public static void publishPage() {
		AEMEditorPage editorPage = new AEMEditorPage();
		editorPage.getActionBar().getActionItem(EditorActionBarItems.PublishPage).click();
	}

	/**
	 * 
	 * @param path
	 * @param targetPath
	 */
	@QAFTestStep(description="copy {page-path} page to {target-path}")
	public static void copyPage(String path, String targetPath) {
		try {
			SlingHttpResponse resp = getClient().copyPage(new String[] { path }, null, null, targetPath, "false", false);
			Validator.assertThat(resp.getSlingStatusAsInt(), Matchers.equalTo(200));

		} catch (ClientException e) {
			throw new AutomationError("Unable to cope page " + path + " to " + targetPath, e);
		}
	}

	@QAFTestStep(description="delete {page-path} page")
	public static void deletePage(String pagePath) {
		try {
			if (getClient().exists(pagePath)) {
				SlingHttpResponse resp = getClient().deletePage(new String[] { pagePath },
						false, false);
				if (resp.getSlingStatusAsInt() != 200 || getClient().exists(pagePath)) {
					throw new AutomationError("Unable to delete page " + pagePath + resp.toString());
				}
			} else {
				throw new AutomationError(
						"Please provide correct page path to delete the page. Path " + pagePath + " does not exist.");
			}
		} catch (ClientException e) {
			throw new AutomationError("Unable to delete page " + pagePath, e);
		}
	}

	public static AEMEditorPage editPage(String path, String title) {
		AEMSitesPage sitesPage = new AEMSitesPage();
		sitesPage.launchPage(path);

		return sitesPage.editPage(title);
	}

	/**
	 * 
	 * @param path
	 */
	@QAFTestStep(description="open {page-path} page to edit")
	public static void editPage(String path) {
		AEMEditorPage editorPage = new AEMEditorPage();
		editorPage.launchPage(path);
	}

	public static void addComponent(String id) {
		//TODO: provide implementation
	}

	public static CQClient getClient() {
		CQClient client = (CQClient) getBundle().getObject("aem.cq.client");
		if (null == client) {
			try {
				client = CQClient.Builder.create(new URI(BASE_URL.value()), USER_NAME.value(), PASSWORD.value())
						.build();
				getBundle().setProperty("aem.cq.client", client);
			} catch (Exception e) {
				throw new AutomationError("Unable to crate CQClient", e);
			}
		}
		return client;
	}

	private static String getValue(SlingHttpResponse resp, String path) {
		return XPathUtils.read(resp.getContent()).getString(path);
	}

}
