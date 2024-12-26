package org.work.scipower.service.impl.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.work.scipower.model.ParserDocument;
import org.work.scipower.service.ParserService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@Qualifier("CSV")
public class CSVParserService implements ParserService {

    private final String[] HEADERS;

    @Autowired
    @SuppressWarnings("unchecked")
    public CSVParserService(ApplicationContext context) {
        this.HEADERS = ((List<String>) context.getBean("HEADERS")).toArray(new String[0]);
    }

    @Async
    @Override
    public CompletableFuture<List<ParserDocument>> parseFile(MultipartFile file) {
        log.info("CSVParser process {} file...", file.getOriginalFilename());

        var parseDocumentList = new ArrayList<ParserDocument>();
        var csvFormat = CSVFormat.Builder.create(CSVFormat.RFC4180).setSkipHeaderRecord(true).setHeader(HEADERS).build();

        try(var parser = new CSVParser(new InputStreamReader(file.getInputStream()), csvFormat)) {
            var csvData = parser.getRecords();
            String[] values;

            for (CSVRecord csv: csvData) {
                var document = new ParserDocument();
                var documentValues = new HashMap<String, String>();

                values = csv.values();
                if (values[3].isEmpty()) continue;

                for (int i = 0; i < values.length; i++) {
                    documentValues.put(HEADERS[i], values[i]);
                }

                document.setDocumentValues(documentValues);
                parseDocumentList.add(document);
            }

        } catch (IOException ex) {
            log.error(ex.getMessage());
        }

        log.info("Process completed: {}; Rows: {}", file.getOriginalFilename(), parseDocumentList.size());

        return CompletableFuture.completedFuture(parseDocumentList);
    }

}
