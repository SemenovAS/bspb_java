package client;

import java.util.Objects;


public class IndividualClient extends Client implements Comparable<IndividualClient> {
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


    public static ClientActions clientActions = new ClientActionsImpl();


    @Override
    public Client[] fillClientsArray(TypeClient typeClient, int countClients) {
        return clientActions.fillClientsArray(typeClient, countClients);
    }

    @Override
    public String toString() {
        return String.format("Клиент ФЛ: %s %s %s, Пол: %s, Возраст: %d%n",
                lastName, firstName, middleName, sex, age);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        IndividualClient client = (IndividualClient) obj;

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

    @Override
    public int compareTo(IndividualClient o) {
        return Integer.compare(this.age, o.age);
    }


}
