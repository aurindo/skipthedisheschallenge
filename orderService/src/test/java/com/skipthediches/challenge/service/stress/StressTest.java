package com.skipthediches.challenge.service.stress;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StressTest {

    private Client client;
    private WebResource webResource;

    private static final String URL_LOGIN = "http://localhost:8086/login";
    private static final String URL_CUSTOMER_ORDER = "http://localhost:8086/customerOrders/";

    @Before
    public void init() {
        this.client = Client.create();
    }

    @Test
    @Ignore
    public void callOneHundredPerSecond() throws Exception {


        String token = doLogin("aurindo", "password");

        webResource = client.resource(URL_CUSTOMER_ORDER);

        String boyCustomerOrder = "{\n" +
                "\"status\": \"WAITTING_RESTAURANT\",\n" +
                "\"customer\":{\n" +
                "\"id\": 1\n" +
                "},\n" +
                "  \"productList\":[{\n" +
                "    \"id\": 1\n" +
                "  }]\n" +
                "}";

        List<Long> aList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            aList.add(new Long(i));
        }


        System.out.println("::::::: inicio " + new Date());
        callPost(token, boyCustomerOrder, new ArrayList<>(aList));
        callPost(token, boyCustomerOrder, new ArrayList<>(aList));
        System.out.println("::::::: fim " + new Date());

    }

    private void callPost(String token, String boyCustomerOrder, List<Long> aList) {
        aList.parallelStream().forEach(l -> doANewOrder(webResource, token, boyCustomerOrder));
    }

    public String doLogin(String userName, String password) throws Exception {
        webResource = client.resource(URL_LOGIN);

        StringBuilder builder = new StringBuilder("{\n" +
                "  \"login\":\"" + userName + "\",\n" +
                "  \"password\":\"" + password + "\"\n" +
                "}");

        ClientResponse response = webResource.
            accept("application/json").
                header("user-agent", "").
                type(MediaType.APPLICATION_JSON).
                post(ClientResponse.class, builder.toString());

        if (response.getStatus() != HttpStatus.SC_OK) {
            throw new Exception("Login error!");
        }

        return response.getHeaders().getFirst("Authorization");
    }

    private void doANewOrder(WebResource webResource, String token, String bodyMessage) {

        try {
            ClientResponse response = webResource.
                    accept("application/json").
                    header("user-agent", "").
                    header("Authorization", token).
                    type(MediaType.APPLICATION_JSON).
                    post(ClientResponse.class, bodyMessage);

            if (response.getStatus() != HttpStatus.SC_CREATED) {
                throw new Exception("Login error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
