Feature: Escenario para realizar el registro

  Scenario Outline: Login (creation de session)
    Given Se tiene el email: "<email>" and contrasena: "<password>"
    When Invoco al servicio register
    Then Entonces el servicio me devuelve el mensaje "<msg>"
    Examples:
      | email  | password    | msg   |
      | admin@ | password123 | SAVED |