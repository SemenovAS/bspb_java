package client;

import java.util.Arrays;
import java.util.Random;

public class ClientActionsImpl extends Client implements ClientActions {

    @Override
    public Client[] fillClientsArray(TypeClient typeClient, int countClients) {
        Random random = new Random();
        int min;
        int max;

        Client[] clients;
        if (typeClient == TypeClient.INDIVIDUAL) {
            min = 25;
            max = 60;

            clients = new IndividualClient[countClients];
            for (int i = 0; i < clients.length; i++) {
                IndividualClient client = new IndividualClient();
                client.setTypeClient(TypeClient.INDIVIDUAL);
                client.setLastName("LastName" + i);
                client.setFirstName("FirstName" + i);
                client.setMiddleName("MiddleName" + i);
                client.setSex(Sex.values()[random.nextInt(Sex.values().length)]);
                client.setName(client.getLastName() + " " + client.getFirstName() + " " + client.getMiddleName());
                int age = random.nextInt(max - min + 1) + min;
                client.setAge(age);
                clients[i] = client;
            }
        } else {
            min = 100000;
            max = 999999;

            clients = new LegalClient[countClients];
            for (int i = 0; i < clients.length; i++) {
                LegalClient client = new LegalClient();
                client.setTypeClient(TypeClient.LEGAL);
                client.setName("LegalClient" + i);
                client.setOgrn(random.nextInt(max - min + 1) + min);
                clients[i] = client;
            }
        }

        return clients;
    }



}
