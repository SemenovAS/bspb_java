package client;

public class LegalClient extends Client {
    private int ogrn;

    public int getOgrn() {
        return ogrn;
    }

    public void setOgrn(int ogrn) {
        this.ogrn = ogrn;
    }

    public static ClientActions clientActions = new ClientActionsImpl();


    @Override
    public Client[] fillClientsArray(TypeClient typeClient, int countClients) {
        return clientActions.fillClientsArray(typeClient, countClients);
    }

    @Override
    public String toString() {
        return String.format("Клиент ЮЛ: %s, ОГРН: %d%n",
                this.getName(), this.ogrn);
    }
}
