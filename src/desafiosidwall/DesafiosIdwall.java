/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiosidwall;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author matheusmarchezan
 */
public class DesafiosIdwall {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int size = 40;
        String text = "In the beginning God created the heavens and the earth. Now the earth was \n"
                + "formless and empty, darkness was over the surface of the deep, and the Spirit of\n"
                + "God was hovering over the waters.\n"
                + "\n"
                + "And God said, \"Let there be light,\" and there was light. God saw that the light\n"
                + "was good, and he separated the light from the darkness. God called the light\n"
                + "\"day,\" and the darkness he called \"night.\" And there was evening, and there was\n"
                + "morning - the first day.";

        System.out.println("Break Lines:\n" + braekLines(text, size));
        System.out.println("\nJustify:\n" + justify(text, size));

    }

    //Teste 1 - função para quebrar as linhas com um número específico de caracteres. 
    public static String braekLines(String text, int size) {
        int currentIndex = 0;
        StringBuilder sb = new StringBuilder();
        text = text.replaceAll("\n", " ");
        if (text.length() < size) {
            return text;
        }

        while ((currentIndex + size) < text.length()) { //enquanto não chegar na última linha
            if (Character.isLetter(text.charAt(currentIndex + size)) || Character.isDigit(currentIndex + size)) { //se for uma lerta ou um dígito
                if (Pattern.matches("\\p{Punct}", String.valueOf(text.charAt(currentIndex + size + 1)))
                        || Character.isLetter(text.charAt(currentIndex + size + 1))
                        || Character.isDigit(currentIndex + size)) { //se o próximo for uma letra ou um tipo de ponto ou um dígito
                    for (int i = currentIndex + size; i > currentIndex; i--) { //retorna até encontrar um espeço em branco
                        if (text.charAt(i) == ' ' || text.charAt(i) == '\r' || text.charAt(i) == '\n' || text.charAt(i) == '\t') {//quando encontrar quebra a linha
                            sb.append(text.substring(currentIndex, i).trim());
                            sb.append("\n");
                            currentIndex = i;
                        }
                    }
                } else { //caso contrário será espeço em branco, portanto quebra alinha
                    sb.append(text.substring(currentIndex, currentIndex + size + 1).trim());
                    sb.append("\n");
                    currentIndex += size + 1;
                }
            } else { //se não for letra nem digito, quebra a linha
                sb.append(text.substring(currentIndex, currentIndex + size).trim());
                sb.append("\n");
                currentIndex += size;
            }

        }
        String rest = text.substring(currentIndex, text.length()).trim();
        sb.append(rest);
        return sb.toString();
    }

    //Teste 2 - função para justificar o alinhamento de cada linha contendo um número específico de caracteres 
    public static String justify(String text, int size) {
        String brokenText = braekLines(text, size); //quebra as linhas
        String[] lines = brokenText.split("\n");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            int whiteSpaces = lines[i].length() - lines[i].replace(" ", "").length(); //número de espaços em branco
            int gapSpaces = (size - lines[i].length()) / whiteSpaces; //calcula o número de espaços entre cada palavra
            int extra = (size - lines[i].length()) % whiteSpaces; //calcula o número de espaços extras que serão adicionados
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
                line.append(white);
                if (extra > 0 && j > padding) { //adiciona os espaços extras
                    line.append(" ");
                    extra--;
                }
            }
            line.append("\n");
            result.append(line);
        }
        return result.toString();
    }
}
