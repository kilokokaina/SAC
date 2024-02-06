package com.mesi.scipower.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Entity(name = "item")
@NoArgsConstructor
public class ItemModel {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long itemId;
    private String itemName;

    public ItemModel(String itemName) {
        this.itemName = itemName;
    }

}
