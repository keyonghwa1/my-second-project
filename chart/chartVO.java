package chart;

public class chartVO {
	private String genre;
	private String rank;
	private String Song;
	private String artist;
	private String album;
	
	public chartVO(String genre, String rank, String Song, String artist, String album) {
		this.genre 		= genre;
		this.rank 		= rank;
		this.Song		= Song;
		this.artist		= artist;
		this.album		= album;
	}
	
	public String getGenre() {
		return genre;
	}
	public String getRank() {
		return rank;
	}
	public String getSong() {
		return Song;
	}
	public String getArtist() {
		return artist;
	}
	public String getAlbum() {
		return album;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "genre: "+ genre+ ",rank: " + rank +", Song: " + Song
				+", artist: "+ artist + ", album: "+ album  ;
	}
	
}
