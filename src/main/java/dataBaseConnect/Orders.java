package dataBaseConnect;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "orders")
public class Orders {
    @Column(name = "number")
    private int number;

    @Column(name = "dateFrom")
    private String dateFrom;

    @Column(name = "dateTill")
    private String dateTill;

    @Column(name = "name")
    @Size(max = 50)
    private String name;

    @Column(name = "cost")
    private int cost;

    @Column(name = "clean")
    private String clean;

    @Column(name = "breakfast")
    private String breakfast;

    @Column(name = "dateregistration")
    private String dateRegistration;

    public Orders(){}

    public String getClean() {
        return clean;
    }

    public void setClean(String clean) {
        this.clean = clean;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTill() {
        return dateTill;
    }

    public void setDateTill(String dateTill) {
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

    public Orders(int number, String dateFrom, String dateTill, String name, int cost, String clean, String breakfast, String dateRegistration) {
        this.number = number;
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
        this.name = name;
        this.cost = cost;
        this.clean = clean;
        this.breakfast = breakfast;
        this.dateRegistration = dateRegistration;
    }
}
