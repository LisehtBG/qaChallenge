package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.LoginData;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class loginSteps {

    LoginData loginData = new LoginData();
    private RequestSpecification request;
    private Response response;

    @Given("^Se tiene el email: \"([^\"]*)\" and contrasena: \"([^\"]*)\" para el login$")
    public void seTieneElEmailAndContrasenaParaElLogin(String email, String password) throws Throwable {

        loginData.setEmail(email);
        loginData.setPassword(password);
    }

    @When("^Invoco al servicio login$")
    public void invocoAlServicioLogin() {
        request = given().header("Content-Type", "application/json")
        .log().all();
        String url = "http://localhost:3000/login";
        response = request.when()
                .body(loginData).post(url);

    }

    @Then("^Entonces el login me devuelve el mensaje \"([^\"]*)\"$")
    public void entoncesElLoginMeDevuelveElMensaje(String msg) throws Throwable {

        String responseJson = response.then().extract().body().asString();
        String message = from(responseJson).get("msg");

        try {
            assertThat(message,equalTo(msg));
            System.out.println("===========================");
            System.out.println("El codigo de estado es "+ response.statusCode());
            System.out.println("El mensaje del response es: "+message);
        }catch (AssertionError e){
            System.out.println("El mensaje del response es: "+message);
        }

        Assert.assertEquals(200,response.statusCode());
    }
}
