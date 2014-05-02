package tetris;

public class Poly {
	
	private SquareType[][] poly;
	
	/**
	 * 
	 * @param poly
	 */
	public Poly(SquareType[][] poly) {
		this.poly = poly;
	}
	
	/**
	 * 
	 * @return
	 */
	public SquareType[][] getPoly() {
		return poly;
	}
	
	public int getPolyLength() {
		return poly.length;
	}

}
