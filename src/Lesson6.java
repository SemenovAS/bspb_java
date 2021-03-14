import client.Client;
import client.ClientUtils;
import client.IndividualClient;
import client.LegalClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class Lesson6 {

    public static ClientUtils clientUtils = new ClientUtils();
    public static final Class<?> individualClient = IndividualClient.class;
    public static final Class<?> legalClient = LegalClient.class;


    public static void main(String[] args) {
        int cntClients = 10;

        System.out.println("\nКлиенты ФЛ без сортировки и фильтрации:");
        List<Client> individualClients = clientUtils.fillListOfClients(individualClient, cntClients);
        clientUtils.printListOfClients(individualClients);

        System.out.println("\nКлиенты ФЛ отфильтрованные по полу и отсортированные по возрасту");
        List<Client> filterClientsByGender = clientUtils.
                filterListOfClientsByGender(individualClients, IndividualClient.Gender.FEMALE);

        Comparator<IndividualClient> individualClientComparator = Comparator.comparing(IndividualClient::getAge);
        List<Client> sortClientsByAge = clientUtils.sortClientsByAge(filterClientsByGender, individualClientComparator);
        clientUtils.printListOfClients(sortClientsByAge);

        System.out.println("\nКлиенты ЮЛ без сортировки и фильтрации:");
        List<Client> legalClients = clientUtils.fillListOfClients(legalClient, cntClients);
        clientUtils.printListOfClients(legalClients);

        System.out.println("\nКлиенты ФЛ/ЮЛ до фильтрации:");
        List<Client> allClients = new ArrayList<>(legalClients);
        allClients.addAll(individualClients);
        clientUtils.printListOfClients(allClients);

        System.out.println("\nКлиенты отфильтрованные по типу: " + legalClient.toString());
        allClients = clientUtils.filterListOfClientsByType(allClients, legalClient);
        clientUtils.printListOfClients(allClients);

        System.out.println("\nКлиенты с дубликатами: " + legalClient.toString());
        List<Client> dublicateOfClients = clientUtils.addDublicatesToListOfClients(allClients, cntClients);
        clientUtils.printListOfClients(dublicateOfClients);

        System.out.println("\nКлиенты без дубликатов: " + legalClient.toString());
        List<Client> clientsWithoutDublicate = clientUtils.deleteDublicatesFromListOfClients(allClients);
        clientUtils.printListOfClients(clientsWithoutDublicate);
    }
}
