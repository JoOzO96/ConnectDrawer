package com.example.jose.connectdrawer.uteis;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.jose.connectdrawer.Impressora.BluetoothService;
import com.example.jose.connectdrawer.Impressora.Command;
import com.example.jose.connectdrawer.Impressora.PrinterCommand;

/**
 * Created by Usuario on 28/02/2018.
 */

public class CriaImpressao {
    // Message types sent from the BluetoothService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    public static final int MESSAGE_CONNECTION_LOST = 6;
    public static final int MESSAGE_UNABLE_CONNECT = 7;
    String address = "0F:02:17:70:78:22";
    private static final String THAI = "CP874";
    private BluetoothService mService = null;
    private BluetoothAdapter mBluetoothAdapter = null;

    public void imprime(String texto, Integer codpage, Integer nWidhTimes, Integer nHeightTimes, Integer nFontType, Integer alinhamento) {
//        TIPO DA LETRA = 0 48 POSICOES
//        TIPO DA LETRA = 1 E 2 64 POSIÇOES
//
        if (alinhamento > 0) {
            texto = alinhaTexto(texto, nFontType, alinhamento);
        }
        try {
            SendDataByte(PrinterCommand.POS_Print_Text(texto, THAI, codpage, nWidhTimes, nHeightTimes, nFontType));
            SendDataByte(Command.LF);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void avanco(Integer avanco) {
        for (int i = 0; i <= avanco; i++) {
            try {
//                SendDataByte(PrinterCommand.POS_Print_Text("", THAI, 0, 0, 0, 0));
                SendDataByte(Command.LF);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String alinhaTexto(String texto, Integer nFontType, Integer alinhamento) {
        if (nFontType > 0) {
            if (alinhamento == 1) {
                if (texto.length() < 64) {
                    for (int i = 0; texto.length() < 64; i++) {
                        texto = " " + texto + " ";
                    }

                    if (texto.length() > 64) {
                        texto = texto.substring(0, 63);
                    }
                    return texto;
                } else {
                    return texto;
                }
            }
            if (alinhamento == 2) {
                if (texto.length() < 64) {
                    for (int i = 0; texto.length() < 64; i++) {
                        texto = " " + texto;
                    }
                    if (texto.length() > 64) {
                        texto = texto.substring(0, 63);
                    }
                    return texto;
                } else {
                    return texto;
                }
            }
        } else {
            if (alinhamento == 1) {
                if (texto.length() < 48) {
                    for (int i = 0; texto.length() < 48; i++) {
                        texto = " " + texto + " ";
                    }
                    if (texto.length() > 48) {
                        texto = texto.substring(0, 47);
                    }
                    return texto;
                } else {
                    return texto;
                }
            }
            if (alinhamento == 2) {
                if (texto.length() < 48) {
                    for (int i = 0; texto.length() < 48; i++) {
                        texto = " " + texto;
                    }
                    if (texto.length() > 48) {
                        texto = texto.substring(0, 47);
                    }
                    return texto;
                } else {
                    return texto;
                }
            }
            if (alinhamento == 3) {
                if (texto.length() < 48) {
                    Integer tamanhoTexto = texto.length();
                    String array[] = null;

                    array = texto.split(" ");



                    if (tamanhoTexto < 48){
                        tamanhoTexto = 48 - tamanhoTexto;
                        texto = "";
                        String textoMeio = adicionaCaracter("", " ", Long.parseLong(tamanhoTexto.toString()));

                        for (int i = 0; array.length > i ; i++) {
                            texto += array[i];
                            if (i==0){
                                texto += textoMeio;
                            }
                        }
                        return texto;
                    }else{
                        return "";
                    }
                } else {
                    return texto;
                }
            }
        }

        return "";
    }

    public String linha(String texto) {

        if (texto.length() < 48) {
            adicionaCaracter(texto, " ", 48L);
        }

        return texto;
    }

    public String adicionaCaracter(String texto, String caracter, Long tamanho) {

        for (int i = texto.length(); i < tamanho; i++) {
            texto += caracter;
        }

        return texto;
    }


    public void conectaImpressora(Context context) throws InterruptedException {
        mService = new BluetoothService(context, mHandler);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = mBluetoothAdapter
                .getRemoteDevice(address);
        //mService.start();
        // Attempt to connect to the device
        mService.connect(device);
        // If the adapter is null, then Bluetooth is not supported
        Thread.sleep(1500);
    }

    public void desconectaImpressora() throws InterruptedException {
        Thread.sleep(500);
        mService.stop();
    }

    public void SendDataByte(byte[] data) throws InterruptedException {

        mService.write(data);

    }


    @SuppressLint("HandlerLeak")
    public final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothService.STATE_CONNECTED:
//					mTitle.setText(R.string.title_connected_to);
//					mTitle.append(mConnectedDeviceName);
                            break;
                        case BluetoothService.STATE_CONNECTING:
                            //mTitle.setText(R.string.title_connecting);
                            break;
                        case BluetoothService.STATE_LISTEN:
                        case BluetoothService.STATE_NONE:
                            //mTitle.setText(R.string.title_not_connected);
                            break;
                    }
                    break;
                case MESSAGE_WRITE:

                    break;
                case MESSAGE_READ:

                    break;
                case MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    break;
                case MESSAGE_TOAST:
                    break;
                case MESSAGE_CONNECTION_LOST:    //蓝牙已断开连接
                    break;
                case MESSAGE_UNABLE_CONNECT:     //无法连接设备
                    break;
            }
        }
    };

}
