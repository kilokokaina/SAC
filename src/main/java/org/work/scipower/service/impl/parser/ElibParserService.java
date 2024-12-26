package org.work.scipower.service.impl.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.work.scipower.model.ParserDocument;
import org.work.scipower.service.ParserService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ElibParserService implements ParserService {

    public void getElibRef(String elibHTML) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/elib.txt"))) {
            var elibDoc = Jsoup.parse(elibHTML);

            String pageNum;
            try {
                var links = elibDoc.select("table[bgcolor='#ffffff']")
                        .get(2).select("a[href*='query_results.asp?pagenum=']");
                pageNum = links.getLast().attr("href").split("\\?")[1];
                pageNum = pageNum.split("=")[1];
            } catch (IndexOutOfBoundsException exception) {
                log.error("There is only one page");
                writer.write("There is only one page\n");
                pageNum = "1";
            }

            log.info("Count of pages: {}", pageNum);
            writer.write("Count of pages: " + pageNum + "\n");

            var papers = elibDoc.select("table[bgcolor='#ffffff']")
                    .get(1).select("tr[bgcolor='#f5f5f5']");
            int paperCount = papers.size();

            log.info("Count of papers on one page: {}", paperCount);
            writer.write("Count of papers on one page: " + paperCount + "\n");
            papers.forEach(paper -> {
                var paperInfo = paper.getElementsByTag("td");

                String paperId = paperInfo.get(0).text();
                String paperLink = paperInfo.get(1).getElementsByTag("a").getFirst().attr("href");
                String paperTitle = paperInfo.get(1).getElementsByTag("span").text();

                log.info("{} {} {}", paperId, paperLink, paperTitle);
                try {
                    writer.write(paperId + " " + paperLink + " " + paperTitle + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            writer.flush();

        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
    }

    @Override
    public CompletableFuture<List<ParserDocument>> parseFile(MultipartFile file) {
        return null;
    }
}
