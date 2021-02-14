public class Lesson2 {

    public static void main(String[] args) {
        int cntClients = 10;
        Client[] clients = Client.fillClientsArray(cntClients);


        System.out.println("Без сортировки и фильтрации:");
        for (Client element : clients) {
            System.out.printf("Клиент: %s %s %s, Пол: %s, Возраст: %d%n",
                    element.getLastName(),
                    element.getFirstName(),
                    element.getMiddleName(),
                    element.getSex(),
                    element.getAge());
        }

        System.out.println("\nОтфильтрованные по полу и отсортированные по возрасту");
        Client[] filterClientsBySex = Client.filterClientsBySex(clients, Client.Sex.FEMALE);
        Client[] sortedClients = Client.sortClientsByAge(filterClientsBySex);
        for (Client element : sortedClients) {
            System.out.printf("Клиент: %s %s %s, Пол: %s, Возраст: %d%n",
                    element.getLastName(),
                    element.getFirstName(),
                    element.getMiddleName(),
                    element.getSex(),
                    element.getAge());
        }
        System.out.printf("Количество клиентов: %d%n", filterClientsBySex.length);
    }

}

