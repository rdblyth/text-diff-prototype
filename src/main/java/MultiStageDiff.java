import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MultiStageDiff {

    public static void main(String[] args) throws DiffException {
        DiffRowGenerator generator1 = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> f ? "<span style=\"background-color: #FFE6E6\"><strike>" : "</strike></span>")
                .build();

        DiffRowGenerator generator2 = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> f ? "<span style=\"background-color: #FFFF00\">" : "</span>")
                .build();

        List<String> originalText = Arrays.asList("(1) This is a test sentence.", "(2) This is the second line.", "(3) And here is the finish.");

        List<DiffRow> rows = generator1.generateDiffRows(
                originalText,
                removeNumbers(originalText));

        List<String> afterPhase1 = rows.stream().map(DiffRow::getOldLine).collect(Collectors.toList());

        rows = generator2.generateDiffRows(
                afterPhase1,
                removeStopWords(afterPhase1));

        System.out.println("<html>");
        for (String row: originalText) {
            System.out.println("<span>" + row + "</span></br>");
        }
        System.out.println("</br>");
        for (DiffRow row : rows) {
            System.out.println(fixEscapedTags(row.getOldLine()) + "</br>");
        }
        System.out.println("</html>");

    }

    private static List<String> removeNumbers(List<String> lines) {
        return lines.stream().map(l -> l.replaceAll("[(\\d)]", "")).collect(Collectors.toList());
    }

    private static List<String> removeStopWords(List<String> lines) {
        return lines.stream().map((l -> l.replaceAll("the", ""))).collect(Collectors.toList());
    }

    public static String fixEscapedTags(String str) {
        return str.replace("&lt;", "<").replace("&gt;", ">");
    }
}
