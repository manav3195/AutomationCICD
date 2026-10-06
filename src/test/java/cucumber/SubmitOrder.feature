@tag
Feature: Purchase the orderr from Ecommerce Website

Background:
Given I landed on Ecommerce login Webpage

@Regression
Scenario Outline: Positive test of submitting the order
Given Logged in username <username> and password <password>
When  I Add the product <productName> from CART
And  Checkout <productName> and submit the order
Then Success message "THANKYOU FOR THE ORDER." should come

Examples:

| username | password | productName |
| manavchaudhary3195@gmail.com | Manav@123 | ZARA COAT 3|
| releulexefeu-1678@yopmail.com| Manav@123 | ZARA COAT 3|