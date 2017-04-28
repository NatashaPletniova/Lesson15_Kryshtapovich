package by.epam.lesson15.main;

import by.epam.lesson15.thread.MatrixMultiplication;
import by.epam.lesson15.thread.locker.LockerHandler;
import by.epam.lesson15.util.MatrixUtil;

public class Main {

	public static void main(String[] args) {

		int[][] matrixA = new int[][] { { 1, 2, 3, 4, 6, 9 }, { 4, 5, 6, 5, 7, 3 }, { 7, 8, 9, 6, 2, 1 },
				{ 1, 2, 3, 7, 9, 3 }, { 7, 8, 9, 6, 8, 2 }, { 7, 8, 9, 6, 9, 1 }

		};
		int[][] matrixB = new int[][] { { 9, 8, 7, 6, 9, 3 }, { 6, 5, 4, 7, 8, 2 }, { 3, 2, 1, 8, 5, 3 },
				{ 9, 8, 7, 9, 4, 8 }, { 3, 2, 1, 8, 2, 9 }, { 3, 2, 1, 8, 1, 7 }

		};

		int[][] matrixC = new int[matrixA.length][matrixA[0].length];

		LockerHandler lockerHandler = new LockerHandler(matrixC.length);

		int numberOfThreads = 10;

		MatrixMultiplication[] threads = new MatrixMultiplication[numberOfThreads];

		for (int i = 0; i < numberOfThreads; i++) {

			threads[i] = new MatrixMultiplication(lockerHandler, matrixA, matrixB, matrixC);
			threads[i].setName("Thread #" + i);
			threads[i].start();

		}

		for (int i = 0; i < numberOfThreads; i++) {

			try {
				threads[i].join();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		System.out.println();
		MatrixUtil.printMatrix(matrixC);

	}
}
