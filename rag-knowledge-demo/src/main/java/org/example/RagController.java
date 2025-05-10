package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class RagController {

    private final RagService cloudRagService;

    public RagController(RagService cloudRagService) {
        this.cloudRagService = cloudRagService;
    }

    @GetMapping("/knowledge/importDocument")
    public void importDocument() {
        cloudRagService.importDocuments();
    }

    @GetMapping("/knowledge/generate")
    public Flux<String> generate(@RequestParam(value = "message",
            defaultValue = "你好，请问你的知识库文档主要是关于什么内容的?") String message) {
        return cloudRagService.retrieve(message).map(x -> x.getResult().getOutput().getText());
    }
}
