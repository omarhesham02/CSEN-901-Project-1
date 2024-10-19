
package code.utils;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConditionalPrintStream extends PrintStream {
    private final PrintStream fileOut;

    public ConditionalPrintStream(OutputStream consoleOut, PrintStream fileOut) {
        super(new FilterOutputStream(consoleOut) {
            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                super.write(b, off, len);
                fileOut.write(stripAnsiCodes(new String(b, off, len)).getBytes());
            }
        });
        this.fileOut = fileOut;
    }

    private static String stripAnsiCodes(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    @Override
    public void close() {
        super.close();
        fileOut.close();
    }
}