package movie.model.vo;

public class Genre {
	private int genreNo;
	private String genreCate;
	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Genre(int genreNo, String genreCate) {
		super();
		this.genreNo = genreNo;
		this.genreCate = genreCate;
	}
	public int getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(int genreNo) {
		this.genreNo = genreNo;
	}
	public String getGenreCate() {
		return genreCate;
	}
	public void setGenreCate(String genreCate) {
		this.genreCate = genreCate;
	}
	
}
