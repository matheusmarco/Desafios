package desafiosidwall;

import java.util.regex.Pattern;

/**
 *
 * @author matheusmarchezan
 */
public class DesafiosIdwall {

    public static void main(String[] args) {
        int size = 20;
        String text = "In the beginning God created the heavens and the earth. Now the earth was \n"
                + "formless and empty, darkness was over the surface of the deep, and the Spirit of\n"
                + "God was hovering over the waters.\n"
                + "\n"
                + "And God said, \"Let there be light,\" and there was light. God saw that the light\n"
                + "was good, and he separated the light from the darkness. God called the light\n"
                + "\"day,\" and the darkness he called \"night.\" And there was evening, and there was\n"
                + "morning - the first day.";

        System.out.println("\nBreak Lines Words:\n" + breakLinesWords(text, size));
        System.out.println("\nJustify:\n" + justify(text, size));

    }
    
    //Teste 1 - função para quebrar as linhas com um número específico de caracteres. 
    public static String breakLinesWords(String text, int size){
        text = text.replaceAll("\n+|\\s+\n|\n\\s+|\\s+", " "); //remove espaços e quebras de linhas extras
        String [] words = text.split(" "); //quebra o texto em palavras
        StringBuilder sb = new StringBuilder();
        String temp = "";
        for(int i = 0; i < words.length; i++){ //percorre todas as palavras
            if(temp.length() + words[i].trim().length() <= size){ //verifica se a palavra cabe na linha
                temp += words[i].trim() + " ";
            }else{ //se não cabe, quebra a linha
                sb.append(temp.trim() + "\n");
                temp = words[i].trim() + " ";
            }
        }
        sb.append(temp.trim() + "\n"); //adiciona a ultima palavra
        return sb.toString();
    }

    //Teste 2 - função para justificar o alinhamento de cada linha contendo um número específico de caracteres 
    public static String justify(String text, int size) {
        String brokenText = breakLinesWords(text, size); //quebra as linhas
        String[] lines = brokenText.split("\n");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            String l = lines[i];
            int whiteSpaces = lines[i].length() - lines[i].replace(" ", "").length(); //número de espaços em branco
            int gapSpaces = (whiteSpaces != 0) ? (size - lines[i].length()) / whiteSpaces : 0; //calcula o número de espaços entre cada palavra
            int extra = (whiteSpaces != 0) ? (size - lines[i].length()) % whiteSpaces : (size - lines[i].length()); //calcula o número de espaços extras que serão adicionados
            String[] words = lines[i].split(" ");
            String white = " ";
            while (gapSpaces > 0) { //cria o gap no tamanho certo
                white += " ";
                gapSpaces--;
            }
            int padding = (whiteSpaces - extra) / 2; // calcula onde serão colocados os espaços extras
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < words.length; j++) { //adiciona as palavras e o gap
                line.append(words[j]);
                if (j < words.length - 1) {
                    line.append(white);
                    if (extra > 0 && j > padding) { //adiciona os espaços extras
                        line.append(" ");
                        extra--;
                    }
                }
            }
            line.append("\n");
            result.append(line);
        }
        return result.toString();
    }
}
