package com.nttdata.glue;

import com.nttdata.model.OrderPage;
import com.nttdata.steps.OrderSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;


public class OrderStepDef {

    @Steps
    OrderSteps orderSteps;

    private Response response;


    @Given("la base URL {string}")
    public void setBaseUrl(String baseUrl) {
        orderSteps.setBaseUrl(baseUrl);
    }

    @Given("el path es {string}")
    public void setPath(String path) {
        orderSteps.setPath(path);
    }

    @When("el usuario crea una orden con ID {int}, petId {int}, cantidad {int}, fecha {string}, estado {string} y completado {string}")
    public void crearOrden(int orderId, int petId, int quantity, String shipDate, String status, String complete) {
        orderSteps.createOrder(orderId, petId, quantity, shipDate, status, Boolean.parseBoolean(complete));
    }

    @When("el usuario obtiene la orden con ID {int}")
    public void obtenerOrdenPorId(int orderId) {
        orderSteps.getOrderById(orderId);
    }

    @Then("valido el status code de la respuesta es {int}")
    public void validarStatusCode(int statusCode) {
        orderSteps.validateStatusCode(statusCode);
    }

    @Then("valido que el ID de la orden es {int}, el estado es {string} y completado es {string}")
    public void validarContenidoOrden(int id, String status, String complete) {
        orderSteps.validateOrderData(id, status, Boolean.parseBoolean(complete));
    }


}