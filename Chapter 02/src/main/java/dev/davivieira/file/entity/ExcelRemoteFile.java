package dev.davivieira.file.entity;

import dev.davivieira.file.vo.Type;
import dev.davivieira.file.exception.FileConversionException;

public final class ExcelRemoteFile extends RemoteFile {

    private final String namePrefix = "excel";

    public ExcelRemoteFile(byte[] content, String name) {
        this.type = Type.EXCEL;
        this.content = content;
        this.name = name;
    }

    @Override
    public RemoteFile convertTo(Type conversionType) throws Exception {
        switch (conversionType) {
            case WORD -> {
                return toWord();
            }
            case PDF -> {
                return toPdf();
            }
            default -> throw new FileConversionException("Invalid file conversion type");
        }
    }

    private RemoteFile toWord() {
        var wordSuffix = "word.docx";
        var name = "converted-from-"+namePrefix+"-to-"+wordSuffix;
        return new WordRemoteFile(this.content, name);
    }

    private RemoteFile toPdf() {
        var pdfSuffix = "pdf.pdf";
        var name = "converted-from-"+namePrefix+"-to-"+pdfSuffix;
        return new PdfRemoteFile(this.content, name);
    }
}
