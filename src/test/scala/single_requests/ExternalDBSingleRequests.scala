package single_requests

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ExternalDBSingleRequests extends Simulation {

  val httpConf = http
    .baseUrl("http://" + System.getenv("GATEWAY_HOST"))

  val appName = "test-app-90"
  val token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJzcG5OS0FZX2pub2FGZEJ1QjdTRDl3UmZfMFIwQjZ1UFdkUG9WaVRBSU1zIn0.eyJqdGkiOiJmMDhhMGQyZS02NGQwLTQwMzktOWYwMi1lNDhkODRmYjUyMmMiLCJleHAiOjE1NTUwNzk0MTUsIm5iZiI6MCwiaWF0IjoxNTU1MDc5MTE1LCJpc3MiOiJodHRwOi8vYXBzMnFhLmVudmFsZnJlc2NvLmNvbS9hdXRoL3JlYWxtcy9hbGZyZXNjbyIsImF1ZCI6WyJyZWFsbS1tYW5hZ2VtZW50IiwidGVzdC1yb2QtOTAiLCJ0ZXN0LXJvZC04OSIsInRlc3Qtcm9kLTEyIiwiYWNjb3VudCIsInRlc3QtYXBwLWJlY2U0MDJmLWYzYjUtNDQ2MS1hMDg2LTEwYmE3YmIwYTE5NiJdLCJzdWIiOiI0YTAzYTNiMy1iZTIyLTQ5NjQtYTY4NS05OTBlMzg1ZTM5MWUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhY3Rpdml0aSIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6ImRlNDAzYTdkLWExMTYtNDIyOS1hZjU0LTc0NGVkZDhmMGI4NCIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiQVBTX0RFVk9QUyIsIkFDVElWSVRJX01PREVMRVIiLCJBUFNfVVNFUiIsIm9mZmxpbmVfYWNjZXNzIiwiQUNUSVZJVElfVVNFUiIsIkFDVElWSVRJX0FETUlOIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJyZWFsbS1tYW5hZ2VtZW50Ijp7InJvbGVzIjpbInZpZXctdXNlcnMiLCJxdWVyeS1ncm91cHMiLCJxdWVyeS11c2VycyJdfSwiYWN0aXZpdGkiOnsicm9sZXMiOlsiQUNUSVZJVElfVVNFUiJdfSwidGVzdC1yb2QtOTAiOnsicm9sZXMiOlsiQUNUSVZJVElfQURNSU4iLCJBQ1RJVklUSV9VU0VSIl19LCJ0ZXN0LXJvZC04OSI6eyJyb2xlcyI6WyJBQ1RJVklUSV9VU0VSIiwiQUNUSVZJVElfQURNSU4iXX0sInRlc3Qtcm9kLTEyIjp7InJvbGVzIjpbIkFDVElWSVRJX1VTRVIiLCJBQ1RJVklUSV9BRE1JTiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19LCJ0ZXN0LWFwcC1iZWNlNDAyZi1mM2I1LTQ0NjEtYTA4Ni0xMGJhN2JiMGExOTYiOnsicm9sZXMiOlsiQUNUSVZJVElfVVNFUiJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6IkRldk9wcyBVc2VyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiZGV2b3BzdXNlciIsImdpdmVuX25hbWUiOiJEZXZPcHMiLCJmYW1pbHlfbmFtZSI6IlVzZXIiLCJlbWFpbCI6ImRldm9wc3VzZXJAdGVzdC5jb20ifQ.hvN0A3L8odjTbRp-VX_FyUaymSzQsauh_CLKA8DJdddaJSE3IN3zkwMeNLs3BqSrfmEg8U19U83LqQAm_-RRAfKUAnS7SCcuQ4SOUp4cECVCcJoKqOQ7YGpXfJu9_Anfe33tIhmIz_F2OEmGvfhqQaWxB4cRxVjXw9ZtEtB6m4h7_84mNuxPvTR1xBSGYQ4PLxe-nZok6oLin7lehs0NVEkVroGs6DK5-LzmEGuH7qxrOU8bZwrq0rPG0cGjhSmZ9yMEKC3aeXzk-eBmtLvysyZxrTQ0AVYtc18Ws3IgCnM12OPqsEz_04R9OPj3k5S39i8THWE4Yo0HUYZd_HHRYg"

  val scn1 = scenario("Basic interaction")

    .exec(http("getAuthentication")
      .post("http://" + System.getenv("SSO_HOST") + ".envalfresco.com/auth/realms/alfresco/protocol/openid-connect/token")
      .formParam("client_id", "activiti")
      .formParam("grant_type", "password")
      .formParam("username", "devopsuser")
      .formParam("password", "password")
      .check(jsonPath("$..access_token").ofType[String].saveAs("token"))
    )

  val scn2 = scenario("Get process definitions")

    .exec(http("getProcessDefinitions")
      .get("/" + appName + "/rb/v1/process-definitions")
      .header("Authorization", "Bearer " + token)
      .check(jsonPath("$..id").ofType[String].saveAs("processDefinitionId"))
    )

    val scn3 = scenario("Start process")

      .exec(http("startProcess")
        .post("/" + appName+ "/rb/v1/process-instances")
        .header("Authorization", "Bearer " + token)
        .header("Content-Type", "application/json")
        .body(StringBody(
          """
           {
               "processDefinitionId": "test:1:c3b25328-55eb-11e9-9b7d-0a5864600978",
               "businessKey": "MyBusinessKey",
               "variables":{ "firstName": "TestName", "lastName": "TestLast", "age": 25 },
               "payloadType":"StartProcessPayload"
           }
          """))
        .check(jsonPath("$..id").ofType[String].saveAs("processInstanceId"))
      )

  val scn4 = scenario("Create standalone task")

    .exec(http("createStandaloneTask")
      .post("/" + appName + "/rb/v1/tasks")
      .header("Authorization", "Bearer " + token)
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

    .exec(session => {
      println("Variables logging(this is optional of course):")
      println(session("token").as[String])
      println(session("processDefinitionId").as[String])
      println(session("processInstanceId").as[String])
      println(session("taskId").as[String])
      session
    })

  setUp(scn1.inject(constantUsersPerSec(50) during 5).protocols(httpConf),
        scn2.inject(constantUsersPerSec(50) during 5).protocols(httpConf),
        scn3.inject(constantUsersPerSec(50) during 5).protocols(httpConf),
        scn4.inject(constantUsersPerSec(50) during 5).protocols(httpConf))
}
