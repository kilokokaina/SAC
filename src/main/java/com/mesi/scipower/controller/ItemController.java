package com.mesi.scipower.controller;

import com.mesi.scipower.model.ParseDocument;
import com.mesi.scipower.service.impl.SwitchControllerServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Slf4j
@Component
public class ItemController {

    private final ApplicationContext applicationContext;
    private final SwitchControllerServiceImpl controllerService;

    @Autowired
    public ItemController(SwitchControllerServiceImpl controllerService,
                          ApplicationContext applicationContext) {
        this.controllerService = controllerService;
        this.applicationContext = applicationContext;

        log.info("Application Context ID: " + this.applicationContext.getId());
        log.info("add-item is loaded");
    }

    private ObservableList<ParseDocument> documentData = FXCollections.observableArrayList();

    @FXML
    private TableView<ParseDocument> documentTable;

    @FXML
    protected void initialize() {
        var parsedDocuments = (List<ParseDocument>) applicationContext.getBean("parsedDocuments");

        Field[] fields = ParseDocument.class.getDeclaredFields();
        for (Field field : fields) {
            TableColumn<ParseDocument, String> column = new TableColumn<>();
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            documentTable.getColumns().add(column);
        }

        documentTable.setOnMouseClicked(mouseEvent -> {
            SelectionModel<ParseDocument> selectionModel = documentTable.getSelectionModel();
            log.info(selectionModel.getSelectedItem().toString());
        });

        documentData.addAll(parsedDocuments);
        documentTable.setItems(documentData);
    }

    @FXML
    protected void previousPage() throws IOException {
        documentTable.getScene().getWindow().hide();
        controllerService.switchController(
                "hello-view", applicationContext
        );
    }

}
