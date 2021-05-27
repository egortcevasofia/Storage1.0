package repository;

import model.Provider;
import utils.FileWriterUtility;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProviderRepository {

    private static final String PATH_TO_FILE = "C://listOfProviders.txt";
    private static int counter = 0;
    private Provider provider = new Provider();

    private Path path = Path.of(PATH_TO_FILE);
    GoodRepository goodRepository = new GoodRepository();


    public Provider create(Provider provider) {
        try {
            if (FileWriterUtility.doesFileNotExist(PATH_TO_FILE)){
                Files.createFile(Path.of(PATH_TO_FILE));
            }
            List<String> lines = Files.readAllLines(path);
            for(String line : lines){
                int idFromFile = FileWriterUtility.parseId(line);
                if (idFromFile > counter) {
                    counter = idFromFile;
                }
                System.out.println(idFromFile); // потом убрать
            }
            provider.setProviderId(++counter);
            FileWriterUtility.writeLineToFile(provider.toString(), PATH_TO_FILE);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return provider;
    }


    //////////////////////////////////////////////////////////////

    public Provider readById(int id) {
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                int idFromFile = FileWriterUtility.parseId(line);
                if (idFromFile == id) {
                    provider =  parseProviderFromLine(line);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        GoodRepository goodRepository = new GoodRepository();// можно ли вынести в контроллер
        List<Integer> listOfGoodId = goodRepository.readAllIdByProviderId(id);
        provider.setListOfGoodId(listOfGoodId);
        System.out.println(listOfGoodId);// потом убрать
        return provider;
    }
    //////////////////////////////////////////////

    public List<Provider> readAll() {
        final List<Provider> providerListReadAll = new ArrayList<Provider>();
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
               // GoodRepository goodRepository = new GoodRepository();///Не могу не сделать этого так как каждый раз все едобавляется в один лист товаров
                //List<Integer> listOfGoodId = goodRepository.readAllIdByProviderId(FileWriterUtility.parseId(line));
                provider =  parseProviderFromLine(line);
                provider.setListOfGoodId(goodRepository.readAllIdByProviderId(FileWriterUtility.parseId(line)));
                providerListReadAll.add(provider);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(providerListReadAll);// потом убрать
        for (Provider p:providerListReadAll) {
            System.out.println(p.getListOfGoodId());
        }//потом убрать
        return providerListReadAll;
    }

    /////////////////////////////////////

    public void updateById(int id, Provider provider) {
        try {
            List<String> lines = Files.readAllLines(path);
            RandomAccessFile cleanFile = new RandomAccessFile(PATH_TO_FILE, "rw");
            cleanFile.setLength(0);
            for (String line:lines) {
                int idFromFile = FileWriterUtility.parseId(line);
                if (idFromFile != id) {
                    FileWriterUtility.writeLineToFile(line, PATH_TO_FILE);
                } else {
                    provider.setProviderId(id);
                    FileWriterUtility.writeLineToFile(provider.toString(), PATH_TO_FILE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////
    public void deliteById(int id) {
        try {
            List<String> lines = Files.readAllLines(path);
            RandomAccessFile cleanFile = new RandomAccessFile(PATH_TO_FILE, "rw");
            cleanFile.setLength(0);
            for (String line:lines) {
                int idFromFile = FileWriterUtility.parseId(line);
                if (idFromFile != id) {
                    FileWriterUtility.writeLineToFile(line, PATH_TO_FILE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Provider parseProviderFromLine(String line) {
        int providerId = 0;
        String name = "";
        Pattern pattern = Pattern.compile("(\\d+)\\s(\\w+).*");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            providerId = Integer.parseInt(matcher.group(1));
            name = matcher.group(2);
                    }
        Provider provider = new Provider(providerId, name);
        return provider;
    }




}
