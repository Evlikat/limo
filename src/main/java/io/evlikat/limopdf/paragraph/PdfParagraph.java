package io.evlikat.limopdf.paragraph;

import io.evlikat.limopdf.page.PageSpecification;
import io.evlikat.limopdf.structure.Drawable;
import io.evlikat.limopdf.structure.StickyDrawable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class PdfParagraph implements StickyDrawable, Drawable {

    private final PdfParagraphProperties paragraphProperties;
    private final List<PdfParagraphChunk> chunks = new ArrayList<>();

    public PdfParagraph(List<PdfParagraphChunk> chunks, PdfParagraphProperties paragraphProperties) {
        this.paragraphProperties = paragraphProperties;
        this.chunks.addAll(chunks);
    }

    public PdfParagraph(PdfParagraphChunk chunk, PdfParagraphProperties paragraphProperties) {
        this(List.of(chunk), paragraphProperties);
    }

    public PdfParagraph(List<PdfParagraphChunk> chunks) {
        this(chunks, new PdfParagraphProperties());
    }

    public PdfParagraph(String text, PdfParagraphProperties paragraphProperties) {
        this(new PdfParagraphChunk(text, new PdfCharacterProperties()), paragraphProperties);
    }

    public PdfParagraph(String text) {
        this(new PdfParagraphChunk(text, new PdfCharacterProperties()), new PdfParagraphProperties());
    }

    @Override
    public PdfParagraphDrawer drawer() {
        return new PdfParagraphDrawer(this);
    }

    @Override
    public boolean isKeepWithNext() {
        return paragraphProperties.isKeepWithNext();
    }

    @Override
    public Optional<PageSpecification> pageBreak() {
        return Optional.ofNullable(paragraphProperties.getNextPageSpecification());
    }
}
