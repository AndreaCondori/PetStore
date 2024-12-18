@Order
Feature: Crear y consultar órdenes usando la API Swagger PetStore

  Background: Configurar la base URL
    Given la base URL "https://petstore.swagger.io/v2"

  Scenario Outline: Crear una orden en PetStore
    Given el path es "/store/order"
    When el usuario crea una orden con ID <orderId>, petId <petId>, cantidad <quantity>, fecha "<shipDate>", estado "<status>" y completado "<complete>"
    Then valido el status code de la respuesta es 200
    And valido que el ID de la orden es <orderId>, el estado es "<status>" y completado es "<complete>"


    Examples:
      | orderId | petId | quantity | shipDate         | status   | complete |  |
      | 7       | 101   | 2        | 2024-12-16T12:00 | Pending  | true     |  |
      | 2       | 102   | 5        | 2024-12-17T14:00 | approved | false    |  |

  Scenario Outline: Consultar una orden existente
    Given el path es "/store/order/{orderId}"
    When el usuario obtiene la orden con ID <orderId>
    Then valido el status code de la respuesta es 200
    And valido que el ID de la orden es <orderId>, el estado es "<status>" y completado es "<complete>"

    Examples:
      | orderId | status   | complete |  |
      | 7       | Pending  | true     |  |
      | 2       | approved | false    |  |

