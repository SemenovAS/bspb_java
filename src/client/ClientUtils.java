package client;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class ClientUtils extends Client {
    public static final String TEST_DOMAIN = "testmail.ru";

    /**
     * Заполняет и возвращает массив клиентов по типу в заданном количестве
     *
     * @param typeClient   Тип клиента
     * @param countClients Количество клиентов
     * @return Заполняет и возвращает массив клиентов по типу в заданном количестве
     */
    public Client[] fillClientsArray(Class<?> typeClient, int countClients) {
        Random random = new Random();
        int min;
        int max;

        Client[] clients;
        if (typeClient.isInstance(new IndividualClient())) {
            min = 25;
            max = 60;

            clients = new IndividualClient[countClients];
            for (int i = 0; i < clients.length; i++) {
                IndividualClient client = new IndividualClient();
                client.setLastName("LastName" + i);
                client.setFirstName("FirstName" + i);
                client.setMiddleName("MiddleName" + i);
                client.setGender(IndividualClient.Gender.values()[random.nextInt(Gender.values().length)]);
                client.setName(client.getLastName() + " " + client.getFirstName() + " " + client.getMiddleName());
                client.setEmail(createEmail(client));
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
                client.setName("LegalClient" + i);
                client.setOgrn(random.nextInt(max - min + 1) + min);
                client.setEmail(createEmail(client));
                clients[i] = client;
            }
        }

        return clients;
    }


    /**
     * Возвращает отфильтрованный по полу клиента массив ФЛ
     *
     * @param clients Массив клиентов ФЛ
     * @param gender  Пол клиента
     * @return Возвращает отфильтрованный по полу клиента массив
     */
    public IndividualClient[] filterClientsByGender(IndividualClient[] clients, IndividualClient.Gender gender) {
        IndividualClient[] filteredClient = new IndividualClient[0];
        for (IndividualClient value : clients) {
            if (value.getGender().equals(gender)) {
                filteredClient = Arrays.copyOf(filteredClient, filteredClient.length + 1);
                filteredClient[filteredClient.length - 1] = value;
            }
        }

        return filteredClient;
    }

    /**
     * Возвращает отфильтрованный по полу клиента массив ФЛ
     *
     * @param clients Массив клиентов ФЛ
     * @param gender  Пол клиента
     * @return Возвращает отфильтрованный по полу клиента массив ФЛ
     */
    public IndividualClient[] filterClientsByGenderStream(IndividualClient[] clients, IndividualClient.Gender gender) {
        return Arrays.stream(clients)
                .filter(client -> client.getGender().equals(gender)).toArray(IndividualClient[]::new);
    }


    /**
     * Возвращает отсортированный по возрасту массив ФЛ клиентов
     *
     * @param clients Массив клиентов ФЛ
     * @return Возвращает отсортированный по возрасту массив ФЛ клиентов
     */
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


    /**
     * Возвращает фильтрованный по типу клиента массив клиентов
     *
     * @param clients    Массив клиентов
     * @param typeClient Тип клиента
     * @return Возвращает фильтрованный по типу клиента массив клиентов
     */
    public Client[] filterClientsByType(Client[] clients, Class<?> typeClient) {
        return Arrays.stream(clients)
                .filter(typeClient::isInstance).toArray(Client[]::new);
    }


    /**
     * Возвращает массив клиентов с добавленными в него дубликатами в количестве
     *
     * @param clients Массив клиентов
     * @param count   Количество дубликатов
     * @return Возвращает массив клиентов с добавленными в него дубликатами в количестве
     */
    public Client[] addDublicates(Client[] clients, int count) {
        int index = clients.length;
        clients = Arrays.copyOf(clients, clients.length + count);

        for (int i = index; i < clients.length; i++) {
            clients[i] = clients[0];
        }

        return clients;
    }

    /**
     * Возвращает массив клиентов без дубликатов
     *
     * @param clients Массив клиентов
     * @return Возвращает массив клиентов без дубликатов
     */
    public Client[] deleteDublicates(Client[] clients) {
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

    /**
     * Возвращает наименование емейл клиента
     *
     * @param client Клиент
     * @return Возвращает наименование емейл клиента
     */
    private String createEmail(Client client) {
        return client.getName().toLowerCase(Locale.ROOT).replaceAll("\\s", "_")
                + '@' + ClientUtils.TEST_DOMAIN;
    }

    /**
     * Распечатывает массив клиентов
     *
     * @param clients Массив клиентов
     */
    public void printClients(Client[] clients) {
        for (Client element : clients) {
            System.out.print(element.toString());
        }
        System.out.printf("Количество клиентов: %d%n", clients.length);
    }

}
