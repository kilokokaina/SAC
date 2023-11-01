package com.mesi.scipower.service.impl;

import com.mesi.scipower.service.ParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CSVParserService implements ParserService {

    @Override
    public void parseFile(String format) {
        String infoString = String.format("CSVParser process %s file...", format);
        try {
            log.info(infoString);
            Thread.sleep(2_000);
            log.info("Process completed");
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

}
