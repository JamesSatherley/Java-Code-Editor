package nz.ac.massey;

import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.TextRenderer;
import com.itextpdf.layout.element.Text;

public class Overrider extends TextRenderer {

    public Overrider(Text textElement) {
        super(textElement);
    }

    @Override
    public IRenderer getNextRenderer() {
        return new Overrider((Text) getModelElement());
    }

    @Override
    public void trimFirst() {
    }
}
