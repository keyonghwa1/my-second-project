package chart;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Crawler {
	ArrayList<chartVO> list = new ArrayList<>();
	
	String Rank; 
	String Song; 
	String Artist; 
	String Album;
	String genre;
		
	public Crawler(int date) {
		// 드라이버 찾기
		String projectPath_chrome 	= System.getProperty("user.dir");
		String chromePath 			= projectPath_chrome + "\\lib\\selenium\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromePath);
		
		WebDriver driver = new ChromeDriver(); // 크롬 드라이버 실행
		
		for(int i = 0; i < 5; i++) {
			
			if( i == 0 ) {
				genre = "all";
			}
			else if ( i == 1 ){
				genre = "ballad";
			}
			else if ( i == 2 ) {
				genre = "dance";
			}		
			else if ( i == 3 ) {
				genre = "hiphop";
			}		
			else if ( i == 4 ) {
				genre = "rock";
			}		
			
			
			driver.get("http://www.mnet.com/chart/Kpop/" + genre + "/" + date);
				
			Document doc = Jsoup.parse( driver.getPageSource() );
				
			Elements div = doc.select("div.MnetMusicList");
			Elements tr = div.select("tr");
			
			System.out.println("장르 : " + genre);
			
			for(int j = 1; j < 11; j++) { 
				Element music = tr.get(j);
				
				Rank		= music.select("span.MMLI_RankNum").text();
				Song		= music.select("a.MMLI_Song").text();
				Artist	= music.select("a.MMLIInfo_Artist").text();
				Album	= music.select("a.MMLIInfo_Album").text();
			
				list.add( new chartVO(genre, Rank, Song, Artist, Album) );
				
				System.out.println(list);

			}
			
			try {
				Thread.sleep(2000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
		
		driver.close(); 
	}
	
}
