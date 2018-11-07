package dataBaseConnect;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Column(name = "number")
    private int number;

    @Column(name = "dateFrom")
    private Date dateFrom;

    @Column(name = "dateTill")
    private Date dateTill;

    @Column(name = "name")
    @Size(max = 50)
    private String name;

    @Column(name = "cost")
    private int cost;

    @Column(name = "dateregistration")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date dateRegistration;

    public Orders(){}

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTill() {
        return dateTill;
    }

    public void setDateTill(Date dateTill) {
        this.dateTill = dateTill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Orders(int number, Date dateFrom, Date dateTill, @Size(max = 50) String name, int cost) {
        this.number = number;
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
        this.name = name;
        this.cost = cost;
    }
}
