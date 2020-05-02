package org.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HTTPClient {


   private boolean verifyUrl(String url) {

//      The pattern can be changed according to the needs:

      String urlRegex = "^(http|https)://[-a-zA-Z0-9+&@#/%?=~_|,!:.;]*[-a-zA-Z0-9+@#/%=&_|]";

      Pattern pattern = Pattern.compile(urlRegex);
      Matcher m = pattern.matcher(url);
      if (m.matches()) {
         return true;
      } else {
         return false;
      }
   }

   public String failedURLS ="";
   public String succeededURLS ="";
   public String incorrectURLS = "";

   public void validateUrl() throws Exception {

//      The source of the links can be changed to a remote repository or database where those links are stored:

      Path filePath = Paths.get("src/url-list.txt");

      List<String> myURLArrayList = Files.readAllLines(filePath);
      myURLArrayList.forEach((String url) -> {
         if (verifyUrl(url)) {
            HttpURLConnection myConnection = null;
            try {
               URL myURL = new URL(url);
               myConnection = (HttpURLConnection) myURL.openConnection();
            } catch (MalformedURLException e) {
               e.printStackTrace();
            } catch (IOException e) {
               e.printStackTrace();
            }
            try {
               if (myConnection.getResponseCode() == URLStatus.HTTP_OK.getStatusCode()) {
                  succeededURLS = succeededURLS + "\n" + url + "****** Status message is : "
                          + URLStatus.getStatusMessageForStatusCode(myConnection.getResponseCode());
               } else {
                  failedURLS = failedURLS + "\n" + url + "****** Status message is : "
                          + URLStatus.getStatusMessageForStatusCode(myConnection.getResponseCode());
               }
            } catch (IOException e) {
               e.printStackTrace();
            }
         }else {
                incorrectURLS += "\n" + url;
              }
          });
        }
}
