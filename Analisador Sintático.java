//Arllon De Fraga Dutra - 01358091

import java_cup.runtime.*;

// Implementação do Scanner
class Scanner implements java_cup.runtime.Scanner {
    private String input;
    private int position;

    public Scanner(String input) {
        this.input = input;
        this.position = 0;
    }

    public Symbol next_token() throws java.lang.Exception {
        // Ignora espaços em branco
        while (position < input.length() && Character.isWhitespace(input.charAt(position))) {
            position++;
        }

        // Verifica se chegou ao final do texto
        if (position >= input.length()) {
            return new Symbol(sym.EOF);
        }

        // Reconhece tokens de números
        if (Character.isDigit(input.charAt(position))) {
            int start = position;
            while (position < input.length() && Character.isDigit(input.charAt(position))) {
                position++;
            }
            String lexeme = input.substring(start, position);
            return new Symbol(sym.NUMBER, Integer.parseInt(lexeme));
        }

        // Reconhece tokens de operadores e parênteses
        char ch = input.charAt(position);
        position++;
        switch (ch) {
            case '+':
                return new Symbol(sym.PLUS);
            case '-':
                return new Symbol(sym.MINUS);
            case '*':
                return new Symbol(sym.TIMES);
            case '/':
                return new Symbol(sym.DIVIDE);
            case '(':
                return new Symbol(sym.LPAREN);
            case ')':
                return new Symbol(sym.RPAREN);
            default:
                throw new Exception("Token inválido: " + ch);
        }
    }
}

// Implementação do analisador sintático
public class MyParser {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner("2 + 3 * (4 - 1)");
            parser p = new parser(scanner);
            Symbol result = p.parse();
            System.out.println("Análise sintática concluída com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
