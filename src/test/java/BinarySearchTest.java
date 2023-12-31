import com.meghabassi.neetcode150.BinarySearch;

import java.lang.reflect.Method;


import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;
import org.testng.ITest;
import org.testng.annotations.*;

public class BinarySearchTest implements ITest {
    static final Logger log = getLogger(lookup().lookupClass());

    protected static ThreadLocal<String> testName
            = new ThreadLocal<>();

    @BeforeMethod
    public void setUp(Method method,Object[] testData) {
        this.testName.set(method.getName()+"_"+testData[0]);
    }


    @DataProvider(name="testData")
    public static Object[][]data(){
        return new Object[][]{
                {"OddNumArrPositive",new int[]{1,2,3},1,0},
                {"EvenNumArrPositive",new int[]{1,2,3,4,5},3,2},
                {"EvenNumArrNegative",new int[]{1,2,3,4,5},6,-1}

        };
    }



    @Test(dataProvider = "testData")
    public void binarySearchTest(String descriptionString,int[] sortedArray,int target,int expectedIndex) {
        BinarySearch binarySearch =new BinarySearch(sortedArray);
        int actualIndex=binarySearch.binarySearch(target);

        log.debug("Array is {} \n Target is {} Expected index is {} \n Actual index is {}",sortedArray,target,expectedIndex,actualIndex);
        assertThat(actualIndex).isEqualTo(expectedIndex);

    }

    @Override
    public String getTestName() {
        return testName.get();
    }

}
