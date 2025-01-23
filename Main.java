package com.tictaktoe;
import java.util.Scanner;

public class Main {
	private static char[][] board;
	private static char currPlayer;
	private static int n;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Size of the Board: (N x N)");
		n = sc.nextInt();
		board = new char[n][n];
		initializeBoard();
		printBoard();
		
		int moves = 0;
		boolean gameOver = false;
		currPlayer = 'X';
		
		System.out.println("Note: All the moves are 0 - indexed\n");
		
		while (moves < n * n && !gameOver) {
			System.out.println("Player " + currPlayer + "! Enter your move: (row, col)");
			int row = sc.nextInt();
			int col = sc.nextInt();
			
			if (isValidMove(row, col)) {
				board[row][col] = currPlayer;
				moves++;
				if (checkWin(row, col)) {
					printBoard();
					System.out.println("Congrats! Player " + currPlayer + " Wins!");
					gameOver = true;
				}
				else {
					printBoard();
					currPlayer = (currPlayer == 'X' ? 'O' : 'X');
				}
			}
			
			else {
				System.out.println("Invalid Move! Please Try Again");
			}
		}
		
		if (!gameOver) {
			System.out.println("The Game is Drawn!");
		}
		
	}
	
	private static boolean checkWin(int row, int col) {
		return (checkRow(row) || checkCol(col) || checkDiagonal());
	}
	
	private static boolean checkRow(int row) {
		for (int col = 0; col < n; col++) {
			if (board[row][col] != currPlayer) {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean checkCol(int col) {
		for (int row = 0; row < n; row++) {
			if (board[row][col] != currPlayer) {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean checkDiagonal() {
		boolean diagonal1 = true, diagonal2 = true;
		for (int i = 0; i < n; i++) {
			if (board[i][i] != currPlayer) {
				diagonal1 = false;
			}
			if (board[i][n - i - 1] != currPlayer) {
				diagonal2 = false;
			}
			if (!diagonal1 && !diagonal2) {
				return false;
			}
		}
		
		return (diagonal1 || diagonal2);
	}
	
	private static boolean isValidMove(int row, int col) {
		return (row >= 0 && row < n && col >= 0 && col < n && board[row][col] == '-');
	}
	
	private static void initializeBoard() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = '-';
			}
		}
	}
	
	private static void printBoard() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
