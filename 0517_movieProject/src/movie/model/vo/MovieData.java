package movie.model.vo;

public class MovieData {
	private Movie mv;
	private Genre g;
	public MovieData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieData(Movie mv, Genre g) {
		super();
		this.mv = mv;
		this.g = g;
	}
	public Movie getMv() {
		return mv;
	}
	public void setMv(Movie mv) {
		this.mv = mv;
	}
	public Genre getG() {
		return g;
	}
	public void setG(Genre g) {
		this.g = g;
	}
	
}
