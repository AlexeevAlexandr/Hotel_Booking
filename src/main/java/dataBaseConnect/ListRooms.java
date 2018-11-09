package dataBaseConnect;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "list_rooms")
public class ListRooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "number")
    private int number;

    @Column(name = "category")
    @Size(max = 50)
    private String category;

    @Column(name = "price")
    private int price;

    public ListRooms() {
    }

    public ListRooms(String category, int price) {
        this.category = category;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
