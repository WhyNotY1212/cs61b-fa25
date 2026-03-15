import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntListOptionalTests {
    // TBD
    @Test
    public void testAddFirst() {
        IntList list = new IntList(10, null);
        list.addFirst(5);
        assertEquals(5, list.first); // 检查头部元素是否是5
        assertEquals(10, list.rest.first); // 检查第二个元素是否是10
    }
}
