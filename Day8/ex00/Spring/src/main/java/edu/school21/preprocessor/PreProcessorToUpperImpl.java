package edu.school21.preprocessor;

import org.springframework.stereotype.Component;

@Component
public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String changeCase(String string) {
        return string.toUpperCase();
    }
}
