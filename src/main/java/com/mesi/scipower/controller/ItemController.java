package com.mesi.scipower.controller;

import com.mesi.scipower.pojo.User;
import com.mesi.scipower.service.impl.ItemServiceImpl;
import com.mesi.scipower.service.impl.SwitchControllerServiceImpl;
import com.mesi.scipower.model.ItemModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class ItemController {

    private final ItemServiceImpl itemService;
    private final ApplicationContext applicationContext;
    private final SwitchControllerServiceImpl controllerService;

    @Autowired
    public ItemController(SwitchControllerServiceImpl controllerService,
                          ApplicationContext applicationContext,
                          ItemServiceImpl itemService) {
        this.controllerService = controllerService;
        this.applicationContext = applicationContext;
        this.itemService = itemService;

        log.info("Application Context ID: " + this.applicationContext.getId());
        log.info("add-item is loaded");
    }

    @FXML
    private ListView<String> itemList;

    @FXML
    private TextField itemName;

    @FXML
    protected void initialize() {
        ObservableList<String> items = FXCollections.observableList(
                itemService.findAll().stream().map(ItemModel::getItemName).toList());
        itemList.setItems(items);

        User globalUser = (User) applicationContext.getBean("sessionUser");
        log.info(globalUser.toString());

        log.info("add-item is initialized");
    }

    @FXML
    protected void addItem() {
        String itemName = this.itemName.getText();
        List<String> items = new java.util.ArrayList<>(itemList.getItems().stream().toList());
        items.add(itemName);

        itemList.setItems(FXCollections.observableList(items));

        itemService.save(new ItemModel(itemName));
    }

    @FXML
    protected void deleteItem() throws IOException {
        MultipleSelectionModel<String> selectedItems = itemList.getSelectionModel();
        ItemModel itemModel = itemService.findByName(selectedItems.getSelectedItem());

        List<String> items = new java.util.ArrayList<>(itemList.getItems().stream().toList());
        items.remove(itemModel.getItemName());

        itemList.setItems(FXCollections.observableList(items));

        itemService.delete(itemModel);

        itemName.getScene().getWindow().hide();
        controllerService.switchController(
                "hello-view", applicationContext
        );
    }

}
