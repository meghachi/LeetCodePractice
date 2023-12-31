import com.meghabassi.systemdesign.ratelimiter.LoggerRateLimiter;
import com.meghabassi.systemdesign.ratelimiter.LoggerRateLimiterNoCleanup;

import java.util.Arrays;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.slf4j.LoggerFactory.getLogger;

import com.meghabassi.systemdesign.ratelimiter.LoggerRateLimiterTwoMap;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoggerRaterLimiterTest {
    static final Logger log = getLogger(lookup().lookupClass());
    static String[][] testParamsArray;
    static Boolean[] expectedArray;

    @BeforeClass
    public void setup(){
        testParamsArray=new String[][]{{null,null},
                {"1","foo"},
                {"1","bar"},
                {"3","foo"},
                {"8","bar"},
                {"10","foo"},
                {"11","foo"},
                {"11","bar"}
        };
        expectedArray=new Boolean[]{null,
                                   true,
                                   true,
                                   false,
                                    false,
                                    false,
                                    true,
                                    true};
    }


    private static Boolean[] parseAndSend(String[][] testParamsArray, LoggerRateLimiter loggerRateLimiter){
        //LoggerRateLimiterNoCleanup loggerrateLimiterNoCleanup =new LoggerRateLimiterNoCleanup();
        Boolean[] results=new Boolean[testParamsArray.length];
        int count=0;
        for(int i=0;i< testParamsArray.length;i++ )
        {
            String[] entry=testParamsArray[i];
            if(entry[0]==null)
                results[i]=null;
            else {
                int timestamp=0;
                try {
                    timestamp=Integer.parseInt(entry[0]);
                    boolean isMessageSaved= loggerRateLimiter.shouldPrintMessage(timestamp,entry[1]);
                    results[i]=isMessageSaved;
                }
                catch(NumberFormatException ex){
                    ex.printStackTrace();
                }
            }


        }
        return results;
    }
    @Test
    public void testLoggerRateLimiter() {
        LoggerRateLimiterNoCleanup loggerRateLimiterNoCleanup= new LoggerRateLimiterNoCleanup();
        Boolean[] isMessageSavedArr=parseAndSend(testParamsArray,loggerRateLimiterNoCleanup);
        log.debug("Expected Array is {} \n Actual array is {}",expectedArray,isMessageSavedArr);
        assertThat(Arrays.equals(isMessageSavedArr,expectedArray)).isTrue();




    }
    @Test
    public void testLoggerRateLimiterSaveMem() {
        LoggerRateLimiterTwoMap loggerRateLimiterTwoMap = new LoggerRateLimiterTwoMap();
        Boolean[] isMessageSavedArr=parseAndSend(testParamsArray, loggerRateLimiterTwoMap);
        log.debug("Expected Array is {} \n Actual array is {}",expectedArray,isMessageSavedArr);
        assertThat(Arrays.equals(isMessageSavedArr,expectedArray)).isTrue();




    }
}
