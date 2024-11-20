import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String S = "\\s+";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String sentence) {

        if (sentence.split(S).length == 1) {
            return sentence + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                List<WordFrequency> wordFrequencyList = getWordFrequencies(sentence);

                wordFrequencyList = getWordFrequencies(wordFrequencyList);

                return joinResult(wordFrequencyList);
            } catch (Exception e) {

                return "Calculate Error";
            }
        }
    }

    private static String joinResult(List<WordFrequency> wordFrequencyList) {
        StringJoiner joiner = new StringJoiner(LINE_BREAK);

        wordFrequencyList.forEach(wordFrequency ->
                joiner.add(wordFrequency.getValue() + " " + wordFrequency.getWordCount()));

        return joiner.toString();
    }

    private static List<WordFrequency> getWordFrequencies(String sentence) {
        String[] words = sentence.split("\\s+");

        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .collect(Collectors.toList());
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencyList) {
        return getListMap(wordFrequencyList).entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted((w1, w2) -> w2.getWordCount() - w1.getWordCount())
                .collect(Collectors.toList());
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> frequencyMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!frequencyMap.containsKey(wordFrequency.getValue())) {
                ArrayList<WordFrequency> arr = new ArrayList<>();
                arr.add(wordFrequency);
                frequencyMap.put(wordFrequency.getValue(), arr);
            } else {
                frequencyMap.get(wordFrequency.getValue()).add(wordFrequency);
            }
        }

        return frequencyMap;
    }

}