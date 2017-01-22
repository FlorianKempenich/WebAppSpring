package com.shockn745;

import com.shockn745.domain.application.driven.MarkdownParser;
import com.shockn745.parsing.PegdownBasedParser;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kempenich Florian
 */
@Configuration
public class ParsingConfiguration {

    @Bean
    public PegDownProcessor getPegdownProcessor() {
        return new PegDownProcessor( Extensions.FENCED_CODE_BLOCKS
                |Extensions.STRIKETHROUGH
                |Extensions.EXTANCHORLINKS
                |Extensions.TABLES
                |Extensions.SMARTS
        );
    }

    @Bean
    public MarkdownParser getMarkdownParser(PegDownProcessor processor) {
        return new PegdownBasedParser(processor);
    }
}