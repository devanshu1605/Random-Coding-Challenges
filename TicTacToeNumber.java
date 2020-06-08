package com.java.palindrome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class TicTacToeNumber {
	static Scanner in;
	static int[] tttboard;
	static String nexturn;
	static List<Integer> winningPos = new ArrayList<Integer>(Arrays.asList(285,063,021,120,345,678,036,147,258,480,246,462,264));
	static List<Integer> computeMovelist =new ArrayList<>(Arrays.asList(4,0,2,6,8,1,3,5,7));
	static int noOfTurns = 0;
	static List oddPlayerList = new ArrayList<Integer>(Arrays.asList(1,3,5,7,9));
	static List evenPlayerList = new ArrayList<Integer>(Arrays.asList(2,4,6,8));
	static Map<Integer,List<Integer>>availableNextMoveMap = new HashMap<>();
	static String winner = null;
	static int playerPos=-1;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		tttboard = new int[9];
		nexturn = "odd";
		populateEmptyBoard();
		printBoard();
		System.out.println("1. Two Player 2. Vs Computer");
		int option = in.nextInt();
		System.out.println("Odd will play first. Enter a position to place number");
		if(option == 1) {
			twoPlayermode();
		}else {
			vsComputerMode();
		}
	}

	private static void vsComputerMode() {
		String winner = null;
		int pos;
		int numInput;
		prepareMoveSet();
		while (winner == null) {
			if(nexturn=="odd") {
				System.out.println("Enter position ");
				pos = in.nextInt();
				System.out.println("Enter number ");
				numInput = in.nextInt();
				if (!(pos > 0 && pos <= 9)) {
						System.out.println("Invalid input; re-enter position");
				}
				boolean validNumberCheck = (oddPlayerList.contains(numInput) && computeMovelist.contains(pos));
				if(validNumberCheck) {
					tttboard[pos-1]=numInput;
					printBoard();
					winner = checkWinner("odd");
					pos = pos-1;
					computeMovelist.remove(Integer.valueOf(pos));
					nexturn ="even";
					noOfTurns++;
					playerPos=pos;
				}
			}else {
				pos = executeComputerTurn();
				System.out.println("\n computer updated position : "+(pos+1)+"\n");
				computeMovelist.remove(0);
				printBoard();
				noOfTurns++;
				nexturn ="odd";
				winner = checkWinner("even");
			}
		}
		if (winner.equalsIgnoreCase("draw")) {
			System.out.println("It's a draw! Thanks for playing.");
		} else {
			System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
		}
	}

	private static void prepareMoveSet() {
		List list1 = new ArrayList();
		list1.add(12);
		list1.add(48);
		list1.add(36);
		availableNextMoveMap.put(0, list1);
		List<Integer> list2 = new ArrayList<>();
		list2.add(20);
		list2.add(47);
		availableNextMoveMap.put(1, list2);
		List<Integer> list3 = new ArrayList<>();
		list3.add(10);
		list3.add(46);
		list2.add(58);
		availableNextMoveMap.put(2, list3);
		List<Integer> list4 = new ArrayList<>();
		list4.add(12);
		list4.add(45);
		List<Integer> list5 = new ArrayList<>();
		list5.add(35);
		list5.add(17);
		list5.add(80);
		list5.add(26);
		List<Integer> list6 = new ArrayList<>();
		list6.add(28);
		list6.add(35);
		List<Integer> list7 = new ArrayList<>();
		list7.add(30);
		list7.add(42);
		list7.add(78);
		List<Integer> list8 = new ArrayList<>();
		list8.add(68);
		list8.add(41);
		List<Integer> list9 = new ArrayList<>();
		list9.add(76);
		list9.add(52);
		list9.add(40);
		
		availableNextMoveMap.put(3, list4);
		availableNextMoveMap.put(4, list5);
		availableNextMoveMap.put(5, list6);
		availableNextMoveMap.put(6, list7);
		availableNextMoveMap.put(7, list8);
		availableNextMoveMap.put(8, list9);
		
	}

	private static int executeComputerTurn() {
		int position;
		int value;
		position = findInsertPositions();
		value = findInsertValue(position);
		updatetttBoard(position, value);
		return position;
	}

	private static int findInsertValue(int position) {
		
		return 0;
	}

	/*
	 * private static int findInsertPositions() { Map<Integer,Integer> posMap = new
	 * HashMap(); int firstPosition = 0; int secondPosition = 0; int finalPos = -1;
	 * List<Integer> list = availableNextMoveMap.get(playerPos); for(Integer i :
	 * list) { firstPosition= i/10; secondPosition=i%10; if(tttboard[playerPos-1]==)
	 * } return finalPos; }
	 */

	private static int updatetttBoard(int move, int num) {
		tttboard[move]=num;
		computeMovelist.remove(Integer.valueOf(move));
		return move;
	}

	static void twoPlayermode() {
		String winner = null;
		boolean validNumberCheck=false;
		while (winner == null) {
			int numInput;
			int pos;
			try {
				pos = in.nextInt();
				numInput = in.nextInt();
				if (!(pos > 0 && pos <= 9)) {
					System.out.println("Invalid input; re-enter position");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input; re-enter slot number:");
				continue;
			}
			winner = null;
			if(nexturn.equals("odd")) {
				validNumberCheck=(oddPlayerList.contains(numInput) && computeMovelist.contains(pos));
				winner = "odd";
			}else {
				validNumberCheck=(evenPlayerList.contains(numInput)  && computeMovelist.contains(pos));
				winner = "even";
			}
			if(validNumberCheck) {
					tttboard[pos-1] = numInput;
					printBoard();
					winner = checkWinner(winner);
			}else {
				System.out.println(" Either input is wrong or Position is incorrect. Try again ");
				continue;
			}
		}
		if (winner.equalsIgnoreCase("draw")) {
			System.out.println("It's a draw! Thanks for playing.");
		} else {
			System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
		}
	}

	static String checkWinner(String winner2) {
		for (int a = 0; a < 8; a++) {
			int line = 0;
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
			if (line==15) {
				return winner2;
			}else {
				return null;
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
		System.out.println("|--------|--------|--------|");
		System.out.println("| " + "pos[1]" + " | " + "pos[2]" + " | " + "pos[3]" + " |");
		System.out.println("|--------|--------|--------|");
		System.out.println("| " + "pos[3]" + " | " + "pos[4]" + " | " + "pos[5]" + " |");
		System.out.println("|--------|--------|--------|");
		System.out.println("| " + "pos[6]" + " | " + "pos[7]" + " | " + "pos[8]" + " |");
		System.out.println("|--------|--------|--------|");
	}

	static void populateEmptyBoard() {
		for (int a = 0; a < 9; a++) {
			tttboard[a] = a+1;
		}
	}
}