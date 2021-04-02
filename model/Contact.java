package model;

import java.util.Objects;

public class Contact {
    private int id;
    private String telefon;
    private int PersoanaId;

    public Contact(int id, String telefon, int persoanaId) {
        this.id = id;
        this.telefon = telefon;
        PersoanaId = persoanaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getPersoanaId() {
        return PersoanaId;
    }

    public void setPersoanaId(int persoanaId) {
        PersoanaId = persoanaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", telefon='" + telefon + '\'' +
                ", PersoanaId=" + PersoanaId +
                '}';
    }
}
