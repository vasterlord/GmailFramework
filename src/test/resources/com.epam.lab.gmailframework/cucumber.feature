Feature: Gmail login

  Background:
    Given I go to Gmail login page

  Scenario Outline:
    When <email>  email entered
    And <password> password entered
    Then Compose button should be available
    And Quit

    Examples:
      | email                     | password          |
      | "tuserfirst@gmail.com"    | "tuserfirst12345" |
#      | "balatskiyandr@gmail.com" | "963852741bal"    |
#      | "Tarchanihor@gmail.com"   | "qweqweqwe"       |
#      | "balatskiyandr@gmail.com" | "963852741bal"    |
#      | "Tarchanihor@gmail.com"   | "qweqweqwe"       |

