import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Vector;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField;

public class PDFfieldLister {
    private static final String SEPARATOR = " : ";

    public static void main(String[] args) throws Exception {
        System.out.println("FullyQualifiedName : FieldType");
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        PDFfieldLister pdFfieldLister = new PDFfieldLister();
        try {
            pdFfieldLister.parse(fileFromString(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
        }
    }

    private void parse(File file) throws Exception {
        PDDocument pdfDocument = PDDocument.load(file);
        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        List<PDField> fields = acroForm.getFields();
        for (PDField field : fields) {
            list(field);
        }
    }

    private void list(PDField field) {
        System.out.println(buildFieldString(field));
        if (field instanceof PDNonTerminalField) {
            PDNonTerminalField nonTerminalField = (PDNonTerminalField) field;
            for (PDField child : nonTerminalField.getChildren()) {
                list(child);
            }
        }
    }

    private static String buildFieldString(PDField field) {
        StringBuilder value = new StringBuilder();
        value.append(field.getFullyQualifiedName());
        value.append(SEPARATOR);
        value.append(field.getFieldType());
        if (field.getValueAsString() != null && field.getValueAsString().length() != 0) {
            value.append(SEPARATOR);
            value.append(field.getValueAsString());
        }
        return value.toString();
    }

    private static File fileFromString(String filePathString) throws FileNotFoundException {
        File f = new File(filePathString);
        if (f.exists() && !f.isDirectory()) {
            return f;
        } else {
            throw new FileNotFoundException(f.getAbsolutePath());
        }
    }
}
