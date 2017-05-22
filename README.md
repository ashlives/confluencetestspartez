# README #

This README would normally document whatever steps are necessary to get your application up and running.

## Setup ##
- Gekodriver/Chromedriver is to be picked up from local path instead of the repository. Hence replace the path in this statement with path to your local machine: System.setProperty("webdriver.gecko.driver","C:\\selenium-java-3.4.0/geckodriver.exe");

## Test Execution Prequisits ##

- User should be already logged in to https://ashishtestspartez.atlassian.net/wiki
- Login credentials for test confluence instance (ashish.p.deshmukh@gmail.com/Password123$)
- Login is done before every test execution

## Test 1: Creating a wiki page ##

- This test fails if message appears "Something went wrong. Please save your changes..". Possible cause is poor network connection or suddent interuption in network.

## Test 2: Editing Restrictions ##

- This test has three options, to set the restrictions as none, editing restricted, view and editing restricted.
- Since this test needs an already existing page, its necessary for Test 1 to execute first, i.e a page to be created. Test 2 execute afterwards editing the restrictions of the page created in Test 1.
- This test fails couple of times since the previously created page was not showing up quickly in All Updates page. Specifically when there were more than one Confluence Space.

## Exploratory Testing ##

- Pages can be dragged and rearranged to change the order.
- Pages can be sorted out in ascending order.
- Sort icon will only appear if pages are not sorted in ascending order.
- Reset icon will only appear if pages are sorted but change is not saved.
- A parent page can be moved to as a child page by dragging it onto the parent page.
- Entire chile page structure can be assigned to another parent page my dragging from the required parent node.
- Change the page heirarchy in the tree. Navigate to anyother tab. Return back to Reorder tab. Changes are saved.
- A page can be made Orphan by dragging it above the Space name i.e. root of the Tree.
- Subpages of a page can be sorted out as well within

**Bugs/Improvements found in Exploratory testing**

Bug 1
- Create three pages i.e. Page 1, Page 2, Page 3. Page 3 is child of Page 2 and Page 2 is child of Page 1.
- Drag Page 2 out from Page 1 creating it as a page under home instead of Page 1.
- Observe that Expand (down arrow) is still visible in front of Page 1 even when it does not have any child pages


Improvement 1
- When I visit Reorder Page from Menu, Tree is always in expanded state. But when I do the same from other tabs in the Space tools, Tree is collapsed. This is inconsistent. It would be better to keep the tree in expanded form always.

**Risk**

*Accidentally making a page Orphan.*

This happened when I dragged the page above the Space/Root page. When I returned to Space, it became difficult for me to locate the orphaned page. The only way was to go to Space Tools to find this page.



