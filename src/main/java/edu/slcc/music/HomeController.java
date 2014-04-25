package edu.slcc.music;

import java.io.BufferedReader;
import java.io.File;
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
		logger.info("/");
		return "home";
	}

	@RequestMapping(value = "/question-answers/mod", method = RequestMethod.GET)
	public @ResponseBody String getModfiedDate() {
		return "";
	}

	@RequestMapping(value = "/qa", method = RequestMethod.GET)
	public @ResponseBody Map<String, List<String>> getAndParseCsvToJson(
			Locale locale, Model model) {
		logger.info("/question-answers");
		Map<String, List<String>> questionsMap = new HashMap<String, List<String>>();
		try (InputStream inputStream = servletContext
				.getResourceAsStream("/WEB-INF/csv/question-answers.csv")) {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));
			String line = "";
			int lineNumber = 1;
			while ((line = bufferedReader.readLine()) != null) {
				logger.info("Parsing line " + lineNumber++ + ": " + line);
				String[] data = line.split(";");
				if (data.length > 0) {
					logger.info("Question:" + data[0]);
					List<String> answers = new ArrayList<String>();
					for (int i = 1; i < data.length; i++) {
						answers.add(data[i]);
						logger.info("Adding answer: " + data[i]);
					}
					if (questionsMap.containsKey(data[0])) {
						questionsMap.get(data[0]).addAll(answers);
					} else {
						questionsMap.put(data[0], answers);
					}
				} else {
					logger.info("Line too short, only " + data.length
							+ " elements.");
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
		}
		return questionsMap;
	}

}
