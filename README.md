**Requirements**
* Maven
* Java 1.8

**Assumptions**
* Only one asset per location
* An alert will be notified regardless if lightning hit the cloud/ground (i.e. notify if flashType is 0 or 1)

**How to Run**
* Execute the following commands in a terminal
    1. mvn install
    2. java -jar ./target/lightning-alert-1.0.jar  < Lightning JSON file > < Asset JSON file >
       * Ex: java -jar ./target/lightning-alert-1.0.jar  lightning.json assets.json

**Additional Questions:**
* What is the time complexity for determining if a strike has occurred for a particular asset?
    * O(n)
* If we put this code into production, but found it too slow, or it needed to scale to many more users or more frequent strikes, what are the first things you would think of to speed it up?
    * To handle more frequent strikes, this can be scaled horizontally. Deploy this application to multiple servers and add a load balancer to route handling of lightning strikes to a server
    
