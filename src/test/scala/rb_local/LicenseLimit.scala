package runtime_bundle

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.DurationInt

class LicenseLimit extends Simulation {

  val httpConf = http
    .baseUrl("http://localhost:8080")

  val appName = "test-app-89"

  val scn = scenario("Basic interaction")

    .exec(http("getAuthentication")
      .post("http://" + System.getenv("SSO_HOST") + ".envalfresco.com/auth/realms/alfresco/protocol/openid-connect/token")
      .formParam("client_id", "activiti")
      .formParam("grant_type", "password")
      .formParam("username", "devopsuser")
      .formParam("password", "password")
      .check(jsonPath("$..access_token").ofType[String].saveAs("token"))
    )

    .exec(http("startProcess")
      .post("/v1/process-instances")
      .header("Authorization", "Bearer ${token}")
      .header("Content-Type", "application/json")
      .body(StringBody(
        """
           {
               "processDefinitionId": "test:1:d94952c2-674b-11e9-bfb1-3afe656e08ae",
               "businessKey": "MyBusinessKey",
               "variables":{ "firstName": "TestName", "lastName": "TestLast", "age": 25 },
               "payloadType":"StartProcessPayload"
           }
          """))
      .check(jsonPath("$..id").ofType[String].saveAs("processInstanceId"))
    )


    .pause(500.milliseconds)

    .exec(session => {
    println("Variables logging(this is optional of course):")
    println(session("token").as[String])
    println(session("processDefinitionId").as[String])
    println(session("processInstanceId").as[String])
    println(session("taskId").as[String])
    session
  })

  setUp(scn.inject(constantUsersPerSec(50) during 10).protocols(httpConf))

}
