package com.github.yuan0122;

import java.util.ArrayList;
import java.util.List;

/*
 * You have 4 types of Lego blocks, of sizes 1, 2, 3, and 4. 
 * Assume that you have an infinite number of blocks of each type.
 * Using these blocks, you want to make a wall of height N and width M. 
 * The wall should not have any holes in it. The wall you build should be one solid structure.
 * A solid structure means you cannot make a vertical cut from top to bottom without cutting one or more Lego blocks.
 * The blocks can only be placed horizontally. In how many ways can the wall be built?
*/

public class MakeWallWithLegoBlocks {

	// Methods of getting width M for one layer: dp[M] = dp[M-1] + dp[M-2] + dp[M-3] + dp[M-4]
	// Methods of getting width M and height N:  
	// 		res[M] = (methods without restrictions) - (illegal ways)
	//			   = (dp[M] ^ N) - (res[1] * dp[M-1]^N + res[2] * dp[M-2]^N + ... + )
	public int lego(int width, int height) {
		assert width > 0 && height > 0;
		// Methods of getting width M for one layer
		int[] dp = oneLayer(width);
		int[] res = new int[width + 1];
		res[1] = 1;
		for (int i = 2; i <= width; i++) {
			//res[i] = power(dp[i], height);
			res[i] = (int) Math.pow(dp[i], height);
			for (int j = 1; j < i; j++) {
				// res[i] -= res[j] * power(dp[i - j], height);
				res[i] -= res[j] * Math.pow(dp[i - j], height);
			}
		}
		return res[width];
	}
	// a helper function to calculate methods of getting width M for one layer
	public int[] oneLayer(int width) {
		assert width > 0;
		int[] dp = new int[width + 1];
		dp[0] = 0;
		dp[1] = 1;
		if (width == 1) {
			return dp;
		}
		dp[2] = 2;
		if (width == 2) {
			return dp;
		}
		dp[3] = dp[2] + dp[1] + 1;
		if (width == 3) {
			return dp;
		}
		dp[4] = dp[3] + dp[2] + dp[1] + 1;
		if (width == 4) {
			return dp;
		}
		for (int i = 5; i <= width; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4];
		}
		return dp;
	}
	// a helper function to calculate a ^ b
	public int power(int a, int b) {
		if (a == 0) {
			return 0;
		}
		if (b == 0) {
			return 1;
		}
		int result = power(a, b / 2);
		if (b % 2 == 0) {
			return result * result;
		} else {
			return result * result * a;
		}
	}
	public List<Integer> toList(int[] array) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i< array.length; i++) {
			result.add(array[i]);
		}
		return result;
	}
	public static void main(String[] args) {
		MakeWallWithLegoBlocks solution = new MakeWallWithLegoBlocks();
		
		int width = 2;
		int height = 2;
		System.out.println(solution.toList(solution.oneLayer(width)));
		System.out.println(solution.lego(width, height));
		System.out.println("-----------------------------------------");
		// | |    |
		// |   |
		// |   |
		width = 3;
		height = 2;
		System.out.println(solution.toList(solution.oneLayer(width)));
		System.out.println(solution.lego(width, height));
		System.out.println("-----------------------------------------");
		width = 4;
		height = 3;
		System.out.println(solution.toList(solution.oneLayer(width)));
		System.out.println(solution.lego(width, height));
		width = 5;
		height = 6;
		System.out.println(solution.toList(solution.oneLayer(width)));
		System.out.println(solution.lego(width, height));
	}
}
