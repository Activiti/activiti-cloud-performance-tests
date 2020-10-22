package runtime_bundle

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.DurationInt

class rbReproduce extends Simulation {

  val httpConf = http
    .baseUrl("https://gateway.aps2dev1.envalfresco.com")

  val appName = "/reprod3"
  val token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJEd1FHbExSekl6OV9rTzZWZXB0OTVoVXdFb2ZwdEJYcS1nSWlORFhNaDVZIn0.eyJqdGkiOiIyMTE0MmQ1Ny1lZWI4LTQxY2QtYmQ5Mi00MWFiNmMyY2UzMDciLCJleHAiOjE1NzQyNDI2ODcsIm5iZiI6MCwiaWF0IjoxNTc0MjQyMzg3LCJpc3MiOiJodHRwczovL2lkZW50aXR5LmFwczJkZXYxLmVudmFsZnJlc2NvLmNvbS9hdXRoL3JlYWxtcy9hbGZyZXNjbyIsImF1ZCI6WyJyZWFsbS1tYW5hZ2VtZW50IiwicmVwcm9kMiIsInJlcHJvZDEiLCJhY2NvdW50Il0sInN1YiI6IjRiOTc5M2VhLTg2ZDItNGQzNi1iOGUxLWJlZWUwMTI5MDVkMSIsInR5cCI6IkJlYXJlciIsImF6cCI6InJlcHJvZDMiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiIwNTg5Njc0NS0yN2Y4LTRiYmQtODkxOC02ZGI1YjM2MTg0MmIiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwiQUNUSVZJVElfVVNFUiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsicmVhbG0tbWFuYWdlbWVudCI6eyJyb2xlcyI6WyJ2aWV3LXVzZXJzIiwidmlldy1jbGllbnRzIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LWdyb3VwcyIsInF1ZXJ5LXVzZXJzIl19LCJyZXByb2QzIjp7InJvbGVzIjpbIkFDVElWSVRJX1VTRVIiXX0sInJlcHJvZDIiOnsicm9sZXMiOlsiQUNUSVZJVElfVVNFUiJdfSwicmVwcm9kMSI6eyJyb2xlcyI6WyJBQ1RJVklUSV9VU0VSIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJIUiBVc2VyIiwiZ3JvdXBzIjpbImhyIl0sInByZWZlcnJlZF91c2VybmFtZSI6ImhydXNlciIsImdpdmVuX25hbWUiOiJIUiIsImZhbWlseV9uYW1lIjoiVXNlciIsImVtYWlsIjoiaHJ1c2VyQHRlc3QuY29tIn0.iydCzY4-9IP2MZ7mf8zwZ65jB9AUuLMSdTOzbfqew0t07X_GhzFnfOr0obchGT1_Pr60z4iLeRDNVYijM44tUeXq-GjTMC9kfTBwbfvrRnhjK0GcvPWDLuUJVZztrEPdWKYbCQ2FtWLbh8xfG_oW6x3aMwIkqwkFEv2NVT8QmLTn5wa_7tzIDNTKyIlq2R1nC2FTXbcjjZEO41Q9MHDOf1XK7P0cCUxt3Ia-tfdG2zwuIX6fJ3JJ0ZtXTwcW-8EJzRQsgHiB9gwogpCf2n0XNFU7l7T7lgp7T1fFcZFwXCVZr_sWsDToVlRmuM42DB1Z2XaE16PG-mJ5Ad4RWBU9CQ"

  val scn = scenario("Basic interaction")

//    .exec(http("getAuthentication")
//      .post("https://" + System.getenv("SSO_HOST") + ".envalfresco.com/auth/realms/alfresco/protocol/openid-connect/token")
//      .formParam("client_id", "reprod3")
//      .formParam("grant_type", "password")
//      .formParam("username", "hruser")
//      .formParam("password", "password")
//      .check(jsonPath("$..access_token").ofType[String].saveAs("token"))
//    )

