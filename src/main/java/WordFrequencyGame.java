import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String NEWLINE = "\n";

    public String getWordFrequency(String sentence) {
        String[] words = sentence.split(WHITESPACE_REGEX);
        if (words.length == 1) {
            return sentence + " 1";
        } else {
            Map<String, Long> wordFrequencyMap = getInitialWordFrequencies(sentence);
            List<WordFrequency> wordFrequencyList = getWordFrequencies(wordFrequencyMap);
            return joinResult(wordFrequencyList);
        }
    }

    private static String joinResult(List<WordFrequency> wordFrequencyList) {
        StringJoiner joiner = new StringJoiner(NEWLINE);
        wordFrequencyList.forEach(wordFrequency ->
                joiner.add(wordFrequency.getValue() + " " + wordFrequency.getWordCount()));
        return joiner.toString();
    }

    private static Map<String, Long> getInitialWordFrequencies(String sentence) {
        return Arrays.stream(sentence.split(WHITESPACE_REGEX))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }

    private List<WordFrequency> getWordFrequencies(Map<String, Long> wordFrequencyMap) {
        return wordFrequencyMap.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().intValue()))
                .sorted((w1, w2) -> w2.getWordCount() - w1.getWordCount())
                .collect(Collectors.toList());
    }
}