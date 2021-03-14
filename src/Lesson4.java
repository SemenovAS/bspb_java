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
        Client[] individualClients = clientUtils.fillArrayOfClients(individualClient, cntClients);
        clientUtils.printArrayOfClients(individualClients);

        System.out.println("\nКлиенты ФЛ отфильтрованные по полу и отсортированные по возрасту");
        Client[] filterClientsByGender = clientUtils.
                filterArrayOfClientsByGenderStream((IndividualClient[]) individualClients, IndividualClient.Gender.FEMALE);

        Comparator<IndividualClient> individualClientComparator = Comparator.comparing(IndividualClient::getAge);
        Arrays.sort((IndividualClient[]) filterClientsByGender, individualClientComparator);
        clientUtils.printArrayOfClients(filterClientsByGender);

        System.out.println("\nКлиенты ЮЛ без сортировки и фильтрации:");
        Client[] legalClients = clientUtils.fillArrayOfClients(legalClient, cntClients);
        clientUtils.printArrayOfClients(legalClients);

        System.out.println("\nКлиенты ФЛ/ЮЛ до фильтрации:");
        Client[] allClients =
                Stream.concat(Arrays.stream(individualClients), Arrays.stream(legalClients)).toArray(Client[]::new);
        clientUtils.printArrayOfClients(allClients);

        System.out.println("\nКлиенты отфильтрованные по типу: " + legalClient.toString());
        Client[] allClientsFiltered = clientUtils.filterArrayOfClientsByType(allClients, legalClient);
        clientUtils.printArrayOfClients(allClientsFiltered);

    }
}