    .exec(http("getProcessDefinitions")
      .get(appName + "/rb/v1/process-definitions")
      .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJEd1FHbExSekl6OV9rTzZWZXB0OTVoVXdFb2ZwdEJYcS1nSWlORFhNaDVZIn0.eyJqdGkiOiJhMzI0NDQ0MS1mYTJlLTRhMDgtYTkzYS0xY2FkMDI1MDdkNjQiLCJleHAiOjE1NzQyNDQ2NjUsIm5iZiI6MCwiaWF0IjoxNTc0MjQ0MzY1LCJpc3MiOiJodHRwczovL2lkZW50aXR5LmFwczJkZXYxLmVudmFsZnJlc2NvLmNvbS9hdXRoL3JlYWxtcy9hbGZyZXNjbyIsImF1ZCI6WyJyZWFsbS1tYW5hZ2VtZW50IiwicmVwcm9kMiIsInJlcHJvZDEiLCJhY2NvdW50Il0sInN1YiI6IjRiOTc5M2VhLTg2ZDItNGQzNi1iOGUxLWJlZWUwMTI5MDVkMSIsInR5cCI6IkJlYXJlciIsImF6cCI6InJlcHJvZDMiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiI2NDhhZDUxOS05ODc4LTRkNzMtYWQyYS0zNzdjNTc4ZTMyM2IiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwiQUNUSVZJVElfVVNFUiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsicmVhbG0tbWFuYWdlbWVudCI6eyJyb2xlcyI6WyJ2aWV3LXVzZXJzIiwidmlldy1jbGllbnRzIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LWdyb3VwcyIsInF1ZXJ5LXVzZXJzIl19LCJyZXByb2QzIjp7InJvbGVzIjpbIkFDVElWSVRJX1VTRVIiXX0sInJlcHJvZDIiOnsicm9sZXMiOlsiQUNUSVZJVElfVVNFUiJdfSwicmVwcm9kMSI6eyJyb2xlcyI6WyJBQ1RJVklUSV9VU0VSIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJIUiBVc2VyIiwiZ3JvdXBzIjpbImhyIl0sInByZWZlcnJlZF91c2VybmFtZSI6ImhydXNlciIsImdpdmVuX25hbWUiOiJIUiIsImZhbWlseV9uYW1lIjoiVXNlciIsImVtYWlsIjoiaHJ1c2VyQHRlc3QuY29tIn0.IheN8qQjkDWko4N1DvjatrknJwmrewRJQOlCOtlaQnJRVAMj6MEHA4tbQNWpCLrP4bGqL6gDH__m3zk0sHhHg9YyggvSF_QY0pkmbyJHJn8hTjSiGO1Y4YHygfdTuVhssW-nKzzw6VU7V0sbKJ5ZVeVewicLjnfniF21dVP0m-FRHGlXw9O8jU9FQKmW_u1tiHmJn9K60OawuLeDREmAZBHtWXC8Webt5a71-NKWG9yuLD4l18tpOdbK6Od6V8va8S-ieydifwMo71V3nu3Jn8pZ77s5XWXtATBM13dixrRl3v0EqYFmoIjy9nsI-pNeoIzcH_RwTwlWlsak5EfGzQ")
      .check(jsonPath("$..id").ofType[String].saveAs("processDefinitionId"))
    )

