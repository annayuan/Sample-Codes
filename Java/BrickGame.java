package com.github.yuan0122;

/*
 * LetÕs play a game! Here is the rule of this game:
 * there are N bricks, each brick has a score. 
 * In each turn, one player can alternatively remove 1, 2 or 3 bricks from the top, 
 * and the numbers on the removed bricks are added to that playerÕs score. 
 * Assume that your friend will also play optimally and you make the first move. 
 * Write a function to return the maximum possible score you can get.
*/
public class BrickGame {

	// Think about this problem in backward way
	// opt[i] stands for max score that I can get start from ith brick
	public int maxScore(int[] input) {
		assert input != null && input.length > 3;
		int[] opt = new int[input.length];
		int sum = 0;
		for (int i = input.length - 1; i >= 0; i--) {
			sum += input[i];
			// for all position brick scores, there is only one way to get max score start from the last three bricks
			// which is to pick all of possible bricks
			if (i >= input.length - 3) {
				opt[i] = sum;
			} else {
				// for every step, we can pick one brick or two or three
				// so just get the max value
				// for  other cases, just return the max of (sum - oponent's score)
				// opt[i] = Math.max(sum - opt[i + 1], sum - opt[i + 2], sum - opt[i + 2])
				opt[i] = Math.max(sum - opt[i + 1], sum - opt[i + 2]);
				opt[i] = Math.max(opt[i], sum - opt[i + 3]);
			}
		}
		return opt[0];
	}
	public static void main(String[] args) {
		BrickGame solution = new BrickGame();
		
		int[] input = new int[] {0, 1, 1, 1, 1, 999};
		System.out.println(solution.maxScore(input));
		input = new int[] {0, 1, 1, 1, 1, 2, 3, 999};
		System.out.println(solution.maxScore(input));
	}
}
