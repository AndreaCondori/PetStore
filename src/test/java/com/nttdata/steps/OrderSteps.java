package com.nttdata.steps;

import com.nttdata.model.OrderPage;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static io.restassured.RestAssured.given;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;


public class OrderSteps {

    private String baseUrl;
    private String path;
    private Response response;
    private OrderPage order;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void createOrder(int orderId, int petId, int quantity, String shipDate, String status, boolean complete) {
        order = new OrderPage();
        order.setId(orderId);
        order.setPetId(petId);
        order.setQuantity(quantity);
        order.setShipDate(shipDate);
        order.setStatus(status);
        order.setComplete(complete);

        response = SerenityRest.given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .body(order)
                .when()
                .post(path)
                .then()
                .log().all()
                .extract().response();
        response.then().statusCode(200);
    }

    public void getOrderById(int orderId) {
        response = given()
                .baseUri(baseUrl)
                .pathParam("orderId", orderId)
                .when()
                .get(path)

                ;

        response.then().statusCode(200);

    }

    public void validateStatusCode(int statusCode) {
        try {
            restAssuredThat(response -> response.statusCode(statusCode));
        } catch (AssertionError e) {
            System.err.println("Error: El código de estado esperado es " + statusCode + " pero se recibió " + response.statusCode());
            throw e;
        }
    }

    public void validateOrderData(int expectedId, String expectedStatus, boolean expectedComplete) {
        assertEquals("El id no es el esperado. ID esperado: " + expectedId + ", ID obtenido: " + response.jsonPath().getInt("id"), expectedId, response.jsonPath().getInt("id"));
        assertEquals("El estado no es el esperado. Estado esperado: " + expectedStatus + ", Estado obtenido: " + response.jsonPath().getString("status"), expectedStatus, response.jsonPath().getString("status"));
        assertEquals("El valor de 'complete' no es el esperado. Esperado: " + expectedComplete + ", Obtenido: " + response.jsonPath().getBoolean("complete"), expectedComplete, response.jsonPath().getBoolean("complete"));
    }



}