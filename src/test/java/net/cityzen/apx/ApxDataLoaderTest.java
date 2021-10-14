package net.cityzen.apx;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ApxDataLoaderTest {

  private ApxDataLoader sut = new ApxDataLoader();
  private QuotesResponse response = new QuotesResponse();
  private allMarketDatas amd = new allMarketDatas();
  private static final long oldestDate = Long.parseLong(PropertiesCons.prop.getProperty("oldestDate"));
  private SimpleDateFormat sdf = new SimpleDateFormat(PropertiesCons.prop.getProperty("dateFormat"));

  @Test
  public void parseOriginalJson() {
    try {
      sut.url = getClass().getResource("apx-data.json");
      response = sut.load();

      assertNotNull("There is no correct quotes!", response.getQuotes());
      assertNotEquals(true,response.getQuotes().isEmpty());

    } catch (IOException ex) {
        ex.printStackTrace();
    }
  }

  @Test
  public void JacksonParsingValidation() {
    try {

      for (Quote currQuote : response.getQuotes()) {

        // QuoteValues key validation:
        for (QuoteValue currQuoteVal : currQuote.getQuoteValues()) {
          assertNotNull("There is a null tLabel!", currQuoteVal.getTLabel());
          assertTrue("There is a 0 value!", currQuoteVal.getValue()>0);
        }
        //values validation:
        assertEquals("APX Power NL Hourly", currQuote.getMarket());
        assertTrue("provided date earlier than the available oldest date!", currQuote.getDateApplied() > oldestDate);
        assertTrue("provided date later than current date!", currQuote.getDateApplied() < System.currentTimeMillis());
        assertTrue("Net Volume isn't correct!", currQuote.getQuoteByTlabel("Net Volume").getValue() > 0);
        assertTrue("Price isn't correct!", currQuote.getQuoteByTlabel("Price").getValue() > 0);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Creating dedicated POJO for holding the relevant data, then parsing the POJO using Jackson to a JSON file.
  public void createDedicatedPojo() {
    try {
      for (int i = 0; i < response.getQuotes().size(); i++) {
        Quote currQuote = response.getQuotes().get(i);

        MarketDataPerHour mdh = new MarketDataPerHour();
        mdh.setHour((int) currQuote.getQuoteByTlabel("Hour").getValue());

        marketTime currMarketHour = new marketTime(currQuote.getMarket(), currQuote.getDateApplied(),
                sdf.format(new Date(currQuote.getDateApplied())), currQuote.getQuoteByTlabel("Net Volume").getValue(),
                currQuote.getQuoteByTlabel("Price").getValue());
        mdh.addMarketTimeToList(currMarketHour);
        amd.addMarketDataToallDatas(mdh);
      }
      sut.write(amd);
    }
    catch (Exception io) {
      io.printStackTrace();
    }
  }
}

