package client;

public interface ClientActions {
    /**
     * Заполняет и возвращает массив клиентов
     *
     * @param typeClient   Тип клиента
     * @param countClients Количество клиентов
     * @return Заполняет и возвращает массив клиентов
     */
    Client[] fillClientsArray(Client.TypeClient typeClient, int countClients);

}
