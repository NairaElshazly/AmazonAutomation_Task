Selenium-Java Automated Scenario for Amazon Egypt

**Overview**
This project automates a scenario on the Amazon Egypt website using Selenium WebDriver with Java. The scenario includes navigating through the website, applying filters, sorting products, adding items to the cart, adding shipping address, and selecting payment method.

**Prerequisites**
JDK (Java Development Kit)
Maven
WebDriver compatible browser (e.g., Chrome)


**Setup Instructions**
Clone or download the project repository.
Ensure JDK is installed and configured properly.
Ensure Maven is installed and configured properly.
Download WebDriver executable for your preferred browser (e.g., ChromeDriver for Chrome).
Update WebDriver executable path in the project configuration.
Run mvn clean install to download dependencies and build the project.

**Scenario Steps**
Open the Amazon Egypt website and login.
Navigate to the "All" menu from the left side.
Click on "Video Games" and choose "All Video Games".
Apply filters for "Free Shipping" and "Condition: New" from the left side filter menu.
Open the sort menu on the right side and sort by price: high to low.
Add all products below 15k EGP to the cart. Move to the next page if no products are below 15k EGP.
Ensure all products are added to the cart.
Add address and choose cash as a payment method.
Verify that the total amount of all items is correct with the shipping fees if exists.

**Running the Tests**
Run the test suite using the IDE or command line (mvn test).
Ensure the browser window is visible during test execution.
Check the test results and logs for any failures or errors.