package dev.davivieira;

import dev.davivieira.file.service.FileConverterService;
import dev.davivieira.file.vo.Type;

/**
 * It is a dummy file converter that downloads files and convert them to different file formats.
 * No actual file conversion occurs. This application explores Java features like sealed classes,
 * pattern matching, string templates, and virtual threads.
 */
public class RemoteFileConverter {

    private static FileConverterService fileConverterService = new FileConverterService();

    public static void main(String... args) throws Exception {
        convertPdfToWord();
        convertWorldToPdf();
        convertExcelToWord();
    }

    private static void convertPdfToWord() throws Exception {
        fileConverterService.convertFile("https://davivieira.dev/file.pdf", Type.WORD);
    }

    private static void convertWorldToPdf() throws Exception {
        fileConverterService.convertFile("https://davivieira.dev/file.docx", Type.PDF);
    }

    private static void convertExcelToWord() throws Exception {
        fileConverterService.convertFile("https://davivieira.dev/file.xlsx", Type.WORD);
    }
}
