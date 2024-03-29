Bodybuilding.com coding-challenge
================  

#### Instructions for running this project  
  1. Create a new directory on your local file system, named "coding_challenge"  
  2. cd into "coding_challenge" directory  
  3. `git clone https://github.com/bbdevteam/api-code-challenge-ryan-daniels.git`  
  4. `cd api-code-challenge-ryan-daniels`  
  5. `mvn spring-boot:run` 
  6. use a REST client like Postman or your web browser, and issue a get request to http://localhost:8080/, which will return a json structure. 
  7. You can also supply a path parameter, such as http://localhost:8080/30day, which will return a json value. If there is no matching value found, you will get a 404 Resource Not Found 
 

Thank you for taking the Bodybuilding.com Coding Challenge. We want to give you an opportunity to demonstrate your
 abilities. We will plan on using your deliverables as a point of conversation during a possible technical interview.
 Our hope is that this takes a few hours, not days. Have fun!

Using Java or Kotlin, create a REST API that returns website navigation data. The full navigation tree is included in [navigation.json](navigation.json).

Requirements:

* The API should load and parse navigation.json into memory once at application startup. It should **not** be reparsed for every request.
* Create a single API endpoint that takes a ID path parameter (/{ID}), here are some examples:
    1. /30day
    2. /30daymain
    3. /Training
* If the ID parameter is missing assume it is "root"
* Using the ID the API will find the node and return JSON for a tree that has been pruned using the following rules:

    1. The root and it's children should *always* be included
    2. The path from the selected ID (node) to the root should be included
    3. The children of other nodes should *not* be included
* If the node cannot be found the endpoint should return 404

#### Pruning Examples

Given this initial tree:


![Initial Tree](start_tree.jpg)


The result when querying the red node should be:


![Result Tree](result_tree.jpg)

Similarly for this node:

![Initial Tree](start_tree2.jpg)

Resulting in:

![Initial Tree](result_tree2.jpg)


#### Submission
* Please create a branch for your work, when ready issue a pull request.
* Make sure you update this  *README* to explains how to start your API. Feel free to mention any important decisions
about the project architecture / layout that you think we should know.
* Include working unit tests
* Please note, when the interview process has concluded you will be removed from the github team, which will also delete your fork of our private repo.
* Please send an email to the recruiter and/or hiring manager you have been working with to notify them you have completed the challenge.
