import client.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Lesson4 {

    public static IndividualClientActions individualClientActions = new IndividualClientActionsImpl();

    public static void main(String[] args) {
        int cntClients = 10;

        System.out.println("\nКлиенты ФЛ без сортировки и фильтрации:");
        Client[] individualClients = new IndividualClient().fillClientsArray(Client.TypeClient.INDIVIDUAL, cntClients);
        Client.printClients(individualClients);

        System.out.println("\nКлиенты ФЛ отфильтрованные по полу и отсортированные по возрасту");
        Client[] filterClientsBySex = individualClientActions.
                filterClientsBySexStream((IndividualClient[]) individualClients, Client.Sex.FEMALE);

        Comparator<Client> comparator = Comparator.comparing(Client::getName);
        Arrays.sort(filterClientsBySex, comparator);
        Client.printClients(filterClientsBySex);

        System.out.println("\nКлиенты ЮЛ без сортировки и фильтрации:");
        Client[] legalClients = new LegalClient().fillClientsArray(Client.TypeClient.LEGAL, cntClients);
        Client.printClients(legalClients);


        System.out.println("\nВсе клиенты ФЛ/ЮЛ:");
        Client[] allClients =
                Stream.concat(Arrays.stream(individualClients), Arrays.stream(legalClients)).toArray(Client[]::new);
        Client.printClients(allClients);

        System.out.println("\nКлиенты отфильтрованные по типу:");
        Client[] allClientsFiltered = Client.filterClientsByType(allClients, Client.TypeClient.LEGAL);
        Client.printClients(allClientsFiltered);

    }
}
