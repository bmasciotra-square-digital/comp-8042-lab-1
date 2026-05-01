package stack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaVerifier<T> {
    private final ArrayListStack<FileMetaData> stack;

    public JavaVerifier() {
        this.stack = new ArrayListStack<>();
    }

    public void run(File file) throws Exception {
        parseAndVerify(file);
    }

    private static String locationString(FileMetaData m) {
        return "line " + m.lineNumber + ", column " + (m.characterNumber + 1);
    }

    private void parseAndVerify(File file) throws Exception {
        // regex pattern to match any type of bracket
        Pattern pattern = Pattern.compile("[(){}\\[\\]]");

        try (Scanner reader = new Scanner(file)) {
            {
                int lineNumber = 1;

                while (reader.hasNextLine()) {
                    String next = reader.nextLine();
                    Matcher matcher = pattern.matcher(next);

                    // Find all the matches and push the file data structure to the stack
                    while (matcher.find()) {
                        char bracket = next.charAt(matcher.start());
                        stack.BracketSpecs specs = deferBracketSpecs(bracket);

                        FileMetaData location = new FileMetaData(next, matcher.start(), lineNumber, next.charAt(matcher.start()), specs.position(), specs.type());

                        // A right bracket signifies a closing bracket
                        if (location.position == BracketPosition.Right) {
                            if (this.stack.empty()) {
                                throw new Exception("closing bracket with no matching opener (" + locationString(location) + ")");
                            }

                            // Always pop so that the stack is aligned with the parsed data stream
                            FileMetaData previous = this.stack.pop();

                            // once popped if, since we have been popping as we go, this type should be aligned with the opening bracket
                            if (previous.type != location.type) {
                                throw new Exception("bracket type mismatch: expected " + previous.type
                                        + ", found " + location.type + " (" + locationString(location) + ")");
                            }
                        }

                        // After the checks, push to the stack (pushing opening brackets)
                        if (location.position == BracketPosition.Left) {
                            stack.push(location);
                        }
                    }

                    lineNumber++;
                }

                // If the stack is not empty then there is an open bracket
                if (!this.stack.empty()) {
                    FileMetaData unclosed = this.stack.peek();
                    throw new Exception("unclosed opening bracket '" + unclosed.bracket + "' (" + locationString(unclosed) + ")");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            throw e;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    private BracketSpecs deferBracketSpecs(char bracket) {
        BracketType type = null;
        BracketPosition position = switch (bracket) {
            case '(' -> {
                type = BracketType.Regular;
                yield BracketPosition.Left;
            }
            case ')' -> {
                type = BracketType.Regular;
                yield BracketPosition.Right;
            }
            case '{' -> {
                type = BracketType.Curly;
                yield BracketPosition.Left;
            }
            case '}' -> {
                type = BracketType.Curly;
                yield BracketPosition.Right;
            }
            case '[' -> {
                type = BracketType.Square;
                yield BracketPosition.Left;
            }
            case ']' -> {
                type = BracketType.Square;
                yield BracketPosition.Right;
            }
            default -> null;
        };

        return new BracketSpecs(type, position);
    }
}
