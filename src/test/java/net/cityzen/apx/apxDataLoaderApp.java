package net.cityzen.apx;

public class apxDataLoaderApp {

    public static void main(String[] args) {
       try {
           ApxDataLoaderTest adl = new ApxDataLoaderTest();
           adl.parseOriginalJson();
           adl.JacksonParsingValidation();
           adl.createDedicatedPojo();
       }
       catch (Exception e) {
           e.printStackTrace();
       }

    }
}
