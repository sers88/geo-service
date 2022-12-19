import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {

    @ParameterizedTest(name = "Test for {0}")
    @CsvSource(value = {"RUSSIA, Добро пожаловать", "USA, Welcome"})
    protected void testLocale(Country country, String expected) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String actual = localizationService.locale(country);
        assertEquals(expected, actual);
    }
}
