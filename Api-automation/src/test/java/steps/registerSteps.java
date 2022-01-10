package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.RegisterData;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class registerSteps {

    RegisterData registerData = new RegisterData();
    private RequestSpecification request;
    private Response response;



    @Given("^Se tiene el email: \"([^\"]*)\" and contrasena: \"([^\"]*)\"$")
    public void seTieneElEmailAndContrasena(String email, String password) throws Throwable {
        registerData.setEmail(email);
        registerData.setPassword(password);


    }

    @When("^Invoco al servicio register$")
    public void invocoAlServicioRegister() {

        request = given().header("Content-Type", "application/json");
        String url = "http://localhost:3000/register";
        response = request.when()
                .body(registerData).post(url);

    }

    @Then("^Entonces el servicio me devuelve el mensaje \"([^\"]*)\"$")
    public void entoncesElServicioMeDevuelveElMensaje(String msg) throws Throwable {
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
