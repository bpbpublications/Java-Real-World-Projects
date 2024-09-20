package dev.davivieira.file.entity;

import dev.davivieira.file.vo.Type;
import dev.davivieira.file.exception.FileConversionException;

public final class WordRemoteFile extends RemoteFile {

    private final String namePrefix = "word";

    public WordRemoteFile(byte[] content, String name) {
        this.type = Type.WORD;
        this.content = content;
        this.name = name;
    }

    @Override
    public RemoteFile convertTo(Type conversionType) throws Exception {
        switch (conversionType) {
            case PDF -> {
                return toPdf();
            }
            case EXCEL -> {
                return toExcel();
            }
            default -> throw new FileConversionException("Invalid file conversion type");
        }
    }

    private RemoteFile toPdf() {
        var pdfSuffix = "pdf.pdf";
        var name = "converted-from-"+namePrefix+"-to-"+pdfSuffix;
        return new PdfRemoteFile(this.content, name);
    }

    private RemoteFile toExcel() {
        var excelSuffix = "excel.xlsx";
        var name = "converted-from-"+namePrefix+"-to-"+excelSuffix;
        return new ExcelRemoteFile(this.content, name);
    }
}
