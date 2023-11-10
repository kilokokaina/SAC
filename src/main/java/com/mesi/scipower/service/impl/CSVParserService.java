package com.mesi.scipower.service.impl;

import com.mesi.scipower.service.ParserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CSVParserService implements ParserService {

    @Override
    public void parseFile(String format) {
        String infoString = String.format("CSVParser process %s file...", format);

        String[] headerArray = {
                "Authors","Author full names","Author(s) ID","Title","Year",
                "Source title","Volume","Issue","Art. No.","Page start","Page end","Page count",
                "Cited by","DOI","Link","Affiliations","Authors with affiliations","Abstract",
                "Author Keywords","Index Keywords","Molecular Sequence Numbers","Chemicals/CAS",
                "Tradenames","Manufacturers","Funding Details","Funding Texts","References",
                "Correspondence Address","Editors","Publisher","Sponsors","Conference name",
                "Conference date","Conference location","Conference code","ISSN","ISBN","CODEN",
                "PubMed ID","Language of Original Document","Abbreviated Source Title",
                "Document Type","Publication Stage","Open Access","Source","EID"
        };

        CSVFormat csvFormat = CSVFormat.Builder
                .create(CSVFormat.RFC4180).setHeader(headerArray).build();

        try(CSVParser parser = new CSVParser(
                new FileReader("src/main/resources/test_files/ar2001.csv"),
                csvFormat)) {

            List<CSVRecord> csvData = parser.getRecords();
            log.info(parser.toString());
            csvData.forEach(csv -> {
                log.info("------------Next_item------------");
                for (Map.Entry<String, String> entry : csv.toMap().entrySet()) {
                    log.info(String.format("%s: %s",
                            entry.getKey(), entry.getValue())
                    );
                }
            });

        } catch (IOException ex) {
            log.error(ex.getMessage());
        }

        log.info("Process completed");
    }

}
