package org.nachc.cad.tools.vsactoohdsiexample.util.connection;

import org.junit.Test;
import org.nachc.cad.tools.vsactoohdsi.util.auth.VsacToOhdsiAuthProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VsacToOhdsiExampleConnectionPropertiesIntegrationTest {

	@Test
	public void shouldGetProperties() {
		log.info("Starting test...");
		String url = VsacToOhdsiAuthProperties.getJdbcUrl();
		log.info("url: \n" + url);
		log.info("Done.");
	}

}
