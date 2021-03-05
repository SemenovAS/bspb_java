import client.*;

public class Lesson3 {

    public static ClientUtils clientUtils = new ClientUtils();
    public static Class<?> individualClient = IndividualClient.class;


    public static void main(String[] args) {
        int cntClients = 10;

        Client[] clients = clientUtils.fillClientsArray(individualClient, cntClients);

        System.out.println("\nБез сортировки и фильтрации:");
        clientUtils.printClients(clients);

        System.out.println("\nОтфильтрованные по полу и отсортированные по возрасту");
        IndividualClient[] filterClientsBySex =
                clientUtils.filterClientsByGender((IndividualClient[]) clients, IndividualClient.Gender.FEMALE);
        IndividualClient[] sortedClients = clientUtils.sortClientsByAge(filterClientsBySex);
        clientUtils.printClients(sortedClients);

        clients = clientUtils.addDublicates(sortedClients, 5);
        System.out.println("\nДобавлены дубликаты клиентов:");
        clientUtils.printClients(clients);

        Client[] clientsWithoutDublicate = clientUtils.deleteDublicates(clients);
        System.out.println("\nУдаление дубликатов клиентов:");
        clientUtils.printClients(clientsWithoutDublicate);
    }
}
