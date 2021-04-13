package ru.bspb;

import ru.bspb.restApiData.ListUsers;
import ru.bspb.restApiUtils.ApiUtils;

import java.io.IOException;
import java.util.List;


public class Lesson10 {
    public static ApiUtils apiUtils = new ApiUtils();

    public static void main(String[] args) throws IOException, InterruptedException {
        //1) Получить список ВСЕХ пользователей используя эндпоинт GET LIST USERS
        List<ListUsers.User> listAllUsers = apiUtils.getListUsers();

        //2) Удалить всех пользователей с именем George используя DELETE эндпоинт
        List<ListUsers.User> deletedUsers = apiUtils.deleteUsers(listAllUsers, "George");

        //3) Заново создать удалённых пользователей используя POST CREATE эндпоинт
        apiUtils.createUsers(deletedUsers);
    }


}
