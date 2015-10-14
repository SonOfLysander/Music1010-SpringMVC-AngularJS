package io.paulbaker.music1010;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class Music1010ResourceApplication implements CommandLineRunner {

  private Logger logger = Logger.getLogger(this.getClass());

//  @Autowired
//  private Environment environment;
  @Value("${server.port}")
  private String port;

  public static void main(String[] args) {
    SpringApplication.run(Music1010ResourceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    URI uri = new URI("http://localhost:" + port);//environment.getProperty("local.server.port"));
    if (Desktop.isDesktopSupported()) {
      Desktop desktop = Desktop.getDesktop();
      desktop.browse(uri);
    }
    logger.info(String.format("Point your browser to-> %s", uri));
  }
}
