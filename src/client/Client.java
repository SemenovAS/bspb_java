package client;


public abstract class Client {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new RuntimeException("Для клиента не заполнено поле Имя клиента");
        } else
            this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public enum Gender {
        MALE,
        FEMALE
    }


    @Override
    public String toString() {
        return String.format("Клиент: %s, email: %s%n", name, email);
    }


}
