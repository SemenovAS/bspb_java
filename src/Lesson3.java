import client.Client;

public class Lesson3 {

    public static void main(String[] args) {
        int cntClients = 10;

        Client[] clients = Client.fillClientsArray(cntClients);
        System.out.println("\nБез сортировки и фильтрации:");
        Client.printClients(clients);

        System.out.println("\nОтфильтрованные по полу и отсортированные по возрасту");
        Client[] filterClientsBySex = Client.filterClientsBySex(clients, Client.Sex.FEMALE);
        Client[] sortedClients = Client.sortClientsByAge(filterClientsBySex);
        Client.printClients(sortedClients);

        clients = Client.addDublicates(sortedClients, 5);
        System.out.println("\nДобавлены дубликаты клиентов:");
        Client.printClients(clients);

        Client[] clientsWithoutDublicate = Client.deleteDublicates(clients);
        System.out.println("\nУдаление дубликатов клиентов:");
        Client.printClients(clientsWithoutDublicate);
    }
}
