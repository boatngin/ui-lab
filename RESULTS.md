# UI lab results

Tool parts that I chose: Part 2 (Selenium WebDriver in Java) and Part 3 (Robot Framework)

## Part 0
- Step 0.1, Chrome version: Google Chrome 158.0.8127.88 (Official Build) (arm64)
- Step 0.3, ChromeDriver version: ChromeDriver 158.0.8127.88 (0cefb18236a23db1ebb6b2bc040fbbaf8cbe759e-refs/branch-heads/8127_36@{#32}), at ~/drivers/chromedriver-mac-arm64/chromedriver, platform mac-arm64
- Step 0.4, locator of Pizza, Please!: `button[name="pizza-please"]` (in Selenium `By.name("pizza-please")`), finds 1 element
- Step 0.4, locator of Excluded tools: `select` (the only list on the page, a `select multiple`), finds 1 element
- Step 0.4, locator of Must be vegetarian: `#default-checkbox`, finds 1 element (its label reads Must be vegetarian)
- Step 0.4, locator of Custom Pizza Name: `input#pizza-name`, finds 1 element. `#pizza-name` alone finds 2 after Pizza, Please!, because the heading of the answer has the same id
- Step 0.4, `$$("#floating_filled").length` gave 3, and `//label[normalize-space()='Max Calories per Slice']/preceding-sibling::input` finds exactly 1 element
- Step 0.5, pizza name, tool and calories of the run by hand: Lab Pizza 20261125-121711, Tool: Scissors, Calories per slice: 400 (Dough: Thin)

| Step | Actual result | Pass or fail |
|------|---------------|--------------|
| 1 | The heading reads Looking to break out of your pizza routine? | Pass |
| 2 | After Advanced is on, Max Calories per Slice (showing 1000), Excluded tools (Knife, Pizza cutter, Scissors), Must be vegetarian and Custom Pizza Name appear | Pass |
| 3 | Max Calories per Slice shows 600 (1000 replaced), Knife is selected, Must be vegetarian is ticked, the name box shows Lab Pizza 20261125-121711 | Pass |
| 4 | Name: Lab Pizza 20261125-121711, Tool: Scissors (not Knife), Calories per slice: 400 (600 or fewer) | Pass |
| 5 | Please log in first. appears below the buttons (the rating request answered 401) | Pass |
| 6 | TODO: open /admin, check that both boxes hold admin, click Sign in | TODO |
| 7 | TODO: find the pizza name in Latest pizza recommendations and compare its tool | TODO |

## Part 2: Selenium WebDriver in Java
- Step 2.4, the failure line for the wrong title: `[ERROR]   FirstTest.webFormOpens:20 expected: <Web Form> but was: <Web form>` (BUILD FAILURE; after changing the title back to "Web form" the test passed again)
- Step 2.15, QP01Test run 1, result and time: passed at 12:22:11 on 25 November 2026, `Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 8.434 s`, BUILD SUCCESS (Maven total 10.345 s)
- Step 2.15, QP01Test run 2, result and time: passed at 12:22:22 on 25 November 2026, `Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 8.427 s`, BUILD SUCCESS (Maven total 9.739 s), with a new pizza name
- Step 2.20, full run 1, time and number of tests: 25 November 2026, finished 12:24:31, `Tests run: 5, Failures: 0, Errors: 0, Skipped: 0`, BUILD SUCCESS, total time 23.589 s (QP01PageObjectTest 9.061 s, pizza Lab Pizza 20261125-122410, screenshots in `selenium-java/screenshots/20261125-122410`)
- Step 2.20, full run 2, time and number of tests: 25 November 2026, finished 12:24:55, `Tests run: 5, Failures: 0, Errors: 0, Skipped: 0`, BUILD SUCCESS, total time 23.109 s (QP01PageObjectTest 8.272 s, pizza Lab Pizza 20261125-122434, screenshots in `selenium-java/screenshots/20261125-122434`, report in `selenium-java/reports/uilab.QP01PageObjectTest.txt`)

## Part 3: Robot Framework
- robot --version: Robot Framework 7.5.1 (Python 3.12.7 on darwin)
- Step 3.2, failure message for the wrong title: `Title should have been 'Web Form' but was 'Web form'.` (1 test, 0 passed, 1 failed; SeleniumLibrary saved selenium-screenshot-1.png. With the title back to Web form: 1 test, 1 passed, 0 failed)
- Step 3.8, run 1, result, elapsed time and pizza name: PASS (1 test, 1 passed, 0 failed), started 12:30:35, elapsed 00:00:09.715, Lab Pizza 20261125-123041 (Tool: Scissors, Calories per slice: 600), evidence in `robot/results/run1`
- Step 3.8, run 2, result, elapsed time and pizza name: PASS (1 test, 1 passed, 0 failed), started 12:30:45, elapsed 00:00:11.404, Lab Pizza 20261125-123052 (Tool: Pizza cutter, Calories per slice: 500), evidence in `robot/results/run2`
- Note: while building Step 3.7, one visible-window run right after adding step 5 failed. I did not keep its log, and the next six runs (three headless, three visible) all passed. A likely cause is the pizza animation that flies over the buttons after Pizza, Please!, but I could not reproduce it.

## Part B
1. The three number boxes of QuickPizza share the id `floating_filled`. In Step 0.4 `$$("#floating_filled").length` returned 3, so `By.id("floating_filled")` would always hit the first box and could never tell Max Calories per Slice apart from Min or Max Number of Toppings. The tests therefore step from the label text, `//label[normalize-space()='Max Calories per Slice']/preceding-sibling::input`, which found exactly 1 element. I would ask the developers to give each box its own id, for example `id="max-calories"`, and to link the label with `for="max-calories"`. Then a test can use `By.id("max-calories")`, which is short, fast and does not break when the label wording or the layout changes. A matching `for` also lets screen readers announce the right label, so the fix helps users as well as testers.

2. Step 7 is a consistency oracle: it compares two outputs of the same system, the tool on the recommendation and the `(tool=...)` in the admin list, instead of comparing with a value written in advance. QuickPizza picks the pizza at random, so the test cannot know the tool beforehand, but it can demand that both views agree. In my run 1 of Step 3.8 the page said Tool: Scissors and the admin line had to contain `(tool=Scissors,`. It would catch a fault where the server saves a different pizza from the one it shows the customer, or where the admin list maps the tool field wrongly, for example always showing the first tool in the list.

3. A check would read every ingredient of the recommendation and compare it with a list of non-vegetarian ingredients, such as pepperoni, ham, bacon, anchovies or salami. The test needs a trustworthy source for which ingredients are vegetarian. The page shows only names, so the test needs that list from the specification or from the API. Step 4.6 of the sheet shows that the answer of `POST /api/pizza` also carries `"vegetarian": true`, so a test could assert that flag and check each ingredient against the backend list. For example, in my run 1 the ingredients were Extra cheese, Ricotta cheese, Spinach, Olive oil, San Marzano tomatoes, Mozzarella fior di latte, Garlic and Kalamata olives, and none of them is meat.

4. Selenium WebDriver in Java waits only where I tell it to. Every late element needed an explicit `wait.until(ExpectedConditions...)`, and without it WaitTest failed with `NoSuchElementException ... "#box0"`. Robot Framework also needs waits, but `Wait Until Element Is Visible`, `Wait Until Element Contains` and the library option `timeout=10s` made them one short line each. Writing QP-01 was quicker in Robot Framework, because the keywords read like the steps of the test case. Java needed a base class, two page objects and imports before QP01PageObjectTest looked that clean. Run times were close: QP01Test took 8.4 s and QP01PageObjectTest 8.3 to 9.1 s in Java, against 9.7 s and 11.4 s for the Robot scenario. Robot Framework's `log.html` was clearly the more readable evidence, with every keyword, its arguments and the screenshots inline. Maven's report only says `Tests run: 1, Failures: 0`, so the Java evidence depends on the screenshots that I saved myself.

5. I would use Robot Framework for Project 2. First, the resource file let me name keywords after the steps of QP-01, such as `Turn On Advanced` and `Log In As Admin`, so each automated test matches its written test case step by step, which Project 2 asks for. Second, every run writes `log.html` and `report.html` with the screenshots inside, so the evidence of two full runs, as in `robot/results/run1` and `run2`, comes with no extra code. Java gave me more control, but it needed more code for the same result.

6. (Part 2) In QP01Test the calories locator is written inline in the test method, so a new locator means editing the XPath in the test itself, and in every other test that copied it. In QP01PageObjectTest nothing changes at all: only the constant `MAX_CALORIES` in `HomePage.java` changes, and `showAdvanced`, `setMaxCalories` and `maxCalories` all use it. The page object makes the repair one line in one file, however many tests call `home.setMaxCalories(600)`. The test keeps describing what the user does, not how the page is built.

7. (Part 3) I would change only the variable `${MAX CALORIES}` in the Variables section of `resources/quickpizza.resource`. The keywords `Turn On Advanced` and `Fill In The Pizza Request` use that variable, and `pizza_scenario.robot` never contains a locator, only steps such as `Fill In The Pizza Request    600    Knife    ${name}`. So the test file stays the same, and every test that imports the resource is repaired at once.
