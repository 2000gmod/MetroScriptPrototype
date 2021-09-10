package metroscript;

public class Token {
    private TokenType type;

    private int intValue;
    private double doubleValue;
    private boolean boolValue;
    private String stringValue;
    private String identifierName;

    public static final String[] keywords = {
            "int", "double", "bool", "string",
            "for", "while", "if", "else",
            "and", "or", "not"
    };

    public String getStringValue() {
        return stringValue;
    }

    public String getIdentifierName() {
        return identifierName;
    }



    @Override
    public String toString() {
        String out = type.toString();
        switch (type) {
            case INT_LIT: out += ": " + intValue; break;
            case DOUBLE_LIT: out += ": " + doubleValue; break;
            case BOOL_LIT: out += ": " + boolValue; break;
            case STRING_LIT: out += ": \"" + stringValue + "\""; break;
            case IDENTIFIER: out += ": " + identifierName; break;
            default:
        }
        return out;
    }

    public TokenType getType() {
        return type;
    }

    public int getIntValue() {
        return intValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public boolean isBoolValue() {
        return boolValue;
    }

    public Token(String token) {
        //if(token.charAt(0) == ' ') token = token.substring(1);
        if (token.charAt(0) == '\"' && token.charAt(token.length() - 1) == '\"' && token.length() > 1) {
            type = TokenType.STRING_LIT;
            stringValue = token.substring(1, token.length() - 1);
            return;
        }

        try {
            intValue = Integer.parseInt(token);
            type = TokenType.INT_LIT;
            return;
        }
        catch (NumberFormatException ignored) {
        }

        try {
            doubleValue = Double.parseDouble(token);
            type = TokenType.DOUBLE_LIT;
            return;
        }
        catch (NumberFormatException ignored) {
        }

        switch (token) {
            default:
                if (isValidIdentifier(token)) {
                    type = TokenType.IDENTIFIER;
                    identifierName = token;
                }
                else {
                    type = TokenType.ERROR;
                }
                break;
            case "true":
                boolValue = true;
                type = TokenType.BOOL_LIT;
                break;
            case "false":
                boolValue = false;
                type = TokenType.BOOL_LIT;
                break;
            case "+":
                type = TokenType.PLUS;
                break;
            case "-":
                type = TokenType.MINUS;
                break;
            case "*":
                type = TokenType.STAR;
                break;
            case "/":
                type = TokenType.SLASH;
                break;
            case ",":
                type = TokenType.COMMA;
                break;
            case "=":
                type = TokenType.ASSIGN;
                break;
            case "==":
                type = TokenType.EQ;
                break;
            case "!=":
                type = TokenType.NOT_EQ;
                break;
            case "<":
                type = TokenType.LT;
                break;
            case ">":
                type = TokenType.GT;
                break;
            case "<=":
                type = TokenType.LEQ;
                break;
            case ">=":
                type = TokenType.GEQ;
                break;
            case ";":
                type = TokenType.SEMICOLON;
                break;
            case "and":
            case "&&":
                type = TokenType.AND;
                break;
            case "or":
            case "||":
                type = TokenType.OR;
                break;
            case "not":
            case "!":
                type = TokenType.NOT;
                break;
            case "(":
                type = TokenType.LEFT_PAREN;
                break;
            case ")":
                type = TokenType.RIGHT_PAREN;
                break;
            case "[":
                type = TokenType.LEFT_SQR;
                break;
            case "]":
                type = TokenType.RIGHT_SQR;
                break;
            case "{":
                type = TokenType.LEFT_CUR;
                break;
            case "}":
                type = TokenType.RIGHT_CUR;
                break;
            case "if":
                type = TokenType.IF;
                break;
            case "else":
                type = TokenType.ELSE;
                break;
            case "for":
                type = TokenType.FOR;
                break;
            case "while":
                type = TokenType.WHILE;
                break;
            case "int":
                type = TokenType.INT;
                break;
            case "double":
                type = TokenType.DOUBLE;
                break;
            case "bool":
                type = TokenType.BOOL;
                break;
            case "string":
                type = TokenType.STRING;
                break;
        }
    }

    public static boolean isValidIdentifier(String id) {
        if (Character.isDigit(id.charAt(0))) return false;

        for (String keyword : keywords) {
            if (id.equals(keyword)) return false;
        }
        for (char c : id.toCharArray()) {
            if (!(Character.isLetterOrDigit(c) || (c == '_')) || c == ';' || c == '\"') {
                return false;
            }
        }
        return true;
    }
}