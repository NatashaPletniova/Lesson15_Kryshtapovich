package by.epam.lesson15.thread.locker;

public class LockerHandler {

	private int nextRow = 0;
	private final int rowCount;

	public LockerHandler(int rowCount) {
		this.rowCount = rowCount;
	}

	public boolean isFinished() {

		if (nextRow == rowCount) {
			return true;
		} else {
			return false;
		}
	}

	public int giveNextRow() {
		if (this.isFinished()) {
			throw new IllegalStateException();
		}
		int rowToCalculate = nextRow;
		nextRow++;
		return rowToCalculate;
	}

}
