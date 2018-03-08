```
export GATEWAY_HOST=gateway.example.com
export AUTH_HOST=auth.example.com
mvn gatling:test -Dgatling.simulationClass=simulation.BasicSimulation
```

You may also decide to use SBT instead of Maven.
However if you are planning to create your own project with Maven and using an IDE such as Scala IDE or Intellij, 
it is good to follow the link below:
https://gatling.io/docs/2.3/extensions/maven_archetype/


This is an example and so the basic simulation runs for just a minute:
  ```scala
setUp(scn.inject(constantUsersPerSec(5) during 60).protocols(httpConf))
```

Note also the presence of pause() in the code, 
that can be adjusted or removed or added to other places based on your expectations.

After the test execution the report will be available under ```target/gatling/basicsimulation-{id}```
