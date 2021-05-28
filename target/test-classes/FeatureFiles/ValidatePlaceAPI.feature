@MapsAPI

Feature: Validate place API's

  @TestAPI
  Scenario: Validate add place api
    Given Add place PayLoad
    When User calls "AddPlaceAPI" Place API with "post" http request
    Then the api call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

  @TestAPI
  Scenario: verify delete a work place in maps
    Given Delete place api PayLoad
    When User calls "DeletePlaceAPI" Place API with "Post" http request
    Then the api call got success with status code 200
    And "status" in response body is "OK"

  @TestAPI1
  Scenario Outline: Validate add place api
    Given Add place PayLoad with "<Name>" "<Language>" "<Address>"
    When User calls "AddPlaceAPI" Place API with "Post" http request
    Then the api call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
#    And verify Place_Id created maps to "<Name>" using call "GetPlaceAPI"
    When the user get a place using place_id
    When User calls "GetPlaceAPI" Place API with "GET" http request
    Then verify "<Name>" argument with get call "GetPlaceAPI" response
    Examples:
      | Name      | Language | Address                  |
      | Nagasesha | English  | Atmakur Anantapur        |
      | Shireesha | Hindi    | Kanagana palli Anantapur |

  @DeleteAPI
    Scenario: verify delete a work place in maps
      Given Delete place api PayLoad
      When User calls "DeletePlaceAPI" Place API with "Post" http request
      Then the api call got success with status code 200
      And "status" in response body is "OK"


  @TestAPIDelete
  Scenario Outline: Validate add place api
    Given Add place PayLoad with "<Name>" "<Language>" "<Address>"
    When User calls "AddPlaceAPI" Place API with "Post" http request
    Then the api call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
#    And verify Place_Id created maps to "<Name>" using call "GetPlaceAPI"
    Given Delete place api PayLoad
    When User calls "DeletePlaceAPI" Place API with "Post" http request
    Then the api call got success with status code 200
    And "status" in response body is "OK"
    Examples:
      | Name      | Language | Address                  |
      | Nagasesha | English  | Atmakur Anantapur        |
      | Shireesha | Hindi    | Kanagana palli Anantapur |

    @GetPlaceAPI
    Scenario: verify get place api call
      Given the user get a place using place_id
      When User calls "GetPlaceAPI" Place API with "GET" http request
      Then verify "Frontline house" argument with get call "GetPlaceAPI" response