import java.util.Random;

public class Lesson2 {

    public static void main(String[] args) {
        //Вариант 2
        int cntClients = 10;
//        Client[] clients = Client.fillClientsArray(cntClients);
//
//
//        System.out.println("Без сортировки и фильтрации:");
//        for (Client element : clients) {
//            System.out.printf("Клиент: %s %s %s, Пол: %s, Возраст: %d%n",
//                    element.getLastName(),
//                    element.getFirstName(),
//                    element.getMiddleName(),
//                    element.getSex(),
//                    element.getAge());
//        }
//
//        System.out.println("\nОтфильтрованные по полу и отсортированные по возрасту");
//        Client[] filterClientsBySex = Client.filterClientsBySex(clients, Client.Sex.FEMALE);
//        Client[] sortedClients = Client.sortClientsByAge(filterClientsBySex);
//        for (Client element : sortedClients) {
//            System.out.printf("Клиент: %s %s %s, Пол: %s, Возраст: %d%n",
//                    element.getLastName(),
//                    element.getFirstName(),
//                    element.getMiddleName(),
//                    element.getSex(),
//                    element.getAge());
//        }
//        System.out.printf("Количество клиентов: %d%n", filterClientsBySex.length);


        //Вариант 2
        Random random = new Random();
        int min = 25;
        int max = 60;
        String[] arraySex = {"Мужской", "Женский"};

        String[][] clients2 = new String[cntClients][3];
        int sizeFilteredArray = 0;

        for (int i = 0; i < clients2.length; i++) {
            clients2[i][0] = String.format("ClientName%s", i);
            String sex = arraySex[random.nextInt(arraySex.length)];
            clients2[i][1] = sex;
            clients2[i][2] = String.valueOf(random.nextInt(max - min + 1) + min);
            if (sex.equals(arraySex[0])) {
                sizeFilteredArray++;
            }

        }

        System.out.println("Вариант 2. Без сортировки и фильтрации:");
        for (String[] strings : clients2) {
            System.out.printf("Клиент: %s, Пол: %s, Возраст: %s%n", strings[0], strings[1], strings[2]);
        }
        System.out.printf("Количество клиентов: %d%n", clients2.length);


        System.out.println("\nОтфильтрованные по полу и отсортированные по возрасту");
        String[][] filteredClients2 = new String[sizeFilteredArray][3];
        for (String[] element : clients2) {
            if (element[1].equals(arraySex[0])) {
                filteredClients2[sizeFilteredArray - 1] = element;
                sizeFilteredArray--;
            }

        }

        String[][] tmpClients = new String[1][3];
        for (int i = filteredClients2.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt(filteredClients2[j][2]) > Integer.parseInt(filteredClients2[j + 1][2])) {
                    tmpClients[0] = filteredClients2[j];
                    filteredClients2[j] = filteredClients2[j + 1];
                    filteredClients2[j + 1] = tmpClients[0];
                }
            }
        }


        for (String[] element : filteredClients2) {
            System.out.printf("Клиент: %s, Пол: %s, Возраст: %s%n",
                    element[0], element[1], element[2]);
        }
        System.out.printf("Количество клиентов: %d%n", filteredClients2.length);

    }

}

