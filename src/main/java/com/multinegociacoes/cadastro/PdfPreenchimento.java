package com.multinegociacoes.cadastro;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.util.Map;

public class PdfPreenchimento {
    public static String preencherPDF(Map<String, String> campos) throws Exception {
        String entrada = "FichaCadastro_Preparada.pdf";
        String saida = "FichaCadastro_Preenchida.pdf";

        PdfReader reader = new PdfReader(entrada);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(saida));
        AcroFields form = stamper.getAcroFields();

        for (Map.Entry<String, String> entry : campos.entrySet()) {
            form.setField(entry.getKey(), entry.getValue());
        }

        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();

        return saida;
    }
}
