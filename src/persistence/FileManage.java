package persistence;

import java.io.*;
import java.util.ArrayList;

public class FileManage {
    private String fileName;

    public FileManage(String fileName)
    {
        this.fileName = fileName;
    }

    private File getFile(){
        return new File("src/files/" + this.fileName);
    }

    public ArrayList<String> getDataOfFile(){
        ArrayList<String> lines = null;
        try {
            File file = this.getFile();
            if (file.exists()){
                lines = new ArrayList<>();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null){
                    lines.add(line);
                }
                br.close();
            }
        } catch (IOException e){
            e.printStackTrace(System.out);
        }

        return lines;
    }

    public void writeFile(String line){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFile(), true));
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void updateFile(String oldLine, String newLine){
        ArrayList<String> lines = this.getDataOfFile();
        if(lines != null){
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals(oldLine)){
                    lines.set(i, newLine);
                    this.writeLinesToFile(lines);
                    System.out.println("The line was updated successful");
                    break;
                }
            }
        }
    }

    public void deleteLineInFile(String line){
        ArrayList<String> lines = this.getDataOfFile();
        if(lines != null){
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals(line)){
                    lines.remove(i);
                    this.writeLinesToFile(lines);
                    System.out.println("The line was deleted successful");
                    break;
                }
            }
        }
    }

    private void writeLinesToFile(ArrayList<String> lines){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFile()));
            for (String line : lines){
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
