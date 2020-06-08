package com.java.palindrome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class TicTacToeXO {
	static Scanner in;
	static String[] tttboard;
	static String nexturn;
	static List<Integer> winningPos = new ArrayList<Integer>(Arrays.asList(285,063,021,120,345,678,036,147,258,480,246,462,264));
	static List<Integer> list =new ArrayList<>(Arrays.asList(4,0,2,6,8,1,3,5,7));
	static int noOfTurns = 0;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		tttboard = new String[9];
		nexturn = "X";
		populateEmptyBoard();
		printBoard();
		System.out.println("1. Two Player 2. Vs Computer");
		int option = in.nextInt();
		System.out.println("X's will play first. Enter a slot number to place X in:");
		if(option == 1) {
			twoPlayermode();
		}else {
			vsComputerMode();
		}
	}

	private static void vsComputerMode() {
		String winner = null;
		int move;
		while (winner == null) {
			if(nexturn=="X") {
				System.out.println("Your move");
				move = in.nextInt();
				if (tttboard[move-1].equals(String.valueOf(move))) {
					tttboard[move-1] = nexturn;
					printBoard();
					winner = checkWinner();
					move = move-1;
					list.remove(Integer.valueOf(move));
					nexturn ="O";
					noOfTurns++;
				}
			}else {
				System.out.println("computer's turn");
				move = executeComputerTurn();
				System.out.println("computer updated position : "+(move+1));
				list.remove(0);
				printBoard();
				noOfTurns++;
				nexturn ="X";
				winner = checkWinner();
			}
		}
		if (winner.equalsIgnoreCase("draw")) {
			System.out.println("It's a draw! Thanks for playing.");
		} else {
			System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
		}
	}

	private static int executeComputerTurn() {
		int move;
		if(noOfTurns>2) {
			move = findXpositions();
			if(move==-1) {  // no winning sequence found
				move = playToWin();
			}else {
				move=updatetttBoard(move);
			}
			nexturn="X";
		}else {
			move = playToWin();
		}
		return move;
	}

	private static int playToWin() {
		tttboard[list.get(0)] = "O";
		return list.get(0);
	}

	private static int findXpositions() {
		Map<Integer,Integer> posMap = new HashMap();
		int counter = 0;
		int finalPos = -1;
		String temp="";
		for(int i =0;i<tttboard.length;i++) {
			if(tttboard[i].equalsIgnoreCase("X")){
				for(int j=i+1;j<tttboard.length;j++) {
					if(tttboard[j]=="X") {
						temp= String.valueOf(i)+String.valueOf(j);
						for(Integer k:winningPos){
							if(k.toString().contains(temp)) {
								if(posMap.get(k)==null) {
									posMap.put(k, 1);
								}else {
									counter = posMap.get(k);
									posMap.put(k, counter+1);
								}
							}
						}
					}
				}
			}
		}
		if(!posMap.isEmpty()) {
			for(Entry entry:posMap.entrySet()) {
				int pos = Integer.parseInt(entry.getKey().toString().substring(2,3));
				if(tttboard[pos]!="O" && tttboard[pos]!="X") {
					finalPos = pos;
					break;
				}
			}
		}
		return finalPos;
	}

	private static int updatetttBoard(int move) {
		tttboard[move]="O";
		list.remove(Integer.valueOf(move));
		return move;
	}

	static void twoPlayermode() {
		String winner = null;
		while (winner == null) {
			int numInput;
			try {
				numInput = in.nextInt();
				if (!(numInput > 0 && numInput <= 9)) {
					System.out.println("Invalid input; re-enter slot number:");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input; re-enter slot number:");
				continue;
			}
			if (tttboard[numInput-1].equals(String.valueOf(numInput))) {
				tttboard[numInput-1] = nexturn;
				if (nexturn.equals("X")) {
					nexturn = "O";
				} else {
					nexturn = "X";
				}
				printBoard();
				winner = checkWinner();
			} else {
				System.out.println("Slot already taken; re-enter slot number:");
				continue;
			}
		}
		if (winner.equalsIgnoreCase("draw")) {
			System.out.println("It's a draw! Thanks for playing.");
		} else {
			System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
		}
	}

	static String checkWinner() {
		for (int a = 0; a < 8; a++) {
			String line = null;
			switch (a) {
			case 0:
				line = tttboard[0] + tttboard[1] + tttboard[2];
				break;
			case 1:
				line = tttboard[3] + tttboard[4] + tttboard[5];
				break;
			case 2:
				line = tttboard[6] + tttboard[7] + tttboard[8];
				break;
			case 3:
				line = tttboard[0] + tttboard[3] + tttboard[6];
				break;
			case 4:
				line = tttboard[1] + tttboard[4] + tttboard[7];
				break;
			case 5:
				line = tttboard[2] + tttboard[5] + tttboard[8];
				break;
			case 6:
				line = tttboard[0] + tttboard[4] + tttboard[8];
				break;
			case 7:
				line = tttboard[2] + tttboard[4] + tttboard[6];
				break;
			}
			if (line.equals("XXX")) {
				return "X";
			} else if (line.equals("OOO")) {
				return "O";
			}
		}

		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(tttboard).contains(String.valueOf(a+1))) {
				break;
			}
			else if (a == 8) return "draw";
		}

		System.out.println(nexturn + "'s turn; enter a slot number to place " + nexturn + " in:");
		return null;
	}

	static void printBoard() {

		System.out.println("| " + tttboard[0] + " | " + tttboard[1] + " | " + tttboard[2] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + tttboard[3] + " | " + tttboard[4] + " | " + tttboard[5] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + tttboard[6] + " | " + tttboard[7] + " | " + tttboard[8] + " |");
	}

	static void populateEmptyBoard() {
		for (int a = 0; a < 9; a++) {
			tttboard[a] = String.valueOf(a+1);
		}
	}
}