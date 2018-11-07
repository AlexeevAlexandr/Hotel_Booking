package dataBaseConnect;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "list_rooms")
public class DataBaseConnect {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "number")
    public int number;

    @Column(name = "category")
    @Size(max = 50)
    public String category;

    @Column(name = "price")
    @Size(max = 50)
    public String price;

    public DataBaseConnect() {
    }

    public DataBaseConnect(String category, String price) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
