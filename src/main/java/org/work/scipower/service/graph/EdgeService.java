package org.work.scipower.service.graph;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.work.scipower.model.Reference;
import org.work.scipower.model.graph.Edge;

import java.util.Set;

@Slf4j
@Service
public class EdgeService {

    private final Set<Reference> referenceList;
    private final Set<Edge> edgeList;
    private final NodeService nodeService;

    @Autowired
    @SuppressWarnings("unchecked")
    public EdgeService(ApplicationContext context, NodeService nodeService) {
        this.referenceList = (Set<Reference>) context.getBean("referenceList");
        this.edgeList = (Set<Edge>) context.getBean("edgeList");
        this.nodeService = nodeService;
    }

    public boolean calculateEdges() {
        referenceList.parallelStream().forEach(reference -> {
            var documentNode = nodeService.findByTitle(reference.getDocument().getDocumentValues().get("Title"));
            var referenceNode = nodeService.findByTitle(reference.getReference().getDocumentValues().get("Title"));

            if (!documentNode.getLabel().equals(referenceNode.getLabel())) {
                String[] docReferences = reference.getDocument().getDocumentValues().get("References").split(";");

                int referenceWeight = 1;
                for (String docReference : docReferences) {
                    if (reference.getReference().getDocumentValues().get("References").contains(docReference)) {
                        referenceWeight++;
                    }
                }

                edgeList.add(new Edge(documentNode, referenceNode, referenceWeight, 1));
            }
        });

        log.info("Edges: {}", edgeList.size());

        nodeService.calculateNodePlacement();

        return edgeList.isEmpty();
    }

}
