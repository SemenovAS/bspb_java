package ru.bspb.restApiUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bspb.restApiData.ListUsers;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApiUtils extends ListUsers {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final String HOST_URL = "https://reqres.in/api/";


    private int getListUsersCountPages() throws IOException, InterruptedException {
        HttpRequest getListUsersRequest = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(HOST_URL + "users"))
                .build();
        HttpResponse<String> getResponse = HTTP_CLIENT.send(getListUsersRequest, HttpResponse.BodyHandlers.ofString());
        ListUsers listUsers = mapper.readValue(getResponse.body(), ListUsers.class);
        return listUsers.getTotalPages();
    }

    private List<User> getListUsersByPage(int pageNum) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(HOST_URL + "users?" + "page=" + pageNum))
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ListUsers listUsers = mapper.readValue(response.body(), ListUsers.class);
            return listUsers.getUserList();
        } else
            throw new RuntimeException("Не удалось получить список клиентов");
    }

    public List<User> getListUsers() throws IOException, InterruptedException {
        System.out.println(ANSI_RED + "Список всех пользователей +++++++++++++++++++++++++++++++++" + ANSI_RESET);
        List<User> listUsers = new ArrayList<>();
        for (int i = 0; i < getListUsersCountPages(); i++) {
            listUsers.addAll(getListUsersByPage(i + 1));
        }
        System.out.println("\n" + listUsers.toString());
        System.out.println(ANSI_RED + "Количество всех пользователей: " + listUsers.size() + ANSI_RESET);

        return listUsers;
    }


    public List<User> getListUsersByName(List<User> listUsers, String userName) {
        System.out.println(ANSI_RED + "\nПолучение клиентов по имени +++++++++++++++++++++++++++++++++" + ANSI_RESET);
        List<User> georgeList =
                listUsers.stream().filter(user -> user.getFirstName().equals(userName)).collect(Collectors.toList());
        System.out.println("\n" + georgeList.toString());
        System.out.println(ANSI_RED + "Количество пользователей с именем \"" + userName + "\": "
                + georgeList.size() + ANSI_RESET);

        return georgeList;
    }

    public List<User> deleteUsers(List<User> listUsers, String userName) throws IOException, InterruptedException {
        List<ListUsers.User> listUsersByName = getListUsersByName(listUsers, userName);

        System.out.println(ANSI_RED + "\nУдаление клиентов+++++++++++++++++++++++++++++++++" + ANSI_RESET);
        int i = 0;
        List<User> deletedUsers = new ArrayList<>();
        for (User user : listUsersByName) {
            HttpRequest delete = HttpRequest.newBuilder()
                    .DELETE()
                    .header("content-type", "application/json")
                    .uri(URI.create(HOST_URL + "users/" + user.getId()))
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(delete, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 204) {
                i++;
                deletedUsers.add(user);
                System.out.println("Пользователь ID=" + user.getId() + ", удален");
                System.out.println(user.toString());
            } else
                throw new RuntimeException("Не удалось получить информацию по удаленному клиенту");
        }

        System.out.println(ANSI_RED + "Количество удаленных клиентов: " + i + ANSI_RESET);
        return deletedUsers;
    }

    public void createUsers(List<User> listUsersByName) throws IOException, InterruptedException {
        System.out.println(ANSI_RED + "\nСоздание клиентов+++++++++++++++++++++++++++++++++" + ANSI_RESET);
        int i = 0;
        for (User user : listUsersByName) {
            String body = mapper.writeValueAsString(user);
            HttpRequest post = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .header("content-type", "application/json")
                    .uri(URI.create(HOST_URL + "users"))
                    .build();
            HttpResponse<String> response = HTTP_CLIENT.send(post, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                i++;
                User addedUser = mapper.readValue(response.body(), User.class);
                System.out.println(addedUser.toString());
                System.out.println("Пользователь создан");
            } else
                throw new RuntimeException("Не удалось получить информацию по созданному клиенту");
        }
        System.out.println(ANSI_RED + "Количество добавленных клиентов: " + i + ANSI_RESET);
    }


}

