package dynamic_planing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
	public static void printArr2(int[][] dp) {
		for(int[] arr : dp) {
			List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());
			System.out.println(collect);
		}
	}
	public static void printArr2(boolean[][] dp) {
		System.out.println("\n");
		for(boolean[] arr : dp) {
			List<Integer> collect = Stream.of(Arrays.toString(arr)).map(s -> s.substring(1, s.length() - 1))
			.map(s->s.split(", "))
			.flatMap(Arrays::stream)
			.map(b -> "true".equals(b) ? 1 : 0)
			.collect(Collectors.toList());
			
//			String collect = Stream.of(Arrays.toString(arr)).map(s -> s.substring(1, s.length() - 1))
//					.collect(Collectors.joining());
			System.out.println(collect);
		}
	}
}
