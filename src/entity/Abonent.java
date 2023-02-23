package entity;

public class Abonent extends Entity{

    private Long id_phonebook;
    private String name;
    private Integer phone;

    public Abonent(Long id_phonebook, String name, Integer phone) {
        this.id_phonebook = id_phonebook;
        this.name = name;
        this.phone = phone;
    }

    public Abonent(String name, Integer phone) {
        this.name = name;
        this.phone = phone;
    }

    public Abonent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Long getId_phonebook() {
        return id_phonebook;
    }

    public void setId_phonebook(Long id_phonebook) {
        this.id_phonebook = id_phonebook;
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "id_phonebook=" + id_phonebook +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                '}';
    }
}
