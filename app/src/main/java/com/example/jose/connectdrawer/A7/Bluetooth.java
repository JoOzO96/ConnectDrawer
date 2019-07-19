package com.example.jose.connectdrawer.A7;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.jose.connectdrawer.NotaFiscal.NotaFiscal;
import com.example.jose.connectdrawer.NotaProduto.NotaProduto;
import com.example.jose.connectdrawer.Produto.Produto;
import com.example.jose.connectdrawer.cidade.Cidade;
import com.example.jose.connectdrawer.uteis.CriaImpressao;
import com.example.jose.connectdrawer.uteis.MostraToast;

import okhttp3.internal.Util;

public class Bluetooth {
    public BluetoothAdapter Adapter = null;
    public BluetoothSocket Socket;
    public BluetoothDevice Device;
    public InputStream Istream;
    public BufferedOutputStream Ostream;
    MostraToast mostraToast = new MostraToast();
    public static final int CENTRALIZADO = 1;
    public static final int ESQUERDA = 0;
    public static final int DIREITA = 2;
    public static final int BORDAS = 3;
    public static final int DEPOIS = 4;
    public static final int ANTES = 5;
    public static final int METADE = 6;
    private static final UUID BLUETOOTH_SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");


    public boolean imprimeA7(Context context, String codNota) {
        boolean imprimirnaA7 = false;
        imprimirnaA7 = checkBth(context);
        Bitmap mBitmap = imprimeNota(context, codNota);
        if (imprimirnaA7) {

            ESCP.ImageToEsc(mBitmap, Ostream, 5, 8);

            closeBth();
        } else {
            CriaImpressao criaImpressao = new CriaImpressao();
            criaImpressao.imprimeimagem(mBitmap);
        }

        return false;
    }

    public String texto() {
        return "";
    }

    public Integer retornaTamanhoLinha(Integer tamanhoLetra) {
        return (int) (576 / (tamanhoLetra / 1.65));
    }

    public String retornaFormatado(String texto, Integer tamanhoLetra, Integer tipo) {
        CriaImpressao criaImpressao = new CriaImpressao();
        String[] temp = new String[0];
        int tamanhoLinha = retornaTamanhoLinha(tamanhoLetra);

//        if (divisao > 0) {
//            tamanhoLinha = tamanhoLinha / divisao;
//        }

        int numeroDeEspacos = 0;
        if (tipo == BORDAS) {
            temp = texto.split(Character.toString((char) 254));
            texto = texto.replace(Character.toString((char) 254), "");
            numeroDeEspacos = ((int) tamanhoLinha - texto.length()) / (temp.length - 1);
            texto = "";

            for (int i = 0; i < temp.length; i++) {
                texto = texto + adicionaCaracter(temp[i], " ", numeroDeEspacos, DEPOIS);
            }

        } else if (tipo == DIREITA) {
            texto = texto.replace(Character.toString((char) 254), "");
            numeroDeEspacos = tamanhoLinha - texto.length();

            texto = adicionaCaracter(texto, " ", numeroDeEspacos, ANTES);
        } else if (tipo == METADE) {
            texto = texto.replace(Character.toString((char) 254), "");
            numeroDeEspacos = tamanhoLinha - texto.length();
            numeroDeEspacos = numeroDeEspacos / 2;
            texto = adicionaCaracter(texto, " ", numeroDeEspacos, ANTES);
        } else if (tipo == CENTRALIZADO) {
            texto = texto.replace(Character.toString((char) 254), "");
            numeroDeEspacos = tamanhoLinha - texto.length();
            numeroDeEspacos = numeroDeEspacos / 2;
            texto = adicionaCaracter(texto, " ", numeroDeEspacos, ANTES);
            texto = adicionaCaracter(texto, " ", numeroDeEspacos, DEPOIS);
        }
        return texto;
    }

