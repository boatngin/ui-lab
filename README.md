# ui-lab

Automated UI Testing lab (ITDS362). Test case QP-01, "A custom pizza reaches the admin list", automated on [QuickPizza](https://quickpizza.grafana.com) plus tests on Selenium's [web form](https://www.selenium.dev/selenium/web/web-form.html).

Tool parts that I chose: **Part 2, Selenium WebDriver in Java** and **Part 3, Robot Framework**. Results and the reflection are in [RESULTS.md](RESULTS.md).

Both parts read the path of ChromeDriver from the environment variable `CHROMEDRIVER`, for example:

```bash
export CHROMEDRIVER="$HOME/drivers/chromedriver-mac-arm64/chromedriver"
```

## selenium-java/ (Part 2)

A Maven project with JUnit tests: `FirstTest`, `WebFormTest` and `WaitTest` on the web form, `QP01Test` for QP-01, and `QP01PageObjectTest` for QP-01 with the page objects in `pages/`. `screenshots/` holds the pictures of each QP-01 run and `reports/` the Surefire report of the second full run.

```bash
cd selenium-java && mvn test -Dheadless=true
```

## robot/ (Part 3)

Robot Framework tests: `first.robot` and `web_form.robot` on the web form, and `pizza_scenario.robot` for QP-01, with the keywords of the QuickPizza pages in `resources/quickpizza.resource`. `results/run1` and `results/run2` hold the log, report and screenshots of two full runs.

```bash
cd robot && python3 -m venv .venv && source .venv/bin/activate && pip install -r requirements.txt
robot -d results/run1 -v BROWSER:headlesschrome pizza_scenario.robot
```
