package org.example;


public class Main {
   public static void main (String[] args) {


      try {
         HTTPClient aClient = new HTTPClient();
         aClient.validateUrl();
         System.out.println("Valid URLS that have successfully connected :");
         System.out.println(aClient.succeededURLS);
         System.out.println("\n--------------\n\n");
         System.out.println("Broken URLS that did not successfully connect :");
         System.out.println(aClient.failedURLS);
      } catch (Exception e) {
         System.out.print(e.getMessage());
      }
   }
}