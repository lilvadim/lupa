package ru.nsu.lupa.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import ru.nsu.lupa.*;
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
    ResourceManager resources(Configuration configuration) {
        var list = List.of(
                new VkSearch(configuration.getParameters())
        );
        return new ResourceManager(list);
    }
}
