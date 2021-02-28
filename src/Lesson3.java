import client.*;

public class Lesson3 {

    public static IndividualClientActions individualClientActions = new IndividualClientActionsImpl();

    public static void main(String[] args) {
        int cntClients = 10;

        Client[] clients = new IndividualClient().fillClientsArray(Client.TypeClient.INDIVIDUAL, cntClients);

        System.out.println("\nБез сортировки и фильтрации:");
        Client.printClients(clients);

        System.out.println("\nОтфильтрованные по полу и отсортированные по возрасту");
        Client[] filterClientsBySex =
                individualClientActions.filterClientsBySex((IndividualClient[]) clients, Client.Sex.FEMALE);
        Client[] sortedClients = individualClientActions.sortClientsByAge((IndividualClient[]) filterClientsBySex);
        Client.printClients(sortedClients);

        clients = Client.addDublicates(sortedClients, 5);
        System.out.println("\nДобавлены дубликаты клиентов:");
        Client.printClients(clients);

        Client[] clientsWithoutDublicate = Client.deleteDublicates(clients);
        System.out.println("\nУдаление дубликатов клиентов:");
        Client.printClients(clientsWithoutDublicate);
    }
}
