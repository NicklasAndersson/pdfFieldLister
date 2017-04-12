import java.io.File;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField;

/**
 * Created by
 *
 * @author nicklas on 2017-04-12.
 */
public class PDFfieldLister {

    public static void main(String[] args) throws Exception {
        PDFfieldLister pdFfieldLister = new PDFfieldLister();
        pdFfieldLister.test(args[0]);
    }

    public void test(String uri) throws Exception {

        PDDocument pdfDocument = PDDocument.load(new File(uri));
        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        List<PDField> fields = acroForm.getFields();
        for (PDField field : fields) {
            list(field);
        }

    }

    void list(PDField field) throws Exception {
        System.out.println(field.getFullyQualifiedName());
        System.out.println(field.getPartialName());
        if (field instanceof PDNonTerminalField) {
            PDNonTerminalField nonTerminalField = (PDNonTerminalField) field;
            for (PDField child : nonTerminalField.getChildren()) {
                list(child);
            }
        }
    }
}
