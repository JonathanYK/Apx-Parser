package net.cityzen.apx;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Service
public class ApxDataLoader {
  private String path = PropertiesCons.prop.getProperty("jsonPath");
  private String jsonName = PropertiesCons.prop.getProperty("jsonName");

  @Value("${apxDataLoader.url}")
  URL url;

  private static ObjectMapper mapper = getDefaultObjectMapper();

  private static ObjectMapper getDefaultObjectMapper() {
    ObjectMapper defaultObjectMapper = new ObjectMapper();
    return defaultObjectMapper;
  }


  public QuotesResponse load() throws IOException {
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper.readValue(url, QuotesResponse.class);
  }


  public void write(allMarketDatas amd) {
    try {
      File theDir = new File(path);
      if (!theDir.exists()) {
        theDir.mkdirs();
      }
      mapper.writeValue(new File(path.concat("\\"), jsonName), amd);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
