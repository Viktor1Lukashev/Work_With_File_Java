import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1 - Найти определенное слово в файле: ");
        System.out.println("2 - Посчитать сколько в файле слов,знаков пунктуации, цифр ");
        System.out.println("3 - Прочитать файл ");
        System.out.println("4 - Заменить слово в файле ");
        System.out.println("5 - Посчитать сколько раз встречается слово в файле ");
        System.out.println("6 - Записать содержимое 4-х файлов в отдельный файл (.txt)");
        try{
            int s = Integer.parseInt(sc.nextLine());
            WorkWithFiles files = new WorkWithFiles();
            FileReader reader = null;
            switch (s){
                case 1:
                    System.out.println("Введите путь к файлу в котором вы хотите искать слово: ");
                    String path = sc.nextLine();
                    BufferedReader buffreader = createStremFiles(path);
                    System.out.println("Введите слово для поиска: ");
                    String wordForFinding = sc.nextLine();
                    files.findWordInFile(buffreader,path,wordForFinding);
                    break;
                case 2:
                    System.out.println("Введите путь к файлу чтобы увидеть его седержимое: ");
                    String filesPath = sc.nextLine();
                    BufferedReader bufreaders = createStremFiles(filesPath);
                    files.findAndRead(bufreaders,filesPath);
                    files.CountWordNumber(bufreaders,filesPath);
                    break;
                case 3:
                    System.out.println("Введите путь к файлу чтобы увидеть его седержимое: ");
                    String filePath = sc.nextLine().toLowerCase();
                    BufferedReader bufreader = createStremFiles(filePath);
                    files.findAndRead(bufreader,filePath);
                    break;
                case 4:
                    String pathToNewFile = "C:\\файл c замененными словами.txt";
                    System.out.println("Введите путь к файлу в котором будет производиться поиск и замена слова: ");
                    String pathToFile = sc.nextLine().toLowerCase();
                    System.out.println("слово для поиска: ");
                    String wordForFind = sc.nextLine().toLowerCase();
                    System.out.println("Введите слово для замены: ");
                    String changeWord = sc.nextLine().toLowerCase();
                    BufferedReader bufferedReader = createStremFiles(pathToFile);
                    BufferedWriter writer = createStremWriteFiles(pathToNewFile);
                    files.changeWord(bufferedReader,writer,pathToFile,wordForFind,changeWord);
                 case 5:
                     System.out.println("Введите путь к  файлу: ");
                     String path1 = sc.nextLine();
                     BufferedReader buffreader1 = createStremFiles(path1);
                     System.out.println("Введите слово для поиска: ");
                     String wordForFinding1 = sc.nextLine();
                     files.findWordInFile(buffreader1,path1,wordForFinding1);
                     break;
                case 6:
                    ArrayList<String> array = new ArrayList<>();
                    System.out.println("Введите путь к 1-му файлу: ");
                    array.add(sc.nextLine());
                    System.out.println("Введите путь к 2-му файлу: ");
                    array.add(sc.nextLine());
                    System.out.println("Введите путь к 3-му файлу: ");
                    array.add(sc.nextLine());
                    System.out.println("Введите путь к 4-му файлу: ");
                    array.add(sc.nextLine());
                    System.out.println("Введите путь к выходному файлу файлу: ");
                    String fileOutName = sc.nextLine();
                    ArrayList<BufferedReader>bReaders = new ArrayList<>();
                    bReaders.add(createStremFiles(array.get(0)));
                    bReaders.add(createStremFiles(array.get(1)));
                    bReaders.add(createStremFiles(array.get(2)));
                    bReaders.add(createStremFiles(array.get(3)));
                    BufferedWriter bwriter = createStremWriteFiles(fileOutName);
                    int countBytes = files.CopyAllFilesInOne(bReaders,bwriter);
                    System.out.println("Было перенесено - " + countBytes + " байт(а)");
                    break;
            }

        }
        catch(Exception ex) {
            ex.getMessage();
        }
    }
    //создает буферизированный файловый входной поток поток из пути к файлу
    private static BufferedReader createStremFiles(String path) {
        BufferedReader bufstr = null;
        Scanner sc = new Scanner(System.in);
        try{
            FileReader reader= new FileReader(path);
            bufstr = new BufferedReader(reader);
        }catch (Exception ex){
            ex.getMessage();
        }
        return bufstr;
    }
    //создает буферизированный файловый выходной поток
    private static BufferedWriter createStremWriteFiles(String path) {
        BufferedWriter writer = null;
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter write= new FileWriter(path);
            writer = new BufferedWriter(write);
        }catch (Exception ex){
            ex.getMessage();
        }
        return writer;
    }

}
