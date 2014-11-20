package com.waycoolsearch.tutorials.velocity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.junit.Test;

public class VelocityUtilsTest {
	private static final Logger log = Logger.getLogger(VelocityUtilsTest.class);

	@Test
	public void testGenerateList() throws Exception {
		VelocityContext context = new VelocityContext();
		List<String> list = Arrays.asList("JAXB", "JAXP", "Flex", "Velocity",
				"Annotation", "Hibernate");
		context.put("list", list);
		String output = VelocityUtils.applyTemplate(
				"src/test/templates/list.vm", context);
		log.info(output);
		for (String value : list) {
			Assert.assertTrue(output.contains(value));
		}
	}

	@Test
	public void testIncludeOutput() throws Exception {
		VelocityContext context = new VelocityContext();
		context.put("title", "Velocity tutorials");
		context.put("welcome", "Welcome to Velocity tutorials!");
		Map<String, String> tutorial = new HashMap<String, String>();
		context.put("tutorial", tutorial);
		tutorial.put("url", "http://tutorials.waycoolsearch.com/java/");
		tutorial.put("name", "java tutorial");

		List<String> list = Arrays.asList("JAXB", "JAXP", "Flex", "Velocity",
				"Annotation", "Hibernate");
		context.put("list", list);

		String output1 = VelocityUtils.applyTemplate(
				"src/test/templates/test.vm", context);
		String output2 = VelocityUtils.applyTemplate(
				"src/test/templates/list.vm", context);
		log.info(output1);
		log.info(output2);
		Assert.assertTrue(output1.contains(output2));
	}

	/**
	 * The following returns different results if list.vm contains dynamic contents like variables.
	 * #include("src/test/templates/list.vm")
	 * #parse("src/test/templates/list.vm")
	 * @throws Exception
	 */

	@Test
	public void testIncludeVsParse() throws Exception {
		VelocityContext context = new VelocityContext();
		context.put("title", "Velocity tutorials");
		context.put("welcome", "Welcome to Velocity tutorials!");
		Map<String, String> tutorial = new HashMap<String, String>();
		context.put("tutorial", tutorial);
		tutorial.put("url", "http://tutorials.waycoolsearch.com/java/");
		tutorial.put("name", "java tutorial");

		List<String> list = Arrays.asList("JAXB", "JAXP", "Flex", "Velocity",
				"Annotation", "Hibernate");
		context.put("list", list);

		String output1 = VelocityUtils.applyTemplate(
				"src/test/templates/test.vm", context);
		String output2 = VelocityUtils.applyTemplate(
				"src/test/templates/testInclude.vm", context);

		String output3 = VelocityUtils.applyTemplate(
				"src/test/templates/list.vm", context);
		log.info(output1);
		log.info(output2);
		log.info(output3);
		Assert.assertTrue(output1.contains(output3));
		Assert.assertFalse(output2.contains(output3));
	}
}
