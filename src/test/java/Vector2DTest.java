import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Vector2DTest {
    private final double DELTA =  1e-9; //1e-9 точность для вещественных типов 0,000_000_0001
    private static Vector2D v1;

    @BeforeClass
    public static void createNewVector2D() {
        v1 = new Vector2D();
    }
    @Test
    public void newVectorShouldHaveZeroLength() {
        Assert.assertEquals(0, v1.length(), DELTA);
    }

    @Test
    public void newVectorShouldHaveZeroX() {
        Assert.assertEquals(0, v1.getX(), DELTA);
    }

    @Test
    public void newVectorShouldHaveZeroY() {
        Assert.assertEquals(0, v1.getY(), DELTA);
    }

    @Test
    public void vectorShouldHavePositive1() {
        v1.setX(-1.0);
        v1.setY(-1.0);
        Assert.assertEquals(1.414213562373095, v1.length(), DELTA);
    }

    @Test
    public void vectorShouldHavePositive2() {
        v1.setX(-1.0);
        v1.setY(1.0);
        Assert.assertEquals(1.414213562373095, v1.length(), DELTA);
    }
}
