*** Settings ***
Library           SeleniumLibrary
Test Teardown     Close Browser

*** Variables ***
${URL}        https://www.selenium.dev/selenium/web/web-form.html
${BROWSER}    chrome

*** Test Cases ***
Every Kind Of Field
    [Documentation]    Fill in every kind of field and submit.
    Open Browser    ${URL}    ${BROWSER}
    ...    service=executable_path=r'%{CHROMEDRIVER}'
    Set Window Size    1280    1600
    Input Text    id:my-text-id    Robot
    Textfield Value Should Be    id:my-text-id    Robot