    public String adicionaCaracter(String texto, String caracter, Integer numeroCaracteres, Integer lugar) {
        if (lugar == DEPOIS) {
            for (int i = 0; i < numeroCaracteres; i++) {
                texto = texto + caracter;
            }
        } else {
            for (int i = 0; i < numeroCaracteres; i++) {
                texto = caracter + texto;
            }
        }
        return texto;
    }

    public Bitmap imprimeNota(Context context, String codNota) {
        NotaFiscal notaFiscal = new NotaFiscal();
        NotaProduto notaProduto = new NotaProduto();
        Cidade cidade = new Cidade();
        notaFiscal = notaFiscal.retornaObjetoNota(context, notaFiscal.retornaIdnota(context, codNota));
        cidade = cidade.retornaCidadeObjeto(context, notaFiscal.getCodcidade());

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String dest_nome = Objects.toString(notaFiscal.getNomecliente(), "");
        String dest_endereco = Objects.toString(notaFiscal.getEndereco(), "");
        String dest_numero = Objects.toString(notaFiscal.getNumero(), "");
        String dest_cep = Objects.toString(notaFiscal.getCep(), "");
        String dest_cidade = Objects.toString(cidade.getNomecidade() + " - " + cidade.getUf(), "");
        String dest_cpfcnpj = "";
        if (notaFiscal.getCnpj() == null) {
            dest_cpfcnpj = Objects.toString(notaFiscal.getCpf(), "");
        } else {
            dest_cpfcnpj = Objects.toString(notaFiscal.getCnpj(), "");
        }
        String dest_ie = Objects.toString(notaFiscal.getInscesta(), "");
        List<NotaProduto> notaProdutoList = notaProduto.retornaListaProdutosNota(context, codNota);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int i = 0;
        int yinicial = 0;
        int x = 0, y = 0, w = 576, h = w * 5;
        int size_text = 20, size_legend = 16;
        String chave = "";
        Paint tituloBold = new Paint(Color.BLACK); //25 caracteres
        tituloBold.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));
        tituloBold.setTextSize((int) (size_text * 1.2));

        Paint texto = new Paint(Color.BLACK); //47 caracteres
        texto.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL));
        texto.setTextSize((int) (size_text));

        Paint textoBold = new Paint(Color.BLACK); //31 caracteres
        textoBold.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));
        textoBold.setTextSize((int) (size_text));

        Paint legenda = new Paint(Color.BLACK); //47 caracteres
        legenda.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL));
        legenda.setTextSize(size_legend);

        Paint legendaBold = new Paint(Color.BLACK); //43 caracteres
        legendaBold.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));
        legendaBold.setTextSize(size_legend);

        Paint p = new Paint(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2);


        //------------------------------------------------
        //Bitmap não rotacionado usado para DANFE
        //------------------------------------------------
        Bitmap BitmapDanfe = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        Canvas g = new Canvas(BitmapDanfe);
        g.drawColor(Color.WHITE);


