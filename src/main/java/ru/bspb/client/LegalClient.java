package ru.bspb.client;

public class LegalClient extends Client implements Comparable<LegalClient> {
    private int ogrn;

    public LegalClient() {
    }

    public LegalClient(String name, int ogrn) {
        super.setName(name);
        this.ogrn = ogrn;
    }

    public int getOgrn() {
        return ogrn;
    }

    public void setOgrn(int ogrn) {
        this.ogrn = ogrn;
    }


    @Override
    public String toString() {
        return String.format("Клиент ЮЛ: %s, ОГРН: %d, eMail: %s%n",
                this.getName(), this.ogrn, this.getEmail());
    }

    @Override
    public int compareTo(LegalClient o) {
        return Integer.compare(this.ogrn, o.ogrn);
    }
}
