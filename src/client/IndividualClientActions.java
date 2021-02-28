package client;

public interface IndividualClientActions extends ClientActions {

    /**
     * Возвращает отфильтрованный по полу массив клиентов
     *
     * @param clients Массив клиентов
     * @param sex     Пол клиента
     * @return Возвращает отфильтрованный по полу массив клиентов
     */
    IndividualClient[] filterClientsBySex(IndividualClient[] clients, Client.Sex sex);
    IndividualClient[] filterClientsBySexStream(IndividualClient[] clients, Client.Sex sex);

    /**
     * Возвразает отсортированный по возрасту массив клиентов
     *
     * @param clients Массив клиентов
     * @return Возвразает отсортированный по возрасту массив клиентов
     */
    IndividualClient[] sortClientsByAge(IndividualClient[] clients);

}
