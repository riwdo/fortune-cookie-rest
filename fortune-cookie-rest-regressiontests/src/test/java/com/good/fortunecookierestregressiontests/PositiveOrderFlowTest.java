package com.good.fortunecookierestregressiontests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.good.fortunecookierestregressiontests.dto.FortuneDto;
import com.good.fortunecookierestregressiontests.dto.OrderResponseDto;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PositiveOrderFlowTest extends AbstractRegressionTest {

  @Test
  public void orderFlowOk() throws URISyntaxException, IOException, InterruptedException {
    var client = HttpClient.newHttpClient();

    var fortunesRequest = getHttpRequest("/fortunes");
    HttpResponse<String> response =
        client.send(fortunesRequest, HttpResponse.BodyHandlers.ofString());

    assertEquals(200, response.statusCode());

    List<FortuneDto> fortunes =
        new ArrayList<>(Arrays.asList(objectMapper.readValue(response.body(), FortuneDto[].class)));

    assertEquals(3, fortunes.size());

    // Create order with fortune
    Long fortuneId = fortunes.getFirst().getId();
    var postOrderRequest =
        getPostRequest(
            "/orders",
            """
             {
              "fortuneId": %s,
              "paymentType": "klarna"
             }
            """
                .formatted(fortuneId));
    HttpResponse<String> orderCreationResponse =
        client.send(postOrderRequest, HttpResponse.BodyHandlers.ofString());

    assertEquals(200, orderCreationResponse.statusCode());

    OrderResponseDto orderResponseDto =
        objectMapper.readValue(orderCreationResponse.body(), OrderResponseDto.class);
    assertEquals(OrderResponseDto.Status.RECEIVED, orderResponseDto.getStatus());

    Long orderId = orderResponseDto.getId();

    var postOrderBakeRequest = getPostRequest("/orders/%s/bake".formatted(orderId));
    HttpResponse<String> orderBakeResponse =
        client.send(postOrderBakeRequest, HttpResponse.BodyHandlers.ofString());

    orderResponseDto = objectMapper.readValue(orderBakeResponse.body(), OrderResponseDto.class);
    assertEquals(OrderResponseDto.Status.BAKING, orderResponseDto.getStatus());

    var postOrderDeliverRequest =
        getPostRequest(
            "/orders/%s/deliver".formatted(orderId),
            """
            {
              "street": "Packhusplatsen 2",
              "postalCode": "411 13",
              "city": "Göteborg"
            }
            """);
    HttpResponse<String> orderDeliverResponse =
        client.send(postOrderDeliverRequest, HttpResponse.BodyHandlers.ofString());

    orderResponseDto = objectMapper.readValue(orderDeliverResponse.body(), OrderResponseDto.class);
    assertEquals(OrderResponseDto.Status.DELIVERED, orderResponseDto.getStatus());
  }

  private static HttpRequest getPostRequest(String path) throws URISyntaxException {
    return getPostRequest(path, null);
  }

  private static HttpRequest getPostRequest(String path, String jsonBody)
      throws URISyntaxException {
    return HttpRequest.newBuilder()
        .uri(
            new URI(
                "http://127.0.0.1:%s%s"
                    .formatted(fortuneCookieRestApplication.getMappedPort(8080), path)))
        .POST(
            jsonBody != null
                ? HttpRequest.BodyPublishers.ofString(jsonBody)
                : HttpRequest.BodyPublishers.noBody())
        .header("Content-Type", "application/json")
        .build();
  }

  private static HttpRequest getHttpRequest(String path) throws URISyntaxException {
    return HttpRequest.newBuilder()
        .uri(
            new URI(
                "http://127.0.0.1:%s%s"
                    .formatted(fortuneCookieRestApplication.getMappedPort(8080), path)))
        .GET()
        .build();
  }
}
