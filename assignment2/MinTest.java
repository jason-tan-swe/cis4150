package assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MinTest {

    private enum TestType {
        PASS, ERROR, FAILURE
    }

    private List<?> input;
    private Object expected;
    private TestType testType;
    private Class<? extends Throwable> expectedException;

    public MinTest(List<?> input, Object expected, TestType testType, Class<? extends Throwable> expectedException) {
        this.input = input;
        this.expected = expected;
        this.testType = testType;
        this.expectedException = expectedException;
    }

    @Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
        	//
            // Passing tests
        	//
        	
        	/*
        	 * Purpose: Always returns the only element when one is present
        	 * Expected Outcome: -2
        	 * Pass/Error/Fail: Pass 
        	 * Why: Given a list of size one, min does not have any other elements to compare to
        	 * therefore returning the only element in the list
        	 * 
        	 */
            {Arrays.asList(-2), -2, TestType.PASS, null}, // 1 element
            /*
        	 * Purpose: Tests that minimum is found given a list of comparables that vary in value
        	 * Expected Outcome:- 5
        	 * P/E/F: P
        	 * Why: When comparing integers, the value that is least is returned as negatives of the
        	 * greatest magnitude are the minimums or the lowest positive number in magnitude or 0
        	 * 
        	 */
            {Arrays.asList(10, -5, -4, 3, 0), -5, TestType.PASS, null}, // mixed ints
            /*
        	 * Purpose: Tests that comparables of strings are accepted
        	 * Expected Outcome: "apple"
        	 * P/E/F: P
        	 * Why: Comparables for strings are by lexicographical order meaning "apple" is always returned
        	 * since 'a' comes before 'b' and 'c'
        	 * 
        	 */
            {Arrays.asList("apple", "banana", "cherry"), "apple", TestType.PASS, null}, // strings
            /*
        	 * Purpose: Tests comparables of characters
        	 * Expected Outcome: 'A'
        	 * P/E/F: P
        	 * Why: Comparables for characters are by lexicographical order meaning 'A' by lexicographical order is the least
        	 * element throughout the list
        	 * 
        	 */
            {Arrays.asList('a', 'A', 'b', 'c'), 'A', TestType.PASS, null}, // chars
            
            //
            // Error tests
            //
            
            /*
        	 * Purpose: Test that lists of size 0 cause a failure to occur
        	 * Expected Outcome: IllegalArgumentException
        	 * P/E/F: E
        	 * Why: The Min method purposefully throws an exception when the list size is equal to zero
        	 * 
        	 */
            {new ArrayList<>(), null, TestType.ERROR, IllegalArgumentException.class}, // empty list
            /*
        	 * Purpose: Test that contains a list with null elements of integers
        	 * Expected Outcome: NullPointerException
        	 * P/E/F: E
        	 * Why: The Min method purposefully throws when the element being compared is null
        	 * 
        	 */
            {Arrays.asList(1, null, 2), null, TestType.ERROR, NullPointerException.class}, // null ints
            /*
        	 * Purpose: Test that contains a list with multi-typed elements
        	 * Expected Outcome: ClassCastException
        	 * P/E/F: E
        	 * Why: Comparable tries to compare items of same type. When different, an error is thrown
        	 * 
        	 */
            {Arrays.asList("apple", 5, "2"), 5, TestType.ERROR, ClassCastException.class}, // mixed string + ints
            /*
        	 * Purpose: Test strings and characters with null items in a list
        	 * Expected Outcome: NullPointerException
        	 * P/E/F: E
        	 * Why: The Min method purposefully throws when the element being compared is null
        	 * 
        	 */
            {Arrays.asList(null, 'a', "b"), null, TestType.ERROR, NullPointerException.class}, // null chars
            
            //
            // Failure tests
            //
            
            /*
        	 * Purpose: Confirm that Min method works as intended returning the minimum list item for integers
        	 * Expected Outcome: 2
        	 * P/E/F: F
        	 * Why: Failure occurs because the min method actually will return 1, however,
        	 * we expect 2 as the oracle to ensure min has proper behaviour
        	 * 
        	 */
            {Arrays.asList(1, 2, 3), 2, TestType.FAILURE, null}, // does not return expected int
            /*
        	 * Purpose: Confirm that Min method works as intended returning the minimum list item for strings
        	 * Expected Outcome: 'b'
        	 * P/E/F: F
        	 * Why: Failure occurs because the min method actually will return 'a', however,
        	 * we expect 'b' as the oracle to ensure min has proper behaviour
        	 * 
        	 */
            {Arrays.asList('a', 'b', 'c'), 'b', TestType.FAILURE, null}, // does not return expected string
        });
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testMin() {
        switch (testType) {
            case PASS:
                assertEquals("Should return the minimum value", expected, Min.min((List<Comparable>) input));
                break;
            case ERROR:
                Min.min((List<Comparable>) input);
                break;
            case FAILURE:
                assertEquals("Should not return the expected (incorrect) value", expected, Min.min((List<Comparable>) input));
                break;
        }
    }
}
