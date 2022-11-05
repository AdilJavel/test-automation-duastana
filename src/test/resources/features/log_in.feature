@testing
Feature: Testing Action


  Scenario: Log in with the ID
    Given User is on the login page - www.du.astanait.edu.kz/login
    When User goes to outlook
    Given User enter the inputs
      | 201217@astanait.edu.kz          | Qwerty123 |
    Then User click log in by Outlook

    When User on main page
    Then User can see the profile page


    Then User can see Curriculum


    When User on main page
    Then User can see Teachers Tab
    Then User can see Transcript Tab
    Then User can see Disease Tab
    Then User can see Certificate Tab

    When User on main page
    Then User can change the color

    When User on main page
    Then User can log out with tab

    When User on login page
    Then Log in by cache

    Then User can log out by button
