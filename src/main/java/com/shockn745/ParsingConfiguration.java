package com.shockn745;

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
        return new PegDownProcessor(Extensions.FENCED_CODE_BLOCKS);
    }

}