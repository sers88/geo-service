import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageSenderImplTest {

    @ParameterizedTest(name = "Test for {0}")
    @CsvSource(value = {"RUSSIA, Добро пожаловать, 172.123.12.19", "USA, Welcome, 96.123.12.19"})
    void sendTest(Country country, String expected, String ip) {
        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        when(geoService.byIp(anyString())).thenReturn(new Location(null, country, null, 0));
        when(localizationService.locale(Mockito.any())).thenReturn(expected);
        String actual = messageSender.send(headers);
        assertEquals(expected, actual);
    }
}
