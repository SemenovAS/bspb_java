package client;

import java.util.Objects;


public class IndividualClient extends Client implements Comparable<IndividualClient> {
    private String lastName;
    private String firstName;
    private String middleName;
    private int age;
    private Gender gender;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new RuntimeException("Для клиента не заполнено поле Фамилия");
        } else
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("Клиент ФЛ: %s %s %s, Пол: %s, Возраст: %d, eMail: %s %n",
                lastName, firstName, middleName, gender, age, getEmail());
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        IndividualClient client = (IndividualClient) obj;

        return
                this.getName().equals(client.getName())
                        && this.gender == client.gender
                        && this.age == client.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, middleName, age, gender);
    }

    @Override
    public int compareTo(IndividualClient o) {
        return Integer.compare(this.age, o.age);
    }


    public enum Gender {
        MALE,
        FEMALE
    }
}
