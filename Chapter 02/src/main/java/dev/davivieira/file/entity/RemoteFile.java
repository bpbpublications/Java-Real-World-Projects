package dev.davivieira.file.entity;

import dev.davivieira.file.vo.Type;

public abstract sealed class RemoteFile permits ExcelRemoteFile, PdfRemoteFile, WordRemoteFile {

    protected Type type;
    protected byte[] content;
    protected String name;

    public abstract RemoteFile convertTo(Type conversionType) throws Exception;

    @Override
    public String toString() {
        return name;
    }
}
