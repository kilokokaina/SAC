package org.work.scipower.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.work.scipower.model.ParserDocument;
import org.work.scipower.model.Reference;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataService {

    private final List<ParserDocument> dataList;
    private final Set<Reference> referenceList;

    @Autowired
    @SuppressWarnings("unchecked")
    public DataService(ApplicationContext context) {
        this.dataList = (CopyOnWriteArrayList<ParserDocument>) context.getBean("dataList");
        this.referenceList = (Set<Reference>) context.getBean("referenceList");
    }

    private ParserDocument findByTitle(String title) {
        ParserDocument result = null;
        for (var document : dataList) {
            if (document.getDocumentValues().get("Title").equals(title)) {
                result = document;
            }
        }

        return result;
    }

    public boolean findReferences() {
        var documentTitles = dataList.stream().map(document -> document.getDocumentValues().get("Title"))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        long startTime = System.currentTimeMillis();

        dataList.parallelStream().forEach(document -> {
            for (String reference : document.getDocumentValues().get("References").split("; ")) {
                var pageMatcher = Pattern.compile(", pp. \\d+-\\d+").matcher(reference);
                reference = pageMatcher.replaceAll("");

                var yearMatcher = Pattern.compile(", \\(\\d{4}\\)").matcher(reference);
                reference = yearMatcher.replaceAll("");

                var nameMatcher = Pattern.compile("\\D+ (\\D{1,2}\\.)+, ").matcher(reference);
                reference = nameMatcher.replaceAll("");

                var numbersMatcher = Pattern.compile(", \\d+").matcher(reference);
                reference = numbersMatcher.replaceAll("");

                String[] refComponent = reference.split(", ");

                if (documentTitles.contains(refComponent[0])) {
                    var referenceDocument = this.findByTitle(refComponent[0]);
                    if (referenceDocument != null) {
                        referenceList.add(new Reference(document, referenceDocument));
                    }
                }
            }
        });

        long stopTime = System.currentTimeMillis();

        log.info("Reference process: {} ms", stopTime - startTime);
        log.info("References: {}", referenceList.size());

        return referenceList.isEmpty();
    }

    public Set<String> getKeyWordList() {
        var documentKeyWords = dataList.stream().map(document -> document.getDocumentValues().get("Author Keywords"))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        var keyWordsSet = new CopyOnWriteArraySet<String>();

        long startTime = System.currentTimeMillis();

        documentKeyWords.parallelStream().forEach(keyWordString -> {
            String[] keyWords = keyWordString.split("; ");
            for (String keyWord : keyWords) {
                if (keyWord.length() > 1) keyWordsSet.add(keyWord.toLowerCase());
            }
        });

        long stopTime = System.currentTimeMillis();
        log.info("KW process: {} ms", stopTime - startTime);

        return keyWordsSet;
    }

}
