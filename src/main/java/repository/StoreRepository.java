package repository;

import model.Good;
import model.Provider;
import model.Store;
import utils.FileWriterUtility;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StoreRepository {
    private static final String PATH_TO_FILE = "C://Store.txt";
    private final Path path = Path.of(PATH_TO_FILE);
    private GoodRepository goodRepository = new GoodRepository();
    private ProviderRepository providerRepository = new ProviderRepository();
    private final List<Good> listOFGoods = new ArrayList<>();// хранит товар и его количество, берет инфу из гудрепозитория
    private final List<Provider> listOfProviders= new ArrayList<>();

// в классе отсутствует метод Create, так как имеем один Склад и с ним работаем
    /*
    public Store create (Store store) {
        try {
            Files.createFile(Paths.get("C://" + store.getName() + ".txt"));
            store.setId(++counter);
            store.setPathToFile("C://" + store.getName() + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return store;
    }
    */

    public void addGoodToStore(int idOfGood) {
        try {
            FileWriterUtility.writeLineToFile(idOfGood + "", PATH_TO_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deliteGoodFromStoreById(int id) {
        try {
            List<String> lines = Files.readAllLines(path);
            RandomAccessFile cleanFile = new RandomAccessFile(PATH_TO_FILE, "rw");
            cleanFile.setLength(0);
            for (String line:lines) {
                if (Integer.parseInt(line) != id) {
                    FileWriterUtility.writeLineToFile(line, PATH_TO_FILE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Good> readAllGoods (){
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
               Good good = goodRepository.readById(Integer.parseInt(line));
               listOFGoods.add(good);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(listOFGoods);// потом убрать
        return listOFGoods;
    }

    public List<Provider> readAllProviders(){
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Good good = goodRepository.readById(Integer.parseInt(line));
                Provider provider = providerRepository.readById(good.getProvider().getProviderId());
                listOfProviders.add(provider);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(listOfProviders);// потом убрать
        return listOfProviders;
    }

}
