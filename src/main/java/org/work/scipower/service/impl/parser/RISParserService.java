package org.work.scipower.service.impl.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.work.scipower.model.ParserDocument;
import org.work.scipower.service.ParserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@Qualifier("RIS")
public class RISParserService implements ParserService {

    @Async
    @Override
    public CompletableFuture<List<ParserDocument>> parseFile(MultipartFile file) {
        var parseDocumentList = new ArrayList<ParserDocument>();
        log.info("RISParser process {} file...", file.getOriginalFilename());

        try (var reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            var document = new ParserDocument();
            var documentValues = new HashMap<String, String>();

            String line;
            while((line = reader.readLine()) != null) {
                if (line.contains("AB  - ")) {
                    documentValues.put("Abstract", line.split("AB {2}- ")[1]);
                }
                if (line.contains("AD  - ")) {
                    String corrAddress = Objects.nonNull(document.getDocumentValues()) ? document.getDocumentValues().get("Correspondence Address") : "";
                    documentValues.put("Correspondence Address", corrAddress + line.split("AD {2}- ")[1] + "; ");
                }
                if (line.contains("AU  - ")) {
                    String docAuthors = Objects.nonNull(document.getDocumentValues()) ? (document.getDocumentValues().get("Authors")) : "";
                    documentValues.put("Authors", docAuthors + line.split("AU {2}- ")[1] + "; ");
                }
                if (line.contains("DB  - ")) {
                    documentValues.put("Source", line.split("DB {2}- ")[1]);
                }
                if (line.contains("DO  - ")) {
                    documentValues.put("DOI", line.split("DO {2}- ")[1]);
                }
                if (line.contains("SP  - ")) {
                    documentValues.put("Page start", line.split("SP {2}- ")[1]);
                }
                if (line.contains("EP  - ")) {
                    documentValues.put("Page end", line.split("EP {2}- ")[1]);
                }
                if (line.contains("IS  - ")) {
                    documentValues.put("Issue", line.split("IS {2}- ")[1]);
                }
                if (line.contains("KW  - ")) {
                    String corrAddress = Objects.nonNull(document.getDocumentValues()) ? document.getDocumentValues().get("Author Keywords") : "";
                    documentValues.put("Author Keywords", corrAddress + line.split("KW {2}- ")[1]);
                }
                if (line.contains("LA  - ")) {
                    documentValues.put("Language of Original Document", line.split("LA {2}- ")[1]);
                }
                if (line.contains("M3  - ")) {
                    documentValues.put("Document Type", line.split("M3 {2}- ")[1]);
                }
                if (line.contains("PB  - ")) {
                    documentValues.put("Publisher", line.split("PB {2}- ")[1]);
                }
                if (line.contains("PY  - ")) {
                    documentValues.put("Year", line.split("PY {2}- ")[1]);
                }
                if (line.contains("SN  - ")) {
                    documentValues.put("ISSN", line.split("SN {2}- ")[1]);
                }
                if (line.contains("ST  - ")) {
                    documentValues.put("Abbreviated Source Title", line.split("ST {2}- ")[1]);
                }
                if (line.contains("TI  - ")) {
                    documentValues.put("Title", line.split("TI {2}- ")[1]);
                }
                if (line.contains("UR  - ")) {
                    documentValues.put("Link", line.split("UR {2}- ")[1]);
                }
                if (line.contains("VL  - ")) {
                    documentValues.put("Volume", line.split("VL {2}- ")[1]);
                }
                if (line.contains("ER  -")) {
                    document.setDocumentValues(documentValues);
                    parseDocumentList.add(document);

                    document = new ParserDocument();
                }
            }
        } catch (IOException exception) {
            log.error(exception.getMessage());
        }

        log.info("Process completed: {}", file.getOriginalFilename());

        return CompletableFuture.completedFuture(parseDocumentList);
    }

}
