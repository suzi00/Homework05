import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTestTest {
    UnitTest unitTest ;
    @Before
    public void setUp() throws Exception{
        unitTest = new UnitTest(7,10);
    }

    @Test
    public void testingUnit() {
        Assert.assertEquals(17,unitTest.add());
    }
}