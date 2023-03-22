import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class ReinigerWriter extends FilterWriter {

    public ReinigerWriter(Writer out) {
        super(out);
    }

    @Override
    public Writer append(char c) throws IOException {
        if (c == 'ö') {
            return super.append("oe");
        } else if (c == 'Ö') {
            return super.append("Oe");
        } else if (c == 'ü') {
            return super.append("ue");
        } else if (c == 'Ü') {
            return super.append("Ue");
        } else if (c == 'ä') {
            return super.append("ae");
        } else if (c == 'Ä') {
            return super.append("Ae");
        } else if (c == 'ß') {
            return super.append("ss");
        }
        return super.append(c);
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        char[] test = csq.toString().toCharArray();
        for (char c : test) {
            append(c);
        }
        return null;
    }
}