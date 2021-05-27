import model.Good;
import model.Provider;
import org.junit.jupiter.api.*;
import repository.GoodRepository;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GoodRepositoryTest {
    private static final String PATH_TO_FILE = "C://listOfGoods.txt";
    private Good good;
    private GoodRepository goodRepository = new GoodRepository();


    @BeforeEach
    public void setBeforeEach() {
        goodRepository.create(new Good("Apple", "Food", 1000 ,1000 , new Provider(1)));
        goodRepository.create(new Good("Lemon", "Food", 1000 ,1000 , new Provider(1)));
        goodRepository.create(new Good("WaterMelon", "Food", 1000 ,1000 , new Provider(1)));

    }

    @DisplayName("Save good in the file and get id")
    @Test
    public void test_CreateGood(){
        Good good = new Good("Apple", "Food", 1000 ,1000 , new Provider(2));
        Good savedGood = goodRepository.create(good);
        assertEquals(savedGood.getId(), 4);
        assertEquals(savedGood.getName(), good.getName());
        assertEquals(savedGood.getType(), good.getType());
        assertEquals(savedGood.getPrice(), good.getPrice());
        assertEquals(savedGood.getQuantity(), good.getQuantity());
    }

    @DisplayName("Get good  by id from the file")
    @Test
    public void test_getGoodByIdFromTheFile() {
        Good good = new Good("Iphone", "Phone", 10000, 100, new Provider(2));
        goodRepository.create(good);
        Good savedGood = goodRepository.readById(4);
        assertEquals(savedGood.getId(), 4);
        assertNotNull(savedGood.getProvider());
        assertAll(() -> {
            assertEquals(savedGood.getName(), good.getName());
            assertEquals(savedGood.getPrice(), good.getPrice());
            assertEquals(savedGood.getType(), good.getType());
        });
    }

    @DisplayName("Get list of all goods")
    @Test
    public void test_getAll() {
        List<Good> list = goodRepository.readAll();
        assertEquals(list.size(), 3);
    }
    @DisplayName("Get good from file by id")
    @Test
    public void test_updateById() {
        int id = 3;
        Good good = new Good("Samsung", "TV", 100, 1, new Provider(1));
        goodRepository.updateById(id, good);
        Good updatedGood = goodRepository.readById(id);
        assertNotNull(updatedGood);
        assertAll(() -> {
            assertEquals(updatedGood.getName(), good.getName());
            assertEquals(updatedGood.getPrice(), good.getPrice());
            assertEquals(updatedGood.getType(), good.getType());
        });
    }
    @DisplayName("Delite good from file by id")
    @Test
    public void test_deleteById() {
        int id = 4;
        goodRepository.deleteById(id);
        assertThrows(NullPointerException.class, () -> goodRepository.readById(id));
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