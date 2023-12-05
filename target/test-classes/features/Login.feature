Feature: Application Login

Scenario: Login with valid credentials
Given Open any Browser
And Navigate to Login page
When User enters username as "novo@gmail.com" and password as "novo123" into the fields
And User cicks on Login button
Then verify user is able to successfully login