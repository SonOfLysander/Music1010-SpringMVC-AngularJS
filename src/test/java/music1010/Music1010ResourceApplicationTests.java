package music1010;

import io.paulbaker.music1010.Music1010ResourceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Music1010ResourceApplication.class)
@WebAppConfiguration
public class Music1010ResourceApplicationTests {

  @Test
  public void contextLoads() {
  }

}
