package tetris;

public class Poly {
	
	private SquareType[][] poly;
	
	/**
	 * Create a new poly
	 * @param poly
	 */
	public Poly(SquareType[][] poly) {
		this.poly = poly;
	}
	
	/**
	 * Returns a representation of the Poly
	 * @return
	 */
	public SquareType[][] getPoly() {
		return poly;
	}
	
	public int getPolyLength() {
		return poly.length;
	}
	
	
	 public Poly rotateRight(){
		    Poly newPoly = new Poly(new SquareType[this.getPolyLength()][this.getPolyLength()]);	    
		    for (int r = 0; r < this.getPolyLength(); r++) {
		        for (int c = 0; c < this.getPolyLength(); c++){
		            newPoly.poly[c][this.getPolyLength()-1 -r] = this.poly[r][c];
		        }
		    }    
		    return newPoly;
		}
		

}
