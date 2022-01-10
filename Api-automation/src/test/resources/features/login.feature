Feature: Escenario para realizar el login

  Scenario Outline: Login (creation de session)
    Given Se tiene el email: "<email>" and contrasena: "<password>" para el login
    When Invoco al servicio login
    Then Entonces el login me devuelve el mensaje "<msg>"
    Examples:
      | email  | password    | msg         |
      | admin@ | password123 | LOGIN VALID |

