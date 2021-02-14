public class Lesson2 {

    public static void main(String[] args) {
        int cntClients = 10;
        Client[] clients = Client.fillClientsArray(cntClients);


        System.out.println("Без сортировки и фильтрации:");
        for (Client element : clients) {
            System.out.println("ClientName = " + element.getLastName() + ", Sex = " +
                    element.getSex() + ", Age = " + element.getAge());
        }

        System.out.println("\nОтфильтрованные по полу и отсортированные по возрасту");
        Client[] filterClientsBySex = Client.filterClientsBySex(clients, Client.Sex.FEMALE);
        Client[] sortedClients = Client.sortClientsByAge(filterClientsBySex);
        for (Client element : sortedClients) {
            System.out.println("ClientName = " + element.getLastName() + ", Sex = " +
                    element.getSex() + ", Age = " + element.getAge());
        }
        System.out.println("Количество клиентов: " + filterClientsBySex.length);
    }


}

