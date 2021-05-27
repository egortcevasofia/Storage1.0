package repository;

import model.Good;
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

public class GoodRepository {
    private static final String PATH_TO_FILE = "C://listOfGoods.txt";
    private static int counter = 0;
    private Good good = null;
    private final List<Good> goodListReadAll = new ArrayList<Good>();
    private final List<Good> goodListReadAllByType = new ArrayList<Good>();
    private  final List<Integer> listOfGoodId = new ArrayList<Integer>();
    private Path path = Path.of(PATH_TO_FILE);


    // if you create new object it must be write in the end of the file
    public Good create(Good good) {
        try {
            if (FileWriterUtility.doesFileNotExist(PATH_TO_FILE)){
                Files.createFile(Path.of(PATH_TO_FILE));
            }

            List<String> lines = Files.readAllLines(path);// вынести в отдельный метод , будет храниться в этом классе и будет приватный
            for(String line : lines){
                int idFromFile = FileWriterUtility.parseId(line);
                if (idFromFile > counter) {
                    counter = idFromFile;
                }
                System.out.println(idFromFile); // потом убрать
            }
            good.setId(++counter);
            FileWriterUtility.writeLineToFile(good.toString(), PATH_TO_FILE);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return good;
    }




//////////////////////////////////////////////////////////////



    public Good readById(int id) {
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                int idFromFile = FileWriterUtility.parseId(line);
                if (idFromFile == id) {
                    good =  parseGoodFromLine(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(good.toString());// потом убрать
        return good;
    }

    /////////////////////////////////////////////////////////////
    public List<Good> readAll() {
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                good = parseGoodFromLine(line);
                goodListReadAll.add(good);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(goodListReadAll);// потом убрать
        return goodListReadAll;
    }
    ///////////////////////////////////////////////////////////////
    public List<Good> readAllByType(String type) {
        try {
            List<String> lines = Files.readAllLines(path);
            for(String line : lines) {
                String typeFromList = parseType(line);
                if (typeFromList.equalsIgnoreCase(type)) {
                    Good good = parseGoodFromLine(line);
                    goodListReadAllByType.add(good);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(goodListReadAllByType);
        return goodListReadAllByType;
    }


///////////////////////////////////////////////////////////

    public void updateById(int id, Good good) {
        try {
            List<String> lines = Files.readAllLines(path);
            RandomAccessFile cleanFile = new RandomAccessFile(PATH_TO_FILE, "rw");
            cleanFile.setLength(0);
            for (String line:lines) {
                int idFromFile = FileWriterUtility.parseId(line);
                if (idFromFile != id) {
                    FileWriterUtility.writeLineToFile(line, PATH_TO_FILE);
                } else {
                    good.setId(id);
                    FileWriterUtility.writeLineToFile(good.toString(), PATH_TO_FILE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////
    public void deleteById(int id) {
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



    private Good parseGoodFromLine(String line) {
        int id = 0;
        String name = "";
        String type = "";
        double price = 0.0;
        int quantity = 0;
        int providerId = 0;
        Pattern pattern = Pattern.compile("(\\d+)\\s(\\w+)\\s(\\w+)\\s(\\d+\\.\\d+|\\d+)\\s(\\d+)\\s(\\d+)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            id = Integer.parseInt(matcher.group(1));
            name = matcher.group(2);
            type = matcher.group(3);
            price = Double.parseDouble(matcher.group(4));
            quantity = Integer.parseInt(matcher.group(5));
            providerId = Integer.parseInt(matcher.group(6));
        }
        Provider provider = new Provider();
        provider.setProviderId(providerId);
        Good good = new Good(name, type, price, quantity, provider);
        good.setId(id);
        return good;
    }


    private String parseType(String line) {//выносить в отдельный метод или нет
        String type = null;
        Pattern pattern = Pattern.compile("\\d+\\s\\w+\\s(\\w+).*");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            type = matcher.group(1);
        }
        return type;
    }

    private int parseProviderId(String line) {//выносить в отдельный метод или нет
        String providerId = null;
        Pattern pattern = Pattern.compile("(\\d+\\.\\d+|\\d+|\\s|\\w+)*(\\d+)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            providerId = matcher.group(2);
        }
        return Integer.parseInt(providerId);
    }



    public List<Integer> readAllIdByProviderId(int providerId) {
        try {
            List<String> lines = Files.readAllLines(path);
            for(String line : lines) {
                int providerIdFromList = parseProviderId(line);
                if (providerIdFromList == providerId) {
                    int idGood = FileWriterUtility.parseId(line);
                    listOfGoodId.add(idGood);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfGoodId;
    }



}
