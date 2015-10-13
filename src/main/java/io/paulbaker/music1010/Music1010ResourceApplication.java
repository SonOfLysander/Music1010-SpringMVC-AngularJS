package io.paulbaker.music1010;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class Music1010ResourceApplication implements CommandLineRunner {

  @Value("${server.port}")
  private String currentPort;

  public static void main(String[] args) {
    SpringApplication.run(Music1010ResourceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    URI uri = new URI("http://localhost:" + currentPort);
    if (Desktop.isDesktopSupported()) {
      Desktop desktop = Desktop.getDesktop();
      desktop.browse(uri);
    }
    System.out.println(String.format("Point your browser to-> %s", uri));
  }
}
