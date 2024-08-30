package edu.school21.preprocessor;

import org.springframework.stereotype.Component;

@Component
public class PreProcessorToLower implements PreProcessor {
    @Override
    public String changeCase(String string) {
        return string.toLowerCase();
    }
}
