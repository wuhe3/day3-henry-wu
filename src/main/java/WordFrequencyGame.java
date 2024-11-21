import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String NEWLINE = "\n";
    public static final String SPACE = " ";

    public String getWordFrequency(String sentence) {
        String[] words = sentence.split(WHITESPACE_REGEX);
        if (words.length == 1) {
            return sentence + " 1";
        } else {
            List<WordFrequency> wordFrequencyList = getWordFrequencies(words);
            return joinResult(wordFrequencyList);
        }
    }

    private static String joinResult(List<WordFrequency> wordFrequencyList) {
        StringJoiner joiner = new StringJoiner(NEWLINE);
        wordFrequencyList.forEach(wordFrequency ->
                joiner.add(wordFrequency.getValue() + SPACE + wordFrequency.getWordCount()));
        return joiner.toString();
    }

    private List<WordFrequency> getWordFrequencies(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().intValue()))
                .sorted((curr, next) -> next.getWordCount() - curr.getWordCount())
                .collect(Collectors.toList());
    }
}