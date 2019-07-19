# qaf-aem-support
AEM CoralUI functional test automation support

**qaf-aem-support** provides inbuilt `coral-ui` components and a custom locator strategy to locate element through coral-query. 

## Testing AEM custom components
For users who want to test their custom components before use in production, this library also provides AEM authoring pages implementation. In most of the cases following are AEM authoring functionality required to test AEM custom components:
	
  * Check Whether Page Present
  * Create page
  * Open page
  * Add component
  * Publish page
  * Delete page
  * Copy an existing
 
In-built steps (AEMAuthoringSteps.java) are available to achieve above functionality to use in BDD or in Code. 

* To create, copy, move, delete page in-built steps uses [aem-testing-clients](https://github.com/adobe/aem-testing-clients) which is stable maintained by adobe team and works with any version of AEM.  If you want to use UI to create/delete/move page you can use AEMSitesPage.  
* In order to open page, add component and validate functionality you automate through UI using AEMEditorPage. 
