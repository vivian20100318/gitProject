package com.hjkj.business.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateCodes {
	private static CreateCodes codes = null;

	private CreateCodes() {
	};

	/**
	 * 
	 * @方法名称:createCode
	 * @内容摘要: ＜生成虚拟产品码�?
	 * @param num
	 *            生成多少�?
	 * @return List<String>
	 * @exception
	 * @author:luweiwei
	 * @since 1.0.0
	 */
	public synchronized static List<String> createCode(Integer num) {
		List<String> list = new ArrayList<String>();
		// Long t = System.currentTimeMillis();
		// for (int i = 1; i <= num; i++) {
		// String l = Long.toHexString(t);
		// String s_i = Integer.toHexString(i);
		// // System.out.println(l+s_i);
		// // set.add(l+s_i);
		// list.add(l + s_i);
		// }
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		int count = 0;
		while (count < num) {
			// System.out.println(generateNumber());
			String codeStr = generateNumber();
			// System.out.println(bb);
			if (list.contains(codeStr)) {
				//System.out.println("aaaaa=========");
			} else {
				list.add(codeStr);
				count++;
			}
		}
		return list;
	}

	public static CreateCodes getInstance() {
		if (codes == null) {
			codes = new CreateCodes();
		}
		return codes;
	}

	/**
	 * 这是典型的随机洗牌算法�?? 流程是从备�?�数组中选择�?个放入目标数组中，将选取的数组从备�?�数组移除（放至�?后，并缩小�?�择区域�? 算法时间复杂度O(n)
	 * 
	 * @return 随机8为不重复数组
	 */
	public static String generateNumber() {
		String no = "";
		// 初始化备选数�?
		int[] defaultNums = new int[10];
		for (int i = 0; i < defaultNums.length; i++) {
			defaultNums[i] = i;
		}

		Random random = new Random();
		int[] nums = new int[LENGTH];
		// 默认数组中可以�?�择的部分长�?
		int canBeUsed = 10;
		// 填充目标数组
		for (int i = 0; i < nums.length; i++) {
			// 将随机�?�取的数字存入目标数�?
			int index = random.nextInt(canBeUsed);
			nums[i] = defaultNums[index];
			// 将已用过的数字扔到备选数组最后，并减小可选区�?
			//swap(index, canBeUsed - 1, defaultNums);
			//canBeUsed--;
		}
		if (nums.length > 0) {
			for (int i = 0; i < nums.length; i++) {
				no += nums[i];
			}
		}

		return no;
	}

	private static final int LENGTH = 8;

	private static void swap(int i, int j, int[] nums) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static String generateNumber2() {
		String no = "";
		int num[] = new int[8];
		int c = 0;
		for (int i = 0; i < 8; i++) {
			num[i] = new Random().nextInt(10);
			c = num[i];
			for (int j = 0; j < i; j++) {
				if (num[j] == c) {
					i--;
					break;
				}
			}
		}
		if (num.length > 0) {
			for (int i = 0; i < num.length; i++) {
				no += num[i];
			}
		}
		return no;
	}
}
