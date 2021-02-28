package client;

import java.util.Arrays;


public abstract class Client {
    private TypeClient typeClient;
    private String name;

    public TypeClient getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(TypeClient typeClient) {
        this.typeClient = typeClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public enum Sex {
        MALE,
        FEMALE
    }

    public enum TypeClient {
        LEGAL(0),
        INDIVIDUAL(1);
        private final int typeClient;

        TypeClient(int typeClient) {
            this.typeClient = typeClient;
        }

        public static int getValueByEnum(int value) {
            for (TypeClient element : TypeClient.values()) {
                if (element.typeClient == value)
                    return element.typeClient;
            }
            return 0;
        }
    }

    public abstract Client[] fillClientsArray(TypeClient typeClient, int countClients);


    public static Client[] addDublicates(Client[] clients, int count) {
        int index = clients.length;
        clients = Arrays.copyOf(clients, clients.length + count);

        for (int i = index; i < clients.length; i++) {
            clients[i] = clients[0];
        }

        return clients;
    }

    public static Client[] deleteDublicates(Client[] clients) {
        Client[] clientsWithoutDublicate = new Client[0];

        Client client1;
        Client client2;
        for (int i = 0; i < clients.length; i++) {
            if (i + 1 < clients.length) {
                client1 = clients[i];
                client2 = clients[i + 1];
                if (!client1.equals(client2)) {
                    clientsWithoutDublicate = Arrays.copyOf(clientsWithoutDublicate,
                            clientsWithoutDublicate.length + 1);
                    clientsWithoutDublicate[clientsWithoutDublicate.length - 1] = client1;
                }
            }
        }

        return clientsWithoutDublicate;
    }


    public static Client[] filterClientsByType(Client[] clients, TypeClient typeClient) {
        return Arrays.stream(clients)
                .filter(client -> client.getTypeClient().equals(typeClient)).toArray(Client[]::new);
    }

    public static void printClients(Client[] clients) {
        for (Client element : clients) {
            System.out.print(element.toString());
        }
        System.out.printf("Количество клиентов: %d%n", clients.length);
    }

    @Override
    public String toString() {
        return String.format("Клиент: %s%n", name);
    }


}
