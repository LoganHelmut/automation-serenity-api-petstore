#language:en
@FEATURE_pet @regresion
Feature: Create a pet

  @test_0001
  Scenario: Create 10 pets 5 with status available 4 with pending and 1 with sold
    Given  I have created 5 pets with status "available"
    And  I have created 4 pets with status "pending"
    And  I have created 1 pets with status "sold"
    When I request the datails of a pet with status sold
    Then  I should see the details of the pet