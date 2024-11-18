package org.work.scipower.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.work.scipower.model.graph.Edge;
import org.work.scipower.service.graph.EdgeService;

import java.util.Set;

@RestController
@RequestMapping("api/edge")
public class EdgeAPI {

    private final Set<Edge> edgeList;
    private final EdgeService edgeService;

    @Autowired
    @SuppressWarnings("unchecked")
    public EdgeAPI(ApplicationContext context, EdgeService edgeService) {
        this.edgeList = (Set<Edge>) context.getBean("edgeList");
        this.edgeService = edgeService;
    }

    @GetMapping("get")
    public @ResponseBody ResponseEntity<Set<Edge>> getEdges() {
        return ResponseEntity.ok(edgeList);
    }

    @GetMapping("update")
    public @ResponseBody ResponseEntity<HttpStatus> updateEdges() {
        if (!edgeService.calculateEdges()) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
