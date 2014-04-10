package edu.slcc.music;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private @Autowired ServletContext servletContext;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Page Requested");
		logger.info("Page Returned");
		return "home";
	}

	@RequestMapping(value = "/question-answers", method = RequestMethod.GET)
	public @ResponseBody Map<String, List<String>> getAndParseCsvToJson(
			Locale locale, Model model) {
		logger.info("JSON Requested");
		Map<String, List<String>> questionsMap = new HashMap<String, List<String>>();
		try (InputStream inputStream = servletContext
				.getResourceAsStream("/WEB-INF/csv/question-answers.csv")) {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				String[] data = line.split(";");
				if (data.length < 2) {
					throw new IOException(
							"Didn't read enough data from file line.");
				}
				List<String> answers = new ArrayList<String>();
				for (int i = 1; i < data.length; i++) {
					answers.add(data[i]);
				}
				questionsMap.put(data[0], answers);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		logger.info("JSON Returned");
		return questionsMap;
	}

}
