package org.vaadin.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class API {

    static HttpRequest request;
    static HttpClient cliente = HttpClient.newBuilder().build();
    static HttpResponse response;

    Gson gson = new Gson();

    static String api = "http://localhost:8080/";
    private static final String urlPrefix = "https://swapi.dev/api/starships/?page=%s";


    public ArrayList<Starship> getSWAPI(int page) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getCharacter(page);
        com.nimbusds.jose.shaded.gson.Gson gson = new com.nimbusds.jose.shaded.gson.Gson();
       Starships charList = gson.fromJson(resultsAPI, Starships.class);
        return charList.getStarships();
    }

    //POST
    static void postData(String url1, ArrayList<Starship> Spaceship) {
        try {

            String resource;



            resource = String.format(api + url1);

            request = HttpRequest
                    .newBuilder(new URI(resource))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(Spaceship.toString()))
                    .build();

            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        }


        catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //GET
    public String getCharacter( int page) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, page);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }
    static String getData(String url1, String url2){
        try {
            String resource;

            if (url2 == null){
                resource = String.format(api + url1);
            }
            else{
                resource = String.format(api + url1 + "/" + url2);
            }

            request= HttpRequest
                    .newBuilder(new URI(resource))
                    .header("Content-Type","application/json")
                    .GET()
                    .build();
            response=cliente.send(request,HttpResponse.BodyHandlers.ofString());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (String) response.body();
    }
}
