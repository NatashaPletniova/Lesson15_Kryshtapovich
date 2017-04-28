package by.epam.lesson15.thread;

import by.epam.lesson15.thread.locker.LockerHandler;

public class MatrixMultiplication extends Thread {

	private LockerHandler lockerHandler;
	private int[][] matrixA;
	private int[][] matrixB;
	private int[][] matrixC;

	public MatrixMultiplication(LockerHandler lockerHandler, int[][] matrixA, int[][] matrixB, int[][] matrixC) {

		this.lockerHandler = lockerHandler;
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.matrixC = matrixC;
	}

	@Override
	public void run() {
		while (true) {
			int row;
			synchronized (lockerHandler) {
				if (lockerHandler.isFinished()) {
					break;
				}
				row = lockerHandler.giveNextRow();
			}
			System.out.println(Thread.currentThread().getName() + " calculating row number " + row);

			for (int j = 0; j < matrixB[0].length; j++) {
				for (int k = 0; k < matrixA[0].length; k++) {
					matrixC[row][j] += matrixA[row][k] * matrixB[k][j];
				}

			}
		}
	}

}