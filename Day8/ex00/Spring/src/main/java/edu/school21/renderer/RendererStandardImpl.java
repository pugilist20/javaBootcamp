package edu.school21.renderer;

import edu.school21.preprocessor.PreProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RendererStandardImpl implements Renderer{
    private PreProcessor preProcessor;

    @Autowired
    public RendererStandardImpl(@Qualifier("preProcessorToUpperImpl") PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public String print(String string) {
        return preProcessor.changeCase(string);
    }
}