//		if (mBitmapLogo!=null)
//		{
//			x+=10;
//			y+=10;
//			g.drawBitmap(mBitmapLogo, x, y, p);
//		}
//
//		x += mBitmapLogo.getWidth()+10;

        y += size_text;
        g.drawText(retornaFormatado("RECEBEMOS DE" + (char) 254 + "OS PRODUTOS DA NF-E", (int) texto.getTextSize(), BORDAS), x, y, texto);

        y += size_text;
        g.drawText(retornaFormatado("ConnectSys" + (char) 254 + "NF-e: " + Long.parseLong(notaFiscal.getCodnota()), (int) texto.getTextSize(), BORDAS), x, y, texto);

        y += size_text;
        g.drawText(retornaFormatado((char) 254 + "Serie: 1", (int) texto.getTextSize(), DIREITA), x, y, texto);

        y += size_text;
        g.drawText(retornaFormatado("DATA DE RECEBIMENTO" + (char) 254 + "IDENTIFICAÇÃO/ASSINATURA ", (int) texto.getTextSize(), BORDAS), x, y, texto);

        y += size_text;
        g.drawText(" ", x, y, texto);

        y += size_text;
        g.drawText(retornaFormatado("___/___/_______" + (char) 254 + "____________________________ ", (int) texto.getTextSize(), BORDAS), x, y, texto);

        y += size_text;
        g.drawLine(x, y, w, y, p);

        y += size_text;
        g.drawText(" ", x, y, texto);

        y += size_text;
        g.drawText("  DANFE SIMPLIFICADO", x, y, texto);
        g.drawText(retornaFormatado((char) 254 + "OPERAÇÃO  1 - SAÍDA", (int) tituloBold.getTextSize(), DIREITA), x, y, tituloBold);
        y += size_text;
        g.drawText("  DOCUMENTO AUXILIAR", x, y, texto);
        g.drawText(retornaFormatado((char) 254 + "N° " + Integer.parseInt(notaFiscal.getCodnota()), (int) tituloBold.getTextSize(), DIREITA), x, y, tituloBold);
        y += size_text;
        g.drawText("    DA NOTA FISCAL", x, y, texto);
        g.drawText(retornaFormatado((char) 254 + "SÉRIE 1", (int) tituloBold.getTextSize(), DIREITA), x, y, tituloBold);
        y += size_text;
        g.drawText("      ELETRONICA", x, y, texto);


        y += size_text;
        g.drawText(" ", x, y, texto);

        chave = "00123789456963258741147852369987456321015973";
        y += 20;
        BarCode.drawBarCode128C(g, chave, x, y, w, 80);

        y += 100;
        g.drawText(" CHAVE DE ACESSO", x, y, texto);

        y += size_text;
        g.drawText(chave, x, y, texto);

        y += size_text;

        g.drawText(retornaFormatado("SAÍDA " + simpleDateFormat.format(notaFiscal.getDatasaida()) + (char) 254 + "EMISSÃO " + simpleDateFormat.format(notaFiscal.getDataemissao()), (int) texto.getTextSize(), BORDAS), x, y, texto);
        y += size_text;
        g.drawText("NATUREZA DE OPERAÇÃO", x, y, texto);
        y += size_text;
        g.drawText(" VENDA DE PRODUÇÃO DO ESTABELECIMENTO", x, y, legenda);


        y += size_text;
        g.drawText(" ", x, y, texto);

        y += size_text;
        g.drawLine(x, y, w, y, p);

        yinicial = y;

        y += size_text;
        g.drawText(retornaFormatado("EMITENTE", (int) tituloBold.getTextSize(), CENTRALIZADO), x, y, tituloBold);

        g.drawLine(x, y, w, y, p);

        y += size_text;
        g.drawText("ConnectSys", x, y, texto);

        y += size_text;
        g.drawText("Av. 7 de Setembro N° 1000", x, y, texto);

        y += size_text;
        g.drawText("SALA 206 ED ATUALITTA", x, y, texto);

        y += size_text;
        g.drawText("99950-000 Tapejara - RS", x, y, texto);

        y += size_text;
        g.drawText("CPF/CNPJ:06.354.976/0001-49 IE:1470049241", x, y, texto);

        g.drawRect(x, yinicial, w, y, p);

        yinicial = y;

        y += size_text;
        g.drawText(retornaFormatado("DESTINATÁRIO", (int) tituloBold.getTextSize(), CENTRALIZADO), x, y, tituloBold);

        g.drawLine(x, y, w, y, p);

        y += size_text;
        g.drawText(dest_nome, x, y, texto);

        y += size_text;
        g.drawText(dest_endereco + " " + dest_numero, x, y, texto);

