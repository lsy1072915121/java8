package com.liushiyao.java8;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;
import java.util.Random;

public class StreamTest {

	public static int[] arr;

	@BeforeAll
	public static void init() {
		arr = new int[500000000];
		randomInt(arr);
	}

	@JunitPerfConfig(duration = 10000, warmUp = 1000, reporter = {HtmlReporter.class})
	public void testIntFor() {
		minIntFor(arr);
	}

	@JunitPerfConfig(duration = 10000, warmUp = 1000, reporter = {HtmlReporter.class})
	public void testIntParallelStream() {
		minIntParallelStream(arr);
	}

	@JunitPerfConfig(duration = 10000, warmUp = 1000, reporter = {HtmlReporter.class})
	public void testIntStream() {
		minIntStream(arr);
	}

	private int minIntStream(int[] arr) {
		return Arrays.stream(arr).min().getAsInt();
	}

	private int minIntParallelStream(int[] arr) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		return Arrays.stream(arr).parallel().min().getAsInt();
	}

	private int minIntFor(int[] arr) {
		int min = Integer.MAX_VALUE;
		for (int anArr : arr) {
			if (anArr < min) {
				min = anArr;
			}
		}
		return min;
	}

	private static void randomInt(int[] arr) {
		Random r = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt();
		}
	}
}
