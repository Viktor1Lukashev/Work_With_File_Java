import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

//за решение каждого задания отвечает отдельный метод в классе WorkWithFiles
public class WorkWithFiles {
    private Scanner sc;
    //локальный метод для проверки возможности чтения файла и проверки что объект - это файл
    private boolean checkFile(String path) {
        File file = new File(path);
        if (file.isFile() && file.canRead()) {
            return true;
        }
        return false;
    }
    //локальный вспомогательный метол для разбивки строк в массив и сравнения с заданным словом
    private int stringToArray(String str, String word) {
        int count = 0;
        String[] arr = str.split(" ");
        for (String string : arr) {
            if (string.equals(word)) {
                count++;
            }
        }
        return count;
    }
    //конструктор
    public WorkWithFiles() { }
    //чтение содержимого файла по заданному пути
    public void findAndRead(BufferedReader reader, String path) {
        if (checkFile(path)) {
            try {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception ex) {
                ex.getMessage();
            } finally {
                try {
                    reader.close();
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        }
    }
    //поиск слова в файле
    public void findWordInFile(BufferedReader reader, String path, String findWord) {
        int count = 0;
        if (checkFile(path)) {
            try {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    String[] arr = line.split(" ");
                    for (String s : arr) {

                        if (s.toLowerCase().equals(findWord.toLowerCase())) {
                            count++;
                        }
                    }
                }
                System.out.println("Слово " + findWord + " встрчается в вашем файле " + count + " раз(а) ");
            } catch (Exception ex) {
                ex.getMessage();
            } finally {
                try {
                    reader.close();
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        }
    }
    //подсчет количества букв,цифр, и знаков пунктуации в файле(для демонстрации - не все знаки пунктуации)
    public void CountWordNumber(BufferedReader reader, String path) {
        if (checkFile(path)) {
            try {
                String line = "";
                int countNumber = 0;
                int countLetter = 0;
                int countPunctuation = 0;
                while ((line = reader.readLine()) != null) {
                    char[] arr = line.toCharArray();
                    for (int i = 0; i < arr.length; ++i) {
                        if (arr[i] == ',' || arr[i] == '.' || arr[i] == '!' || arr[i] == '?') {
                            countPunctuation++;
                        } else if (Character.isDigit(arr[i])) {
                            countNumber++;
                        } else {
                            countLetter++;
                        }

                    }
                }
                System.out.println("знаков препинания в файле - " + countPunctuation);
                System.out.println("чисел в файле - " + countNumber);
                System.out.println("букв в файле - " + countLetter);
            } catch (Exception ex) {
                ex.getMessage();
            } finally {
                try {
                    reader.close();
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        }
    }
    //метод заменяет слово в файле одно на другое.
    public void changeWord(BufferedReader reader, BufferedWriter writer, String path, String wordForFind, String wordChange) {
        if (checkFile(path)) {
            int countEqualsString = 0;
            try {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    countEqualsString += stringToArray(line, wordForFind);
                    if (countEqualsString > 0) {
                        String newStr = line.replace(wordForFind, wordChange);
                        writer.write(newStr);
                        writer.write("\n");
                    }
                    //

                }
                System.out.println("было произведено - " + countEqualsString + " замен(ы)");
            } catch (Exception ex) {
                ex.getMessage();
            } finally {
                try {
                    reader.close();
                    writer.close();
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        }
    }
    //метод записывает содержимое 4-х файлов в отдельный файл
    public int CopyAllFilesInOne(ArrayList<BufferedReader> list, BufferedWriter writer) {
        int countBytes=0;
        try {
            String line = "";
            for (int i = 0; i < list.size(); i++) {
                while ((line = list.get(i).readLine()) != null) {
                    byte[] arr = line.getBytes();
                    countBytes += arr.length;
                    writer.write(line);
                    writer.newLine();

                }
            }
            return countBytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
        return countBytes;
    }
}