//        y += size_text;
//        g.drawText(notaFiscal.getComplemento(), x, y, texto);

        y += size_text;
        g.drawText(dest_cep + " " + dest_cidade, x, y, texto);

        y += size_text;
        g.drawText("CPF/CNPJ:" + dest_cpfcnpj + " IE:" + dest_ie, x, y, texto);

        g.drawRect(x, yinicial, w, y, p);

        yinicial = y;

        y += size_text;
        g.drawText(retornaFormatado("DADOS DOS IMPOSTOS", (int) tituloBold.getTextSize(), CENTRALIZADO), x, y, tituloBold);

        g.drawLine(x, y, w, y, p);

        y += size_text;
        g.drawText(retornaFormatado("VL ICMS" + (char) 254 + "BC ICMS" + (char) 254 + "BC ICMS ST" + (char) 254 + "VL ICMS ST" + (char) 254 + "TOT PROD", (int) texto.getTextSize(), BORDAS), x, y, texto);

        y += size_text;
        g.drawText(retornaFormatado("R$ " + decimalFormat.format(notaFiscal.getValoricms()) + (char) 254 + "R$ " + decimalFormat.format(notaFiscal.getValoricms()) + (char) 254 + "R$ " + decimalFormat.format(notaFiscal.getValoricmssub()) + (char) 254 + "R$ " + decimalFormat.format(notaFiscal.getIcmssub()) + (char) 254 + "R$ " + decimalFormat.format(notaFiscal.getValordosprodutos()), (int) texto.getTextSize(), BORDAS), x, y, texto);

        y += size_text;
        g.drawText(retornaFormatado("VL FRETE" + (char) 254 + "VL SEGURO" + (char) 254 + "VL OUTROS" + (char) 254 + "VL IPI" + (char) 254 + "TOT NFE", (int) texto.getTextSize(), BORDAS), x, y, texto);

        y += size_text;
        g.drawText(retornaFormatado("R$ " + decimalFormat.format(notaFiscal.getValorfrete()) + (char) 254 + "R$ " + decimalFormat.format(notaFiscal.getValorseguro()) + (char) 254 + "R$ " + decimalFormat.format(notaFiscal.getDespesas()) + (char) 254 + "R$ " + decimalFormat.format(notaFiscal.getValordoipi()) + (char) 254 + "R$ " + decimalFormat.format(notaFiscal.getValornota()), (int) texto.getTextSize(), BORDAS), x, y, texto);

        g.drawRect(x, yinicial, w, y, p);
        yinicial = y;
        y += size_text;
        g.drawText(retornaFormatado("CÓD PRODUTO           PRODUTOS", (int) tituloBold.getTextSize(), CENTRALIZADO), x, y, tituloBold);

        y += size_text;
        g.drawText(retornaFormatado("CST" + (char) 254 + "UN" + (char) 254 + "QUANT" + (char) 254 + "V. UNI." + (char) 254 + "TOTAL", (int) tituloBold.getTextSize(), BORDAS), x, y, tituloBold);

        g.drawLine(x, y, w, y, p);

        for (int j = 0; j < notaProdutoList.size(); j++) {
            Produto produto = new Produto();
            produto = produto.retornaProdutoObjetoAtualizar(context, notaProdutoList.get(j).getCodigo());
            y += size_text;
            g.drawText(retornaFormatado(notaProdutoList.get(j).getCodigo() + (char) 254 + notaProdutoList.get(j).getDescri(), (int) tituloBold.getTextSize(), BORDAS), x, y, tituloBold);

            y += size_text;
            g.drawText(retornaFormatado(notaProdutoList.get(j).getSst() + " " + (char) 254 + produto.getUnid() + (char) 254 + decimalFormat.format(notaProdutoList.get(j).getQuantidade()) + (char) 254 + " R$ " + decimalFormat.format(notaProdutoList.get(j).getValorunitario()) + (char) 254 + " R$ " + decimalFormat.format(notaProdutoList.get(j).getValortotal()), (int) tituloBold.getTextSize(), BORDAS), x, y, tituloBold);

        }

        y += size_text;
        g.drawRect(x, yinicial, w, y, p);

        yinicial = y;

        y += size_text;
        g.drawText("DADOS ADICIONAIS", x, y, tituloBold);

        g.drawRect(x, yinicial, w, y, p);

        y += size_text;
        tituloBold.setTextSize(15);
        g.drawText(retornaFormatado("Desenvolvido por ConnectSys Informatica - (54) 3344 3036", (int) tituloBold.getTextSize(),CENTRALIZADO), x, y, tituloBold);


        y += size_text;
        g.drawText(" ", x, y, texto);
        y += size_text;
        g.drawText(" ", x, y, texto);
        y += size_text;
        g.drawText(" ", x, y, texto);
        y += size_text;
        g.drawText(" ", x, y, texto);
        y += size_text;
        g.drawText(" ", x, y, texto);

        h = (int) (y / 64);
        h = ((h + 1) * 64);
        Bitmap BitmapReturn = Bitmap.createBitmap(BitmapDanfe.getWidth(), h, Bitmap.Config.RGB_565);
        Canvas g3 = new Canvas(BitmapReturn);

        g3.drawBitmap(BitmapDanfe, 0, 0, p);

        return BitmapReturn;
    }

    public static Bitmap createSample() {
        int x = 0, y = 0, w = 576, h = w * 5;
        int size_text = 32, size_legend = 22;

        Paint fontTitleBold = new Paint(Color.BLACK);
        fontTitleBold.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        fontTitleBold.setTextSize((int) (size_text * 1.2));

        Paint fontText = new Paint(Color.BLACK);
        fontText.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
        fontText.setTextSize((int) (size_text));

        Paint fontTextBold = new Paint(Color.BLACK);
        fontTextBold.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        fontTextBold.setTextSize((int) (size_text));

        Paint fontLegend = new Paint(Color.BLACK);
        fontLegend.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
        fontLegend.setTextSize(size_legend);

        Paint fontLegendBold = new Paint(Color.BLACK);
        fontLegendBold.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        fontLegendBold.setTextSize(size_legend);

        Paint p = new Paint(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2);


        //------------------------------------------------
        //Bitmap não rotacionado usado para DANFE
        //------------------------------------------------
        Bitmap BitmapDanfe = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        Canvas g = new Canvas(BitmapDanfe);
        g.drawColor(Color.WHITE);

        g.drawRect(x, y, w, y + 90, p);

//		if (mBitmapLogo!=null)
//		{
//			x+=10;
//			y+=10;
//			g.drawBitmap(mBitmapLogo, x, y, p);
//		}
//
//		x += mBitmapLogo.getWidth()+10;
        y += size_text;
        g.drawText("EXEMPLO GRAFICO", x, y, fontTitleBold);

        y += size_text;
        g.drawText("LEOPARDO A7 ESC/P", x, y, fontTextBold);

        x = 0;
        y = 90;
        g.drawRect(x, y, w, y + size_legend + size_text, p);
        x = 10;
        y += size_legend;
        g.drawText("Exemplo de CHAVE DE ACESSO", x, y, fontLegendBold);
        y += size_legend;

        String chave = "00123789456963258741147852369987456321015973";
        g.drawText(chave, x, y, fontLegend);

        y += 20;
        BarCode.drawBarCode128C(g, chave, x, y, w, 80);

        x = 0;
        y += 105;
        p.setPathEffect(new DashPathEffect(new float[]{10, 20}, 0));
        g.drawLine(0, y, w, y, p);
        p.setPathEffect(null);
        y += 70;
        x = 0;

        int ay = y;
        //------------------------------------------------
        //Bitmap rotacionado usado para Boletos
        //------------------------------------------------
        int W = 1200, H = w;
        Bitmap BitmapBoleto = Bitmap.createBitmap(H, W, Bitmap.Config.RGB_565);
        Canvas g2 = new Canvas(BitmapBoleto);
        g2.drawColor(Color.WHITE);

        g2.rotate(90, H / 2, H / 2);

        String linhaDigitavel = "03399.64355 86600.000003 08288.001012 1 61950000000123";
        String codigoBarras = "03391619500000001239643586600000000828800101";

        x = 0;
        y = 0;
//		if (mBitmapBanco!=null)
//		{
//			g2.drawBitmap(mBitmapBanco, x, y, p);
//		}

        g2.drawLine(x, y + 50, W, y + 50, p);
        g2.drawRect(x, y + 50, W, H - 100, p);

        g2.drawText(linhaDigitavel, 250, 40, fontTextBold);

        g2.drawText("EXEMPLO DE ROTACIONAMENTO PARA IMPRESSÃO DE BOLETO.", 20, H / 2, fontTextBold);
        BarI25 b25 = new BarI25();
        Bitmap i25 = b25.createI25(codigoBarras);
        g2.drawBitmap(i25, 20, H - 97, p);

        //------------------------------------------------
        x = 0;
        y = ay;
        g.drawBitmap(BitmapBoleto, x, y, p);

        //------------------------------------------------

        x = 0;
        y += 1300;

        h = (int) (y / 64);
        h = ((h + 1) * 64);

        Bitmap BitmapReturn = Bitmap.createBitmap(BitmapDanfe.getWidth(), h, Bitmap.Config.RGB_565);
        Canvas g3 = new Canvas(BitmapReturn);

        g3.drawBitmap(BitmapDanfe, 0, 0, p);

        return BitmapReturn;

    }


    public boolean checkBth(Context context) {
        if (!isConnected()) {
            if (!Enable()) {
                mostraToast.mostraToastLong(context, "Nao foi possivel abilitar bluetooth, tente abilitar manualmente e tente novamente.");
                return false;
            }
            String mac = null;
            Set<BluetoothDevice> devices = GetBondedDevices();
            for (BluetoothDevice device : devices) {
                if ("MPT-III".equals(device.getName())) {
                    mac = device.getAddress();
                }
            }
            if (mac == null) {
                mostraToast.mostraToastLong(context, "Nao foi encontrada MPT-III\n\nFaça o pareamento com o disposivo e tente novamente.");
                return false;
            }
            if (!Open(mac)) {
                mostraToast.mostraToastLong(context, "Nao foi possivel conectar ao dispositivo [" + mac + "]\n\nLigue ou conecte o dispositivo e tente novamente.");
                return false;
            }
        }
        return true;
    }

    public boolean closeBth() {
        if (isConnected()) {
            return Close();
        }
        return false;
    }

    public boolean isConnected() {
        return (Socket != null && Socket.isConnected());
    }

    public boolean Enable() {
        Adapter = BluetoothAdapter.getDefaultAdapter();
        if (Adapter != null) {
            if (Adapter.isEnabled()) {
                return true;
            }
            Adapter.enable();
            try {
                Thread.sleep(5000, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
            Adapter = BluetoothAdapter.getDefaultAdapter();
            if (Adapter.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public boolean Disable() {
        if (Adapter != null) {
            if (Adapter.isEnabled()) {
                Adapter.disable();
            }
            if (!Adapter.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public Set<BluetoothDevice> GetBondedDevices() {
        return Adapter.getBondedDevices();
    }

    public boolean Open(String mac) {
        try {
            this.Close();
            Device = Adapter.getRemoteDevice(mac);
            Adapter.cancelDiscovery();
            Socket = Device.createRfcommSocketToServiceRecord(BLUETOOTH_SPP_UUID);
            Socket.connect();
            Istream = Socket.getInputStream();
            Ostream = new BufferedOutputStream(Socket.getOutputStream());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean Close() {
        try {
            if (Ostream != null)
                Ostream.flush();
            if (Socket != null)
                Socket.close();
            Socket = null;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean Write(byte[] buffer, int count) {
        try {
            Ostream.write(buffer, 0, count);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean Read(byte[] buffer, int length) {
        try {
            Istream.read(buffer, 0, length);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
