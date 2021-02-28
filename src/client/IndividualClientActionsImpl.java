package client;

import java.util.Arrays;

public class IndividualClientActionsImpl extends IndividualClient implements IndividualClientActions {

    public IndividualClient[] filterClientsBySex(IndividualClient[] clients, Sex sex) {
        IndividualClient[] filteredClient = new IndividualClient[0];
        for (IndividualClient value : clients) {
            if (value.getSex().equals(sex)) {
                filteredClient = Arrays.copyOf(filteredClient, filteredClient.length + 1);
                filteredClient[filteredClient.length - 1] = value;
            }
        }

        return filteredClient;
    }

    @Override
    public IndividualClient[] filterClientsBySexStream(IndividualClient[] clients, Sex sex) {
        return Arrays.stream(clients)
                .filter(client -> client.getSex().equals(Client.Sex.FEMALE)).toArray(IndividualClient[]::new);
    }

    public IndividualClient[] sortClientsByAge(IndividualClient[] clients) {
        for (int i = clients.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (clients[j].getAge() > clients[j + 1].getAge()) {
                    IndividualClient tmpClient = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = tmpClient;
                }
            }
        }

        return clients;
    }


}
