import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeoServiceImplTest {

    @ParameterizedTest(name = "Test for {0}")
    @CsvSource(value = {"127.0.0.1, null", "172.0.32.11, Moscow", "96.44.183.149, New York", "172., Moscow", "96., New York"})
    protected void testByIp(String ip, String expected) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(ip);

        if (expected.equals("null")) {
            expected = null;
        }
        assertEquals(expected, location.getCity());

    }

    @Test
    protected void testByCoordinates() throws RuntimeException {
        GeoServiceImpl geoService = new GeoServiceImpl();
        assertThrows(RuntimeException.class, () -> geoService.byCoordinates(0, 0));
    }

}
