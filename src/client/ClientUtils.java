package client;

import java.util.*;
import java.util.stream.Collectors;

public class ClientUtils extends Client {
    public static final String TEST_DOMAIN = "testmail.ru";
    public static Random random = new Random();
    public static int MIN_AGE = 25;
    public static int MAX_AGE = 60;
    public static int MIN_OGRN = 100000;
    public static int MAX_OGRN = 999999;


    /**
     * Заполняет полное имя Клиента ФЛ
     *
     * @param client Клиент ФЛ
     * @return Возвращает полное имя Клиента ФЛ
     */
    public String concatIndividualClientName(IndividualClient client) {
        return client.getLastName() + " " + client.getFirstName() + " " + client.getMiddleName();
    }

    /**
     * Создает клиента
     *
     * @param typeClient Тип клиента
     * @param index      Порядковый номер
     * @return Возвращает созданного клиента
     */
    public Client createClient(Class<?> typeClient, int index) {
        Client client;
        if (typeClient.isInstance(new IndividualClient())) {
            client = new IndividualClient(
                    "LastName" + index,
                    "FirstName" + index,
                    "MiddleName" + index,
                    random.nextInt(MAX_AGE - MIN_AGE + 1) + MIN_AGE,
                    IndividualClient.Gender.values()[random.nextInt(Gender.values().length)]
            );
            client.setName(concatIndividualClientName((IndividualClient) client));
        } else {
            client = new LegalClient(
                    "LegalClient" + index,
                    random.nextInt(MAX_OGRN - MIN_OGRN + 1) + MIN_OGRN
            );
        }

        client.setEmail(createEmail(client));

        return client;
    }

    /**
     * Заполняет и возвращает массив клиентов по типу в заданном количестве
     *
     * @param typeClient   Тип клиента
     * @param countClients Количество клиентов
     * @return Заполняет и возвращает массив клиентов по типу в заданном количестве
     */
    public Client[] fillArrayOfClients(Class<?> typeClient, int countClients) {
        Client[] clients;
        Client client;
        if (typeClient.isInstance(new IndividualClient())) {
            clients = new IndividualClient[countClients];
            for (int i = 0; i < clients.length; i++) {
                client = createClient(IndividualClient.class, i);
                clients[i] = client;
            }
        } else {
            clients = new LegalClient[countClients];
            for (int i = 0; i < clients.length; i++) {
                client = createClient(LegalClient.class, i);
                clients[i] = client;
            }
        }

        return clients;
    }


    public List<Client> fillListOfClients(Class<?> typeClient, int countClients) {
        List<Client> clients = new ArrayList<>();
        Client client;
        if (typeClient.isInstance(new IndividualClient())) {
            for (int i = 0; i < countClients; i++) {
                client = createClient(IndividualClient.class, i);
                clients.add(client);
            }
        } else {
            for (int i = 0; i < countClients; i++) {
                client = createClient(LegalClient.class, i);
                clients.add(client);
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
    public IndividualClient[] filterArrayOfClientsByGenderStream(IndividualClient[] clients,
                                                                 IndividualClient.Gender gender) {
        return Arrays.stream(clients)
                .filter(client -> client.getGender().equals(gender)).toArray(IndividualClient[]::new);
    }

    public List<Client> filterListOfClientsByGender(List<Client> clients,
                                                    IndividualClient.Gender gender) {

        return clients.stream()
                .filter(IndividualClient.class::isInstance)
                .map(IndividualClient.class::cast)
                .filter(client -> client.getGender().equals(gender))
                .collect(Collectors.toList());
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

    public List<Client> sortClientsByAge(List<Client> clients,
                                         Comparator<IndividualClient> individualClientComparator) {
        return clients.stream()
                .filter(IndividualClient.class::isInstance)
                .map(IndividualClient.class::cast)
                .sorted(individualClientComparator)
                .collect(Collectors.toList());
    }


    /**
     * Возвращает фильтрованный по типу клиента массив клиентов
     *
     * @param clients    Массив клиентов
     * @param typeClient Тип клиента
     * @return Возвращает фильтрованный по типу клиента массив клиентов
     */
    public Client[] filterArrayOfClientsByType(Client[] clients, Class<?> typeClient) {
        return Arrays.stream(clients).filter(typeClient::isInstance).toArray(Client[]::new);
    }

    public List<Client> filterListOfClientsByType(List<Client> clients, Class<?> typeClient) {
        return clients.stream()
                .filter(LegalClient.class::isInstance)
                .map(LegalClient.class::cast)
                .filter(typeClient::isInstance)
                .collect(Collectors.toList());
    }


    /**
     * Возвращает массив клиентов с добавленными в него дубликатами в количестве
     *
     * @param clients Массив клиентов
     * @param count   Количество дубликатов
     * @return Возвращает массив клиентов с добавленными в него дубликатами в количестве
     */
    public Client[] addDublicatesToArrayOfClients(Client[] clients, int count) {
        int index = clients.length;
        clients = Arrays.copyOf(clients, clients.length + count);

        for (int i = index; i < clients.length; i++) {
            clients[i] = clients[0];
        }

        return clients;
    }

    public List<Client> addDublicatesToListOfClients(List<Client> clients, int count) {
        for (int i = 0; i < count; i++) {
            clients.add(clients.get(0));
        }

        return clients;
    }


    /**
     * Возвращает массив клиентов без дубликатов
     *
     * @param clients Массив клиентов
     * @return Возвращает массив клиентов без дубликатов
     */
    public Client[] deleteDublicatesFromArrayOfClients(Client[] clients) {
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

    public List<Client> deleteDublicatesFromListOfClients(List<Client> clients) {
        return new ArrayList<>(new HashSet<>(clients));
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
    public void printArrayOfClients(Client[] clients) {
        for (Client element : clients) {
            System.out.print(element.toString());
        }
        System.out.printf("Количество клиентов: %d%n", clients.length);
    }

    public void printListOfClients(List<Client> clients) {
        System.out.print(clients);
        System.out.printf("%nКоличество клиентов: %d%n", clients.size());
    }

}
