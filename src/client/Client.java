package client;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class Client {
    private String lastName;
    private String firstName;
    private String middleName;
    private int age;
    private Sex sex;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }


    public enum Sex {
        MALE,
        FEMALE
    }

    public static Client[] fillClientsArray(int countClients) {
        Random random = new Random();
        int min = 25;
        int max = 60;

        Client[] clients = new Client[countClients];
        for (int i = 0; i < clients.length; i++) {
            Client client = new Client();
            client.setLastName("LastName" + i);
            client.setFirstName("FirstName" + i);
            client.setMiddleName("MiddleName" + i);
            client.setSex(Sex.values()[random.nextInt(Sex.values().length)]);
            int age = random.nextInt(max - min + 1) + min;
            client.setAge(age);
            clients[i] = client;
        }

        return clients;
    }

    public static Client[] sortClientsByAge(Client[] clients) {
        for (int i = clients.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (clients[j].getAge() > clients[j + 1].getAge()) {
                    Client tmpClient = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = tmpClient;
                }
            }
        }

        return clients;
    }

    public static Client[] filterClientsBySex(Client[] clients, Sex sex) {
        Client[] filteredClient = new Client[0];
        for (Client value : clients) {
            if (value.getSex().equals(sex)) {
                filteredClient = Arrays.copyOf(filteredClient, filteredClient.length + 1);
                filteredClient[filteredClient.length - 1] = value;
            }
        }

        return filteredClient;
    }

    public static Client[] addDublicates(Client[] clients, int count) {
        int index = clients.length;
        clients = Arrays.copyOf(clients, clients.length + count);

        Client client = new Client();
        for (int i = index; i < clients.length; i++) {
            client.setLastName("LastName");
            client.setFirstName("FirstName");
            client.setMiddleName("MiddleName");
            client.setSex(Sex.MALE);
            client.setAge(50);
            clients[i] = client;
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

    public static void printClients(Client[] clients) {
        for (Client element : clients) {
            System.out.print(element.toString());
        }
        System.out.printf("Количество клиентов: %d%n", clients.length);
    }

    @Override
    public String toString() {
        return String.format("Клиент: %s %s %s, Пол: %s, Возраст: %d%n",
                lastName, firstName, middleName, sex, age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Client client = (Client) obj;

        return
                age == client.age &&
                        lastName.equals(client.lastName) &&
                        firstName.equals(client.firstName) &&
                        middleName.equals(client.middleName) &&
                        sex == client.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, middleName, age, sex);
    }
}
