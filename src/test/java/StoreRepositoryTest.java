import model.Good;
import model.Provider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.ProviderRepository;
import repository.StoreRepository;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StoreRepositoryTest {
    private static final String PATH_TO_FILE = "C://Store.txt";
    private Good good;
    private final StoreRepository storeRepository = new StoreRepository();

    @BeforeEach
    public void setBeforeEach() {
        storeRepository.addGoodToStore(1);
        storeRepository.addGoodToStore(2);
        storeRepository.addGoodToStore(4);
    }

    @DisplayName("Add good to store")
    @Test
    public void test_addGoodToStore() {
        storeRepository.addGoodToStore(1);
        long  lineCount;
        try {
            lineCount = Files.lines(Path.of(PATH_TO_FILE)).count();
            assertEquals((int) lineCount, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @DisplayName("Delite good from store")
    @Test
    public void test_deliteGoodFromStoreById() {
        storeRepository.deliteGoodFromStoreById(1);
        long  lineCount;
        try {
            lineCount = Files.lines(Path.of(PATH_TO_FILE)).count();
            assertEquals((int) lineCount, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Get list of all providers")
    @Test
    public void test_readAllGoods() {
        List<Good> list = storeRepository.readAllGoods();
        assertEquals(list.size(), 3);
        assertEquals(list.get(2).getClass(), Good.class);

    }

    @DisplayName("Get list of all providers")
    @Test
    public void readAllProviders() {
        List<Provider> list = storeRepository.readAllProviders();
        assertEquals(list.size(), 3);
        assertEquals(list.get(2).getClass(), Provider.class);
        assertNotNull(list.get(2).getListOfGoodId());

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
