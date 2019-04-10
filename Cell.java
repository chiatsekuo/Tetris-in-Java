//	Name: Kuo, ChiaTse
//	StudentID: 54889895
//	Lab section: T01

public class Cell {
	private int row, col;
	private BlockColor color;
	public Cell(int row, int col, BlockColor color){
		this.row = row;
		this.col = col;
		this.color = color;
	}
	public int getRow() {
		return this.row;
	}
	public int getCol() {
		return this.col;
	}
	public BlockColor getColor() {
		return this.color;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public void setColor(BlockColor color) {
		this.color = color;
	}
	public void left() {
		this.col -= 1;
	}
	public void right() {
		this.col += 1;
	}
	public void down() {
		this.row += 1;
	}
	public void up() {
		this.row -= 1;
	}
	public String toStirng() {
		return "(" + this.getRow() + "," + this.getCol() + "," + this.getColor() + ")";
	}
}
