package org.work.scipower.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.work.scipower.model.graph.Node;
import org.work.scipower.service.graph.NodeService;

import java.util.Set;

@RestController
@RequestMapping("api/node")
public class NodeAPI {

    private final Set<Node> nodeList;
    private final NodeService nodeService;

    @Autowired
    @SuppressWarnings("unchecked")
    public NodeAPI(ApplicationContext context, NodeService nodeService) {
        this.nodeList = (Set<Node>) context.getBean("nodeList");
        this.nodeService = nodeService;
    }

    @GetMapping("get")
    public @ResponseBody ResponseEntity<Set<Node>> getNodes() {
        return ResponseEntity.ok(nodeList);
    }

    @GetMapping("update")
    public @ResponseBody ResponseEntity<HttpStatus> updateNodes() {
        if (!nodeService.setNodes()) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
