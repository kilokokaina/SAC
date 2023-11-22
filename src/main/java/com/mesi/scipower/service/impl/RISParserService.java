package com.mesi.scipower.service.impl;

import com.mesi.scipower.service.ParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Qualifier("RIS")
public class RISParserService implements ParserService {

    @Override
    public void parseFile(String fileName) {
        log.info(String.format("Parsing RIS file %s ...", fileName));
    }

}
