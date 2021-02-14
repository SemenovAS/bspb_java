import java.util.Arrays;
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

    public static Client[] fillClientsArray(int countClients) {
        Random random = new Random();
        int min = 25;
        int max = 60;

        Client[] clients = new Client[countClients];
        for (int i = 0; i < clients.length; i++) {
            Client client = new Client();
            client.setLastName("LastName " + i);
            client.setFirstName("FirstName " + i);
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
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getSex().equals(sex)) {
                Client client = clients[i];
                filteredClient = Arrays.copyOf(filteredClient, filteredClient.length + 1);
                filteredClient[filteredClient.length - 1] = client;
            }
        }

        return filteredClient;
    }

    public enum Sex {
        MALE,
        FEMALE
    }

    ;
}
