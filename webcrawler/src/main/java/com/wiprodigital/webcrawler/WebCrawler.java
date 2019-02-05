package com.wiprodigital.webcrawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	private HashSet<String> links;

    public WebCrawler() {
        links = new HashSet<String>();
}
    
    public void getPageLinks(String URL, String filepath) throws IOException {
    	String baseURL = URL;
        //Checks if already crawled the URLs 
    	        if (!links.contains(URL)) {
            try {
                //1. If not add it to the index
                if (links.add(URL)) {
                    System.out.println(URL);
                   
                    //iterate over the HashSet and store the links in an output file
                 BufferedWriter outputWriter = new BufferedWriter(new FileWriter(filepath));
                 Iterator<String> itr=links.iterator();  
                 while(itr.hasNext()){  
                 	outputWriter.write(itr.next()+""); 
                 	outputWriter.newLine();
                 }
            	  outputWriter.flush();  
            	  outputWriter.close(); 
                }

                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL).get();
                //3. Parse the HTML code to extract links to other URLs
                Elements linksOnPage = document.select("a[href]");

                //5. For each extracted URL... go back to Step 4.
                for (Element page : linksOnPage) {
                	String href = page.absUrl("href");
                	if(href.startsWith(baseURL))
                		getPageLinks(page.absUrl("href"), filepath);
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
    
}    
