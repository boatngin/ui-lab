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
- Step 3.8, run 1, result, elapsed time and pizza name:
- Step 3.8, run 2, result, elapsed time and pizza name:

## Part B
1.
2.
3.
4.
5.
6.
7.
