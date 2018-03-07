export GATEWAY_HOST=gateway.example.com
export AUTH_HOST=auth.example.com
mvn gatling:test -Dgatling.simulationClass=simulation.BasicSimulation

This is an example, you may also decide to use SBT instead of Maven.
However if you are planning to create your own project with Maven and using an IDE such as Scala IDE or Intellij, 
it is good to follow the link below:
https://gatling.io/docs/2.3/extensions/maven_archetype/

