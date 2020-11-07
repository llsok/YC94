
package com.yc.api.d1107;

import java.util.*;
import java.util.Map.Entry;  // Map 接口中的内部静态类

public class Demo3 {
	
	public static void main(String[] args) {
		
		/*
		 * 生成 0~100之间的数字 1000个 统计每个数字出现的次数
		 * 
		 * 1 ==> 2
		 * 3 ==> 2
		 * 
		 * Map
		 * 
		 */
		
		List<String> list = new ArrayList<>();
		
		Random random = new Random();
		for(int i=0; i<1000;i++){
			int num = random.nextInt(101);
			list.add(num+"");
		}
		
		Map<String, Integer> map = new TreeMap<>();
		for(String i : list){
			// 先判断该数字有没有在map中
			if ( map.containsKey(i)){
				int count = map.get(i);
				map.put(i, count + 1);
			} else {
				map.put(i, 1);
			}
		}
		
		Set<Entry<String,Integer>> entrys = map.entrySet();
		
		for( Entry<String,Integer> entry : entrys  ){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		
		System.out.println(map);
		
		
	}

}
