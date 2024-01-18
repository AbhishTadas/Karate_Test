Feature: sample karate test script
  for help, see: https://github.com/karatelabs/karate/wiki/IDE-Support

  Background:
    * url 'https://jsonplaceholder.typicode.com'
    * def util = Java.type('Excel_Utill.ExcelReader')
    * def req = util.getAllColumnVslues('Sheet2')

  Scenario: get all users and then get the first user by id
    Given path 'users'
    When method get
    Then status 200
    * def first = response[0]

    Given path 'users', first.id
    When method get
    Then status 200

  Scenario: (excel) create a user
    Given url 'https://jsonplaceholder.typicode.com/users'
    And request req
    When method post
    Then status 201

    * def id = response.id
    * print 'created id is: ', id

    Given path id
    # When method get
    # Then status 200
    # And match response contains user


  Scenario Outline: (csv) create a user
    Given url 'https://jsonplaceholder.typicode.com/users'
    And request
    """
      {
        "name": "#(name)",
        "username": "#(username)",
        "email": "#(email)",
        "address": {
          "street": "#(street)",
          "suite": "#(suite)",
          "city": "#(city)",
          "zipcode":"#(zipcode)"
        }
      }
    """
    When method post
    Then status 201
    * def id = response.id
    * print 'created id is: ', id
    Given path id
    # When method get
    # Then status 200
    # And match response contains user
  Examples:
    | read('Test.csv')|

    Scenario: Ui Test
      Given driver 'https://google.com'
      And driver.maximize()
      And def searchbar = waitFor('textarea[name=q]')
      And input('textarea[name=q]','karate dsl')

  Scenario: desktop
    * robot { window: 'Calculator', fork: 'calc', highlight: true }
    * click('Clear')
    * click('One')
    * click('Plus')
    * click('Two')
    * click('Equals')
    * match locate('#CalculatorResults').name == 'Display is 3'
    * screenshot()