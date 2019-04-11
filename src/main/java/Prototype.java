

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
                //.oldTag(f -> f ? "<strike>" : "</strike>")
                .oldTag(f -> f ? "<span style=\"background-color: #FFFF00\">" : "</span>")
                .build();
        List<DiffRow> rows = generator.generateDiffRows(
                Arrays.asList("(1) This is a test sentence.", "(2) This is the second line.", "(3) And here is the finish."),
                Arrays.asList("This is a test sentence.", "This is the second line.", "And here is the finish."));


        System.out.println("<html>");
        for (DiffRow row : rows) {
            System.out.println(row.getOldLine());
        }
        System.out.println("</html>");

    }
}
