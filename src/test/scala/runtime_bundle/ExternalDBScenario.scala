package runtime_bundle

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.DurationInt

class ExternalDBScenario extends Simulation {

  val httpConf = http
    .baseUrl("http://" + System.getenv("GATEWAY_HOST"))

  val appName = "test-app-90"

  val scn = scenario("Basic interaction")

    .exec(http("getAuthentication")
      .post("http://" + System.getenv("SSO_HOST") + ".envalfresco.com/auth/realms/alfresco/protocol/openid-connect/token")
      .formParam("client_id", "activiti")
      .formParam("grant_type", "password")
      .formParam("username", "devopsuser")
      .formParam("password", "password")
      .check(jsonPath("$..access_token").ofType[String].saveAs("token"))
    )

    .exec(http("getProcessDefinitions")
      .get("/" + appName + "/rb/v1/process-definitions")
      .header("Authorization", "Bearer ${token}")
      .check(jsonPath("$..id").ofType[String].saveAs("processDefinitionId"))
    )

    .exec(http("startProcess")
      .post("/" + appName+ "/rb/v1/process-instances")
      .header("Authorization", "Bearer ${token}")
      .header("Content-Type", "application/json")
      .body(StringBody(
        """
           {
               "processDefinitionId": "${processDefinitionId}",
               "businessKey": "MyBusinessKey",
               "variables":{ "firstName": "TestName", "lastName": "TestLast", "age": 25 },
               "payloadType":"StartProcessPayload"
           }
          """))
      .check(jsonPath("$..id").ofType[String].saveAs("processInstanceId"))
    )

    .exec(http("createStandaloneTask")
      .post("/" + appName + "/rb/v1/tasks")
      .header("Authorization", "Bearer ${token}")
      .header("Content-Type", "application/json")
      .body(StringBody(
        """
           {
               "name": "test-standalone",
               "payloadType":"CreateTaskPayload"
           }
          """))
      .check(jsonPath("$..id").ofType[String].saveAs("standaloneTaskId"))
    )

    .exec(http("getProcessTask")
      .get("/" + appName + "/rb/v1/process-instances/${processInstanceId}/tasks")
      .header("Authorization", "Bearer ${token}")
      .check(jsonPath("$..id").ofType[String].saveAs("taskId"))
    )

    .exec(http("claimTask")
      .post("/" + appName + "/rb/v1/tasks/${standaloneTaskId}/claim")
      .queryParam("assignee", "devopsuser")
      .header("Authorization", "Bearer ${token}")
    )

    .exec(http("releaseTask")
      .post("/" + appName + "/rb/v1/tasks/${standaloneTaskId}/release")
      .queryParam("assignee", "devopsuser")
      .header("Authorization", "Bearer ${token}")
    )

    .exec(http("completeTask")
      .post("/" + appName + "/rb/v1/tasks/${taskId}/complete")
      .header("Authorization", "Bearer ${token}")
    ).pause(1)

    .pause(500.milliseconds)

    //    .exec(http("taskCompletedEvent")
    //    .get("/audit/v1/events")
    //    .queryParam("processInstanceId", "${processInstanceId}")
    //    .queryParam("eventType", "TaskCompletedEvent")
    //    .header("Authorization", "Bearer ${token}")
    //    .check(jsonPath("$..id").ofType[String])
    //  )

    //    .exec(http("taskCompletedQuery")
    //    .get("/query/v1/tasks")
    //    .queryParam("id", "${taskId}")
    //    .queryParam("status", "COMPLETED")
    //    .header("Authorization", "Bearer ${token}")
    //    .check(jsonPath("$..id").ofType[String])
    //  )

    .exec(session => {
    println("Variables logging(this is optional of course):")
    println(session("token").as[String])
    println(session("processDefinitionId").as[String])
    println(session("processInstanceId").as[String])
    println(session("taskId").as[String])
    session
  })

  setUp(scn.inject(constantUsersPerSec(10) during 5).protocols(httpConf))

}

