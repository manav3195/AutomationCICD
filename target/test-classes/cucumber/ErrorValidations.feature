@tag
Feature: Error Validations


@tag2
Scenario Outline: Error Validations of Login Page

Given I landed on Ecommerce login Webpage
When  Logged in username <username> and password <password>
Then Message "Incorrect email or password." should come

Examples:

| username | password | productName |
| abc@yopmail.com | john@123 | ZARA COAT 3|
