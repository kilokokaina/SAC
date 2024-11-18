package org.work.scipower.service;

import org.springframework.web.multipart.MultipartFile;
import org.work.scipower.model.ParseDocument;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ParserService {

    CompletableFuture<List<ParseDocument>> parseFile(MultipartFile file);

}
