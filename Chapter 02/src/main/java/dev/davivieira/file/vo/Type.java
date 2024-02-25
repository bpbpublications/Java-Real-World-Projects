package dev.davivieira.file.vo;

public enum Type {
    PDF("pdf"), WORD("word"), EXCEL("spreadsheet");
    public final String mimeTerm;
    private Type(String mimeTerm) {
        this.mimeTerm = mimeTerm;
    }
}
