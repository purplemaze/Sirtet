package tetris;

public class TextTetrisView {
	
	public TextTetrisView() {
		
	}
	
	/**
	 * I, O, T, S, Z, J, L, OUTSIDE
	 * @param b
	 * @return
	 */
	public String convertToText(Board b) {
		
		StringBuilder sb = new StringBuilder();
		for(int y = 0; y < b.getHeight(); y++) {
			sb.append("\n");
			for (int x = 0; x < b.getWidth(); x++) {
				
				SquareType type = b.getSquaretype(y, x);
				if(b.getFalling() != null) {
					if(b.getFallingPositionY() == y && b.getFallingPositionX() == x) {
						//TO DO
					}			
				}
				switch (type) {
					case EMPTY:
						sb.append(" ");
						break;
					case I:
						sb.append("I");
						break;
					case O:
						sb.append("O");
						break;
					case T:
						sb.append("T");
						break;	
					case S:
						sb.append("S");
						break;	
					case J:
						sb.append("J");
						break;	
					case L:
						sb.append("L");
						break;
					case Z:
						sb.append("Z");
						break;	
					case OUTSIDE:
						sb.append("@");
						break;
						
					default:
						sb.append("-");
						break;
				}
			}
		}
		return sb.toString();
		
	}

}
