

import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

import java.util.Arrays;
import java.util.List;

public class Prototype {

    public static void main(String[] args) throws DiffException {
        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> f ? "<span style=\"background-color: #FFE6E6\"><strike>" : "</strike></span>")
                .build();

        List<String> originalText = Arrays.asList("(1) This is a test sentence.", "(2) This is the second line.", "(3) And here is the finish.");

        List<DiffRow> rows = generator.generateDiffRows(
                originalText,
                Arrays.asList("This is a test sentence.", "This is the second line.", "And here is the finish."));


        System.out.println("<html>");
        for (String row: originalText) {
            System.out.println("<span>" + row + "</span></br>");
        }
        System.out.println("</br>");
        for (DiffRow row : rows) {
            System.out.println(row.getOldLine() + "</br>");
        }
        System.out.println("</html>");

    }
}
