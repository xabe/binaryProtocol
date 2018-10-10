package com.xabe.binary.protocol.benchmark;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class BenchmarkBase {

    public static void main(String[] args) throws RunnerException, IOException {

        final Properties properties = new Properties();
        final String path = BenchmarkBase.class.getClassLoader().getResource("benchmark.properties").getPath();
        final String decodePath = URLDecoder.decode(path, StandardCharsets.UTF_8.name());
        properties.load(new InputStreamReader(new FileInputStream(decodePath)));

        int warmup = Integer.parseInt(properties.getProperty("benchmark.warmup.iterations", "5"));
        int iterations = Integer.parseInt(properties.getProperty("benchmark.test.iterations", "5"));
        int forks = Integer.parseInt(properties.getProperty("benchmark.test.forks", "1"));
        int threads = Integer.parseInt(properties.getProperty("benchmark.test.threads", "1"));
        String testClassRegExPattern = properties.getProperty("benchmark.global.testclassregexpattern", ".*Benchmark.*");
        String resultFilePrefix = properties.getProperty("benchmark.global.resultfileprefix", "jmh-");

        ResultFormatType resultsFileOutputType = ResultFormatType.JSON;

        Options opt = new OptionsBuilder()
                .include(testClassRegExPattern)
                .warmupIterations(warmup)
                .measurementIterations(iterations)
                .forks(forks)
                .threads(threads)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .resultFormat(resultsFileOutputType)
                .result(buildResultsFileName(resultFilePrefix, resultsFileOutputType))
                .shouldFailOnError(true)
                .jvmArgs("-server")
                .build();

        new Runner(opt).run();
    }

    private static String buildResultsFileName(String resultFilePrefix, ResultFormatType resultType) {
        
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm-dd-yyyy-hh-mm-ss");

        String suffix;
        switch (resultType) {
            case CSV:
                suffix = ".csv";
                break;
            case SCSV:
                // Semi-colon separated values
                suffix = ".scsv";
                break;
            case LATEX:
                suffix = ".tex";
                break;
            case JSON:
            default:
                suffix = ".json";
                break;

        }

        return String.format("target/%s%s%s", resultFilePrefix, date.format(formatter), suffix);
    }
    
    
    
    
}
