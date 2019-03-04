package com.example.demo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfiguration {
	/*
	@Bean
	public Mapper mapper(@Value(value = "classpath*:mappings/*mappings.xml") Resource[] resourceArray) throws IOException {
	    List<String> mappingFileUrlList = new ArrayList<>();
	    for (Resource resource : resourceArray) {
	        mappingFileUrlList.add(String.valueOf(resource.getURL()));
	    }
	    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
	    dozerBeanMapper.setMappingFiles(mappingFileUrlList);
	    return dozerBeanMapper;
	}*/
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}