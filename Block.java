//	Name: Kuo, ChiaTse
//	StudentID: 54889895
//	Lab section: T01

public abstract class Block {
	private Cell[] cells;
	private char shape;
	private int status;
	public Block(Cell c1, Cell c2, Cell c3, Cell c4, char shape, int status) {
		cells = new Cell[4];
		cells[0] = c1;
		cells[1] = c2;
		cells[2] = c3;
		cells[3] = c4;
		this.shape = shape;
		this.status = status;
	}
	public char getShape() {
		return this.shape;
	}
	public int getStatus() {
		return this.status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Cell[] getcells() {
		return this.cells;
	}
	public void moveleft() throws OutOfBoardException{
		for(int i=0; i<4; i++) {
			this.cells[i].left();
		}
		for(int i=0; i<4; i++) {
			if(this.cells[i].getCol()<0) {
				throw new OutOfBoardException();
			}
		}
	}
	public void moveright() throws OutOfBoardException{
		for(int i=0; i<4; i++) {
			this.cells[i].right();
		}
		for(int i=0; i<4; i++) {
			if(this.cells[i].getCol()>=Board.BOARD_WIDTH) {
				throw new OutOfBoardException();
			}
		}
	}
	public void movedown() throws OutOfBoardException{
		for(int i=0; i<4; i++) {
			this.cells[i].down();
		}
		for(int i=0; i<4; i++) {
			if(this.cells[i].getRow()>=Board.BOARD_HEIGHT) {
				throw new OutOfBoardException();
			}
		}
	}
	public void moveback() {
		for(int i=0; i<4; i++) {
			this.cells[i].up();
		}
	}
	public void rotate() {	// check rotate status
		int x_cent = this.cells[0].getCol();
		int y_cent = this.cells[0].getRow();
		for(int i=0; i<4; i++) {
			int orig_x = this.cells[i].getCol()-x_cent;
			int orig_y = this.cells[i].getRow()-y_cent;
			int rotated_x = -orig_y;
			int rotated_y = orig_x;
			this.cells[i].setCol(rotated_x+x_cent); 
			this.cells[i].setRow(rotated_y+y_cent);
		}
		if(this.getShape()=='I') {
			switch(this.status) {
			case 0:
				this.status += 1;
				try {
					this.movedown();
				} catch (OutOfBoardException e) {
					this.moveback();
				}
				break;
			case 1:
				this.status += 1;
				try {
					this.moveleft();
				} catch (OutOfBoardException e) {
					try {
						this.moveright();
					} catch (OutOfBoardException e1) {}
				}
				break;
			case 2:
				this.status += 1;
				this.moveback();
				break;
			case 3:
				this.setStatus(0);
				try {
					this.moveright();
				} catch (OutOfBoardException e) {
					try {
						this.moveleft();
					} catch (OutOfBoardException e1) {
						e1.printStackTrace();
					}
				}
				break;
			}
		}
	}
}
