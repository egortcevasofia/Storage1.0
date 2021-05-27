import model.Good;
import model.Provider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.GoodRepository;
import repository.ProviderRepository;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProviderRepositoryTest {
    private static final String PATH_TO_FILE = "C://listOfProviders.txt";
    private Good good;
    private final ProviderRepository providerRepository = new ProviderRepository();

    @BeforeEach
    public void setBeforeEach() {
        providerRepository.create(new Provider("Danone"));
        providerRepository.create(new Provider("Indesit"));
        providerRepository.create(new Provider("HP"));

    }


    @DisplayName("Save provider in the file and get id")
    @Test
    public void test_CreateProvider(){
        Provider provider = new Provider("Toyota");
        Provider savedProvider = providerRepository.create(provider);
        assertEquals(savedProvider.getProviderId(), 4);
        assertEquals(savedProvider.getName(), provider.getName());
    }

    @DisplayName("Get provider  by id from the file")
    @Test
    public void test_getProviderByIdFromTheFile() {
        providerRepository.create(new Provider("Toyota"));
        Provider savedProvider = providerRepository.readById(4);
        assertAll(() -> {
            assertEquals(savedProvider.getProviderId(), 4);
            assertEquals(savedProvider.getName(), "Toyota");
        });
        assertEquals(savedProvider.getListOfGoodId().size(), 2);
    }
    @DisplayName("Get provider from file by id")
    @Test
    public void test_updateById() {
        int id = 3;
        Provider provider = new Provider("Toyota");
        providerRepository.updateById(id, provider);
        Provider updatedProvider = providerRepository.readById(id);
        assertAll(() -> {
            assertEquals(updatedProvider.getName(), provider.getName());
            assertEquals(updatedProvider.getProviderId(), provider.getProviderId());
        });
    }
    @DisplayName("Get list of all providers")
    @Test
    public void test_getAll() {
        List<Provider> list = providerRepository.readAll();
        assertEquals(list.size(), 3);
    }
    @DisplayName("Delite provider from file by id")
    @Test
    public void test_deleteById() {
        int id = 3;
        providerRepository.deliteById(id);
        assertThrows(NullPointerException.class, () -> providerRepository.readById(id));
    }



    @AfterEach
    public void cleanAfterTest() {
        RandomAccessFile cleanFile = null;
        try {
            cleanFile = new RandomAccessFile(PATH_TO_FILE, "rw");
            cleanFile.setLength(0);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
