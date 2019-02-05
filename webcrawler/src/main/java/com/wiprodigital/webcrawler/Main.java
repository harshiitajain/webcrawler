package com.wiprodigital.webcrawler;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

        new WebCrawler().getPageLinks("https://wiprodigital.com", "C:/Users/harsh/Documents/wiproDigitalwebcrawler.txt");
        System.out.println("---------------------------------------------------------------------------------------------------");
        new WebCrawler().getPageLinks("https://www.wipro.com", "C:/Users/harsh/Documents/wiprowebcrawler.txt");
        
	}

}
