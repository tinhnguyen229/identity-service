package com.devteria.identity_service.base.exportDocument.template;


import com.devteria.identity_service.base.exportDocument.constants.Model;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TemplateStrategyFactory {
    private final List<TemplateStrategy> strategies;
    private Map<String, TemplateStrategy> strategyMap;

    @PostConstruct
    public void init() {
        strategyMap = strategies.stream()
                .collect(Collectors.toMap(templateStrategy -> templateStrategy.getKey(), Function.identity()));
    }

    public TemplateStrategy getTemplateStrategy(Model resModel, String templateType) {
        String key = resModel.name() + "_" + templateType;
        TemplateStrategy strategy = strategyMap.get(key);
        if (strategy == null) {
            throw new RuntimeException("Not found strategy");
        }
        return strategy;
    }
}
