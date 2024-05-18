import java.util.*;
public class MagicMachine {
    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }
    public static String repeatEachChar(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) { sb.append(c).append(c);
        } return sb.toString();
    }
    public static String repeatString(String input) {
        return input + input;
    }
    public static String shiftRight(String input) {
        return input.substring(input.length() - 1) + input.substring(0, input.length() - 1);
    } public static String replaceWithAlphabetChars(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            sb.append((char)(123 - (c - 'a')));
        }
        return sb.toString();
    }
    public static String combineStrings(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int maxLen = Math.max(s1.length(), s2.length());
        for (int i = 0; i < maxLen; i++) {
            if (i < s1.length()) { sb.append(s1.charAt(i));
            }
            if (i < s2.length()) { sb.append(s2.charAt(i));
            }
        }
        return sb.toString();
    }
    public static String reverseAndAppend(String s1, String s2) {
        return s1 + reverseString(s2);
    }
    public static String alternateChars(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int maxLen = Math.max(s1.length(), s2.length());
        for (int i = 0; i < maxLen; i++) {
            if (i < s1.length()) { sb.append(s1.charAt(i));
            } if (i < s2.length()) { sb.append(s2.charAt(s2.length() - 1 - i));
            }
        }
        return sb.toString(); }
    public static String returnFirstOrSecond(String s1, String s2) {
        return s1.length() % 2 == 0 ? s1 : s2; }
    public static String addChars(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int maxLen = Math.max(s1.length(), s2.length());
        for (int i = 0; i < maxLen; i++) {
            if (i < s1.length()) { sb.append((char)((s1.charAt(i) - 'a' + s2.charAt(i % s2.length()) - 'a') % 26 + 'a'));
            }
            else { sb.append(s2.charAt(i));
            }
        }
        return sb.toString();
    }
    public static String processInput(int[][] machine, String input) {
        String current = input;
        for (int i = 0; i < machine.length; i++) {
            for (int j = 0; j < machine.length; j++) {
                int function = machine[i][j];
                if (i == machine.length - 1 && j == machine.length - 1) {
                    return current;
                } else if (i == machine.length - 1 || j == machine.length - 1) { switch (function) {
                    case 1: current = reverseString(current);
                    break;
                    case 2: current = repeatEachChar(current);
                    break;
                    case 3: current = repeatString(current);
                    break;
                    case 4: current = shiftRight(current);
                    break;
                    case 5: current = replaceWithAlphabetChars(current);
                    break;
                }
                if (i == machine.length - 1) {
                    current = current.substring(0, current.length() / 2);
                } else { current = current.substring(current.length() / 2);
                }
                } else { String left = current.substring(0, current.length() / 2);
                    String right = current.substring(current.length() / 2);
                    switch (function) { case 1: current = combineStrings(left, right);
                        break;
                        case 2: current = reverseAndAppend(left, right);
                        break;
                        case 3: current = alternateChars(left, right);
                        break;
                        case 4: current = returnFirstOrSecond(left, right);
                        break;
                        case 5: current = addChars(left, right);
                        break;
                    }
                }
            }
        }
        return current;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[][] machine = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                machine[i][j] = scanner.nextInt();
            }
        }
        String input = scanner.nextLine();
        System.out.println(processInput(machine, input));
    }
}
