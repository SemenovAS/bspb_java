import client.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Lesson4 {

    public static ClientUtils clientUtils = new ClientUtils();
    public static final Class<?> individualClient = IndividualClient.class;
    public static final Class<?> legalClient = LegalClient.class;


    public static void main(String[] args) {
        int cntClients = 10;

        System.out.println("\nКлиенты ФЛ без сортировки и фильтрации:");
        Client[] individualClients = clientUtils.fillClientsArray(individualClient, cntClients);
        clientUtils.printClients(individualClients);

        System.out.println("\nКлиенты ФЛ отфильтрованные по полу и отсортированные по возрасту");
        Client[] filterClientsByGender = clientUtils.
                filterClientsByGenderStream((IndividualClient[]) individualClients, IndividualClient.Gender.FEMALE);

        Comparator<IndividualClient> individualClientComparator = Comparator.comparing(IndividualClient::getAge);
        Arrays.sort((IndividualClient[]) filterClientsByGender, individualClientComparator);
        clientUtils.printClients(filterClientsByGender);

        System.out.println("\nКлиенты ЮЛ без сортировки и фильтрации:");
        Client[] legalClients = clientUtils.fillClientsArray(legalClient, cntClients);
        clientUtils.printClients(legalClients);

        System.out.println("\nКлиенты ФЛ/ЮЛ до фильтрации:");
        Client[] allClients =
                Stream.concat(Arrays.stream(individualClients), Arrays.stream(legalClients)).toArray(Client[]::new);
        clientUtils.printClients(allClients);

        System.out.println("\nКлиенты отфильтрованные по типу: " + legalClient.toString());
        Client[] allClientsFiltered = clientUtils.filterClientsByType(allClients, legalClient);
        clientUtils.printClients(allClientsFiltered);

    }
}
