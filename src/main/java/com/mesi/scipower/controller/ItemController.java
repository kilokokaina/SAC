package com.mesi.scipower.controller;

import com.mesi.scipower.model.ParseDocument;
import com.mesi.scipower.service.impl.SwitchControllerServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @FXML
    private TableView<ParseDocument> documentTable;
    private final ObservableList<ParseDocument> tableData = FXCollections.observableArrayList();

    @FXML
    protected void initialize() {
        var parsedDocuments = (List<ParseDocument>) applicationContext.getBean("parsedDocuments");
        documentTable.setTableMenuButtonVisible(true);

        Field[] fields = parsedDocuments.get(0).getClass().getDeclaredFields();
        for (Field field : fields) {
            TableColumn<ParseDocument, String> tableColumn = new TableColumn<>(field.getName());
            tableColumn.setPrefWidth(100.0);
            documentTable.getColumns().add(tableColumn);
        }

        documentTable.setOnMouseClicked(mouseEvent -> {
            SelectionModel<ParseDocument> selectionModel = documentTable.getSelectionModel();
            log.info(selectionModel.getSelectedItem().toString());
        });

        tableData.addAll(parsedDocuments);
        documentTable.setItems(tableData);
    }

    @FXML
    protected void previousPage() throws IOException {
        documentTable.getScene().getWindow().hide();
        controllerService.switchController(
                "hello-view", applicationContext
        );
    }

}
