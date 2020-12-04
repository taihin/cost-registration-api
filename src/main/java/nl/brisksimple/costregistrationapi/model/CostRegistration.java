package nl.brisksimple.costregistrationapi.model;

import javax.persistence.*;

@Entity
@Table(name = "costs")
public class CostRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "number")
    private Integer number;

    public CostRegistration() {

    }

    public CostRegistration(String itemName, String description, Integer price, Integer number) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getNumber() {
        return number;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
