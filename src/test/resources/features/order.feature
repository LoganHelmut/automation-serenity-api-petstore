#language:en
@FEATURE_order @regresion
Feature: Order a pet

  @test_0002
  Scenario: Get all the pets available and store five in a data structure to create an order for each one
    Given  I get all the pets with status "available"
    And  I stored 5 of them in a data structure
    When  I create an order for the 5 pets
    Then I should see 5 orders created
