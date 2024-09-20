package dev.davivieira.file.entity;

import dev.davivieira.file.exception.FileConversionException;
import dev.davivieira.file.vo.Type;

import java.util.Objects;

public final class PdfRemoteFile extends RemoteFile {

    private final String namePrefix = "pdf";

    public PdfRemoteFile(byte[] content, String name) {
        this.type = Type.PDF;
        this.content = content;
        this.name = name;
    }

    @Override
    public RemoteFile convertTo(Type conversionType) throws Exception {
        if (Objects.requireNonNull(conversionType) == Type.WORD) {
            return toWord();
        }
        throw new FileConversionException("Invalid file conversion type");
    }

    private RemoteFile toWord() {
        var wordSuffix = "word.docx";
        var name = "converted-from-"+namePrefix+"-to-"+wordSuffix;
        return new WordRemoteFile(this.content, name);
    }
}
