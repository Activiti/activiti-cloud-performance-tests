package simulation

import java.util.concurrent.TimeUnit

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://" + System.getenv("GATEWAY_HOST"))

  val scn = scenario("Basic interaction")

    .exec(http("getAuthentication")
      .post("http://" + System.getenv("AUTH_HOST") + "/auth/realms/springboot/protocol/openid-connect/token")
      .formParam("client_id", "activiti")
      .formParam("grant_type", "password")
      .formParam("username", "hruser")
      .formParam("password", "password")
      .check(jsonPath("$..access_token").ofType[String].saveAs("token"))
    )

    .exec(http("getProcessDefinitions")
      .get("/rb-my-app/v1/process-definitions")
      .header("Authorization", "Bearer ${token}")
      .check(jsonPath("$..id").ofType[String].saveAs("processDefinitionId"))
    )

    .exec(http("startProcess")
      .post("/rb-my-app/v1/process-instances")
      .header("Authorization", "Bearer ${token}")
      .header("Content-Type", "application/json")
      .body(StringBody(
        """
           {
               "processDefinitionId": "${processDefinitionId}",
               "commandType":"StartProcessInstanceCmd"
           }
          """))
      .check(jsonPath("$..id").ofType[String].saveAs("processInstanceId"))
    )

    .exec(http("getProcessTask")
      .get("/rb-my-app/v1/process-instances/${processInstanceId}/tasks")
      .header("Authorization", "Bearer ${token}")
      .check(jsonPath("$..id").ofType[String].saveAs("taskId"))
    )


    .exec(http("claimTask")
      .post("/rb-my-app/v1/tasks/${taskId}/claim")
      .queryParam("assignee", "hruser")
      .header("Authorization", "Bearer ${token}")
    )

    .exec(http("completeTask")
      .post("/rb-my-app/v1/tasks/${taskId}/complete")
      .header("Authorization", "Bearer ${token}")
    ).pause(1)

//    .pause("500" ,TimeUnit.MILLISECONDS)

    .exec(http("taskCompletedEvent")
      .get("/audit/v1/events")
      .queryParam("processInstanceId", "${processInstanceId}")
      .queryParam("eventType", "TaskCompletedEvent")
      .header("Authorization", "Bearer ${token}")
      .check(jsonPath("$..id").ofType[String])
    )

    .exec(http("taskCompletedQuery")
      .get("/query/v1/tasks")
      .queryParam("id", "${taskId}")
      .queryParam("status", "COMPLETED")
      .header("Authorization", "Bearer ${token}")
      .check(jsonPath("$..id").ofType[String])
    )

    .exec(session => {
      println("Variables logging(this is optional of course):")
      println(session("token").as[String])
      println(session("processDefinitionId").as[String])
      println(session("processInstanceId").as[String])
      println(session("taskId").as[String])
      session
    })

  setUp(scn.inject(constantUsersPerSec(5) during 60).protocols(httpConf))
}