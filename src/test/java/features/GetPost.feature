Feature: Check Get Post requests

  Scenario: Verify 1 author of the post
    Given I perform GET operation for "/posts"
    Then I should see the author name as "Karthik KK"


  Scenario: Verify Collection of the authors in post
    Given I perform GET operation for "/posts"
    Then I should see author names

  Scenario: Verify path parameter of authors in post
    Given I perform GET operation for "/posts"
    Then I should see the path parameter

  Scenario: Verify Post Operation
    Given I perform POST operation for "/posts/{profileNo}/profile" with body
      | name | profile |
      | Sam  | 2       |
    Then I should see the body has name as "Sam"

  @smoke
  Scenario: Verify GET operation with bearer authentication token
    Given I perform authentication operation for "/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Karthik KK"

  @smoke
  Scenario: Verify GET operation with json validation
    Given I perform authentication operation for "/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Karthik KK" with json validation