package xyes;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * A test runner class for running the MainTest class. This will print the number of test cases ran,
 * and the number of success and failed test cases.
 */
public class TestRunner {

  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(MainTest.class);
    int totalCount = result.getRunCount();
    int failureCount = result.getFailureCount();
    int successCount = totalCount - failureCount;

    System.out.println("Ran " + totalCount + " cases in total for xyes program, " + failureCount +
        " cases were failed, " + successCount + " cases were succeed.");

  }
}