    .exec(http("startProcess")
      .post(appName + "/rb/v1/process-instances")
      .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJEd1FHbExSekl6OV9rTzZWZXB0OTVoVXdFb2ZwdEJYcS1nSWlORFhNaDVZIn0.eyJqdGkiOiJhMzI0NDQ0MS1mYTJlLTRhMDgtYTkzYS0xY2FkMDI1MDdkNjQiLCJleHAiOjE1NzQyNDQ2NjUsIm5iZiI6MCwiaWF0IjoxNTc0MjQ0MzY1LCJpc3MiOiJodHRwczovL2lkZW50aXR5LmFwczJkZXYxLmVudmFsZnJlc2NvLmNvbS9hdXRoL3JlYWxtcy9hbGZyZXNjbyIsImF1ZCI6WyJyZWFsbS1tYW5hZ2VtZW50IiwicmVwcm9kMiIsInJlcHJvZDEiLCJhY2NvdW50Il0sInN1YiI6IjRiOTc5M2VhLTg2ZDItNGQzNi1iOGUxLWJlZWUwMTI5MDVkMSIsInR5cCI6IkJlYXJlciIsImF6cCI6InJlcHJvZDMiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiI2NDhhZDUxOS05ODc4LTRkNzMtYWQyYS0zNzdjNTc4ZTMyM2IiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwiQUNUSVZJVElfVVNFUiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsicmVhbG0tbWFuYWdlbWVudCI6eyJyb2xlcyI6WyJ2aWV3LXVzZXJzIiwidmlldy1jbGllbnRzIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LWdyb3VwcyIsInF1ZXJ5LXVzZXJzIl19LCJyZXByb2QzIjp7InJvbGVzIjpbIkFDVElWSVRJX1VTRVIiXX0sInJlcHJvZDIiOnsicm9sZXMiOlsiQUNUSVZJVElfVVNFUiJdfSwicmVwcm9kMSI6eyJyb2xlcyI6WyJBQ1RJVklUSV9VU0VSIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJIUiBVc2VyIiwiZ3JvdXBzIjpbImhyIl0sInByZWZlcnJlZF91c2VybmFtZSI6ImhydXNlciIsImdpdmVuX25hbWUiOiJIUiIsImZhbWlseV9uYW1lIjoiVXNlciIsImVtYWlsIjoiaHJ1c2VyQHRlc3QuY29tIn0.IheN8qQjkDWko4N1DvjatrknJwmrewRJQOlCOtlaQnJRVAMj6MEHA4tbQNWpCLrP4bGqL6gDH__m3zk0sHhHg9YyggvSF_QY0pkmbyJHJn8hTjSiGO1Y4YHygfdTuVhssW-nKzzw6VU7V0sbKJ5ZVeVewicLjnfniF21dVP0m-FRHGlXw9O8jU9FQKmW_u1tiHmJn9K60OawuLeDREmAZBHtWXC8Webt5a71-NKWG9yuLD4l18tpOdbK6Od6V8va8S-ieydifwMo71V3nu3Jn8pZ77s5XWXtATBM13dixrRl3v0EqYFmoIjy9nsI-pNeoIzcH_RwTwlWlsak5EfGzQ")
      .header("Content-Type", "application/json")
      .body(StringBody(
        """
           {
               "processDefinitionId": "a5ab9621-0adc-11ea-a4f8-7ee845dfa810",
               "businessKey": "MyBusinessKey",
               "payloadType":"StartProcessPayload"
           }
          """))
      .check(jsonPath("$..id").ofType[String].saveAs("processInstanceId"))
    )

//    .exec(http("createStandaloneTask")
//      .post(appName + "/rb/v1/tasks")
//      .header("Authorization", "Bearer ${token}")
//      .header("Content-Type", "application/json")
//      .body(StringBody(
//        """
//           {
//               "name": "test-standalone",
//               "payloadType":"CreateTaskPayload"
//           }
//          """))
//      .check(jsonPath("$..id").ofType[String].saveAs("standaloneTaskId"))
//    )

    .exec(http("getProcessTask")
      .get(appName + "/rb/v1/process-instances/${processInstanceId}/tasks")
      .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJEd1FHbExSekl6OV9rTzZWZXB0OTVoVXdFb2ZwdEJYcS1nSWlORFhNaDVZIn0.eyJqdGkiOiJhMzI0NDQ0MS1mYTJlLTRhMDgtYTkzYS0xY2FkMDI1MDdkNjQiLCJleHAiOjE1NzQyNDQ2NjUsIm5iZiI6MCwiaWF0IjoxNTc0MjQ0MzY1LCJpc3MiOiJodHRwczovL2lkZW50aXR5LmFwczJkZXYxLmVudmFsZnJlc2NvLmNvbS9hdXRoL3JlYWxtcy9hbGZyZXNjbyIsImF1ZCI6WyJyZWFsbS1tYW5hZ2VtZW50IiwicmVwcm9kMiIsInJlcHJvZDEiLCJhY2NvdW50Il0sInN1YiI6IjRiOTc5M2VhLTg2ZDItNGQzNi1iOGUxLWJlZWUwMTI5MDVkMSIsInR5cCI6IkJlYXJlciIsImF6cCI6InJlcHJvZDMiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiI2NDhhZDUxOS05ODc4LTRkNzMtYWQyYS0zNzdjNTc4ZTMyM2IiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwiQUNUSVZJVElfVVNFUiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsicmVhbG0tbWFuYWdlbWVudCI6eyJyb2xlcyI6WyJ2aWV3LXVzZXJzIiwidmlldy1jbGllbnRzIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LWdyb3VwcyIsInF1ZXJ5LXVzZXJzIl19LCJyZXByb2QzIjp7InJvbGVzIjpbIkFDVElWSVRJX1VTRVIiXX0sInJlcHJvZDIiOnsicm9sZXMiOlsiQUNUSVZJVElfVVNFUiJdfSwicmVwcm9kMSI6eyJyb2xlcyI6WyJBQ1RJVklUSV9VU0VSIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJIUiBVc2VyIiwiZ3JvdXBzIjpbImhyIl0sInByZWZlcnJlZF91c2VybmFtZSI6ImhydXNlciIsImdpdmVuX25hbWUiOiJIUiIsImZhbWlseV9uYW1lIjoiVXNlciIsImVtYWlsIjoiaHJ1c2VyQHRlc3QuY29tIn0.IheN8qQjkDWko4N1DvjatrknJwmrewRJQOlCOtlaQnJRVAMj6MEHA4tbQNWpCLrP4bGqL6gDH__m3zk0sHhHg9YyggvSF_QY0pkmbyJHJn8hTjSiGO1Y4YHygfdTuVhssW-nKzzw6VU7V0sbKJ5ZVeVewicLjnfniF21dVP0m-FRHGlXw9O8jU9FQKmW_u1tiHmJn9K60OawuLeDREmAZBHtWXC8Webt5a71-NKWG9yuLD4l18tpOdbK6Od6V8va8S-ieydifwMo71V3nu3Jn8pZ77s5XWXtATBM13dixrRl3v0EqYFmoIjy9nsI-pNeoIzcH_RwTwlWlsak5EfGzQ")
      .check(jsonPath("$..id").ofType[String].saveAs("taskId"))
    )


