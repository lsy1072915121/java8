package com.liushiyao.java8.performance;

import org.junit.Test;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class NumSumDemo {

    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
//            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }


    static class ParallelStreams {



        //传统for循环的迭代版本执行起来应该会快很多，因为它更为底层，更重要的是不需要对
        //原始类型做任何装箱或拆箱操作。
        public static long iterativeSum(long n) {
            long result = 0;
            for (long i = 1L; i <= n; i++) {
                result += i;
            }
            return result;
        }
        //串行流
        public static long sequentialSum(long n) {
            return Stream.iterate(1L, i -> i + 1)
                    .limit(n)
                    .reduce(0L, Long::sum);
        }
        //并行流
        public static long parallelSum(long n) {
            return Stream.iterate(1L, i -> i + 1)
                    .limit(n)
                    .parallel()
                    .reduce(0L, Long::sum);
        }

        // LongStream.rangeClosed直接产生原始类型的long数字，没有装箱拆箱的开销
        // LongStream.rangeClosed会生成数字范围，很容易拆分为独立的小块。例如，范围1~20可分为1~5、6~10、11~15和16~20
        public static long rangedSum(long n) {
            return LongStream.rangeClosed(1, n)
                    .reduce(0L, Long::sum);
        }

        public static long parallelRangedSum(long n) {
            return LongStream.rangeClosed(1, n)
                    .parallel()
                    .reduce(0L, Long::sum);
        }

    }

    @Test
    public void Demo() {

        System.out.println("Parallel iterativeSum:" + measureSumPerf(ParallelStreams::iterativeSum, 20_000_000) + " msecs");
        System.out.println("Parallel sequentialSum:" + measureSumPerf(ParallelStreams::sequentialSum, 20_000_000) + " msecs");
        System.out.println("Parallel parallelSum:" + measureSumPerf(ParallelStreams::parallelSum, 20_000_000) + " msecs");
        System.out.println("Parallel rangedSum:" + measureSumPerf(ParallelStreams::rangedSum, 20_000_000) + " msecs");
        System.out.println("Parallel parallelRangedSum:" + measureSumPerf(ParallelStreams::parallelRangedSum, 20_000_000) + " msecs");
    }

    @Test
    public void Demo2(){
        LongStream s = LongStream.rangeClosed(1,10);
        s.forEach(System.out::println);
    }

}
