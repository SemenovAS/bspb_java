package ru.bspb;

import ru.bspb.client.*;

public class Lesson3 {

    public static ClientUtils clientUtils = new ClientUtils();
    public static Class<?> individualClient = IndividualClient.class;


    public static void main(String[] args) {
        int cntClients = 10;

        Client[] clients = clientUtils.fillArrayOfClients(individualClient, cntClients);

        System.out.println("\nБез сортировки и фильтрации:");
        clientUtils.printArrayOfClients(clients);

        System.out.println("\nОтфильтрованные по полу и отсортированные по возрасту");
        IndividualClient[] filterClientsBySex =
                clientUtils.filterClientsByGender((IndividualClient[]) clients, IndividualClient.Gender.FEMALE);
        IndividualClient[] sortedClients = clientUtils.sortClientsByAge(filterClientsBySex);
        clientUtils.printArrayOfClients(sortedClients);

        clients = clientUtils.addDublicatesToArrayOfClients(sortedClients, 5);
        System.out.println("\nДобавлены дубликаты клиентов:");
        clientUtils.printArrayOfClients(clients);

        Client[] clientsWithoutDublicate = clientUtils.deleteDublicatesFromArrayOfClients(clients);
        System.out.println("\nУдаление дубликатов клиентов:");
        clientUtils.printArrayOfClients(clientsWithoutDublicate);
    }
}