    .exec(http("claimTask")
      .post(appName + "/rb/v1/tasks/${taskId}/claim")
      .queryParam("assignee", "hruser")
      .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJEd1FHbExSekl6OV9rTzZWZXB0OTVoVXdFb2ZwdEJYcS1nSWlORFhNaDVZIn0.eyJqdGkiOiJhMzI0NDQ0MS1mYTJlLTRhMDgtYTkzYS0xY2FkMDI1MDdkNjQiLCJleHAiOjE1NzQyNDQ2NjUsIm5iZiI6MCwiaWF0IjoxNTc0MjQ0MzY1LCJpc3MiOiJodHRwczovL2lkZW50aXR5LmFwczJkZXYxLmVudmFsZnJlc2NvLmNvbS9hdXRoL3JlYWxtcy9hbGZyZXNjbyIsImF1ZCI6WyJyZWFsbS1tYW5hZ2VtZW50IiwicmVwcm9kMiIsInJlcHJvZDEiLCJhY2NvdW50Il0sInN1YiI6IjRiOTc5M2VhLTg2ZDItNGQzNi1iOGUxLWJlZWUwMTI5MDVkMSIsInR5cCI6IkJlYXJlciIsImF6cCI6InJlcHJvZDMiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiI2NDhhZDUxOS05ODc4LTRkNzMtYWQyYS0zNzdjNTc4ZTMyM2IiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwiQUNUSVZJVElfVVNFUiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsicmVhbG0tbWFuYWdlbWVudCI6eyJyb2xlcyI6WyJ2aWV3LXVzZXJzIiwidmlldy1jbGllbnRzIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LWdyb3VwcyIsInF1ZXJ5LXVzZXJzIl19LCJyZXByb2QzIjp7InJvbGVzIjpbIkFDVElWSVRJX1VTRVIiXX0sInJlcHJvZDIiOnsicm9sZXMiOlsiQUNUSVZJVElfVVNFUiJdfSwicmVwcm9kMSI6eyJyb2xlcyI6WyJBQ1RJVklUSV9VU0VSIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJIUiBVc2VyIiwiZ3JvdXBzIjpbImhyIl0sInByZWZlcnJlZF91c2VybmFtZSI6ImhydXNlciIsImdpdmVuX25hbWUiOiJIUiIsImZhbWlseV9uYW1lIjoiVXNlciIsImVtYWlsIjoiaHJ1c2VyQHRlc3QuY29tIn0.IheN8qQjkDWko4N1DvjatrknJwmrewRJQOlCOtlaQnJRVAMj6MEHA4tbQNWpCLrP4bGqL6gDH__m3zk0sHhHg9YyggvSF_QY0pkmbyJHJn8hTjSiGO1Y4YHygfdTuVhssW-nKzzw6VU7V0sbKJ5ZVeVewicLjnfniF21dVP0m-FRHGlXw9O8jU9FQKmW_u1tiHmJn9K60OawuLeDREmAZBHtWXC8Webt5a71-NKWG9yuLD4l18tpOdbK6Od6V8va8S-ieydifwMo71V3nu3Jn8pZ77s5XWXtATBM13dixrRl3v0EqYFmoIjy9nsI-pNeoIzcH_RwTwlWlsak5EfGzQ")
    )

//    .exec(http("releaseTask")
//      .post(appName + "/rb/v1/tasks/${taskId}/release")
//      .queryParam("assignee", "devopsuser")
//      .header("Authorization", "Bearer ${token}")
//    )

//    .exec(http("completeTask")
//      .post(appName + "/rb/v1/tasks/${taskId}/complete")
//      .header("Authorization", "Bearer ${token}")
//    ).pause(1)

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

  setUp(scn.inject(constantUsersPerSec(300) during 5).protocols(httpConf))

}
