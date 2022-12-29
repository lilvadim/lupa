package ru.nsu.lupa.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import ru.nsu.lupa.MatchGraph;
import ru.nsu.lupa.NameProcessor;
import ru.nsu.lupa.NameProcessorKt;
import ru.nsu.lupa.ResourceManager;
import ru.nsu.lupa.res.GitHubSearch;
import ru.nsu.lupa.res.VkSearch;

import java.util.List;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();
        bind(MatchGraph.class).in(Singleton.class);
    }

    @Singleton
    @Provides
    NameProcessor nameProcessor() {
        return NameProcessorKt.simpleNameProcessor();
    }

    @Singleton
    @Provides
    ResourceManager resources(VkSearch vkSearch, GitHubSearch gitHubSearch) {
        var list = List.of(vkSearch, gitHubSearch);
        return new ResourceManager(list);
    }
}
