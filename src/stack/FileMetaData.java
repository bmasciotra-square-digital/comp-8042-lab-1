package stack;

public class FileMetaData {
    public int lineNumber;
    public int characterNumber;
    public String line;
    public char bracket;
    public BracketPosition position;
    public BracketType type;

    public FileMetaData(String line, int characterNumber, int lineNumber, char bracket, BracketPosition position, BracketType type) {
        this.lineNumber = lineNumber;
        this.line = line;
        this.characterNumber = characterNumber;
        this.bracket = bracket;
        this.position = position;
        this.type = type;
    }
}
