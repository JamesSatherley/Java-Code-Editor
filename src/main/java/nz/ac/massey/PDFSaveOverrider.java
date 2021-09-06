package nz.ac.massey;

import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.TextRenderer;
import com.itextpdf.layout.element.Text;

public class PDFSaveOverrider extends TextRenderer {
    // overrides trimFirst to stop PDF trimming space to save code correctly

    public PDFSaveOverrider(Text textElement) {
        super(textElement);
    }

    @Override
    public IRenderer getNextRenderer() {
        return new PDFSaveOverrider((Text) getModelElement());
    }

    @Override
    public void trimFirst() {
    }
}
