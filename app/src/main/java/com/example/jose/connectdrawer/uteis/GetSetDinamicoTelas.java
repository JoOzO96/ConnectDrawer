package com.example.jose.connectdrawer.uteis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.cliente.Cliente;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jose on 06/06/2017.
 */

public class GetSetDinamicoTelas extends Fragment {

    public void colocaValorEditText(Field field, View view, List<Field> fieldList, Object valor, @Nullable String mascara) {
        try {

            if (valor != null) {
                Class res = R.id.class;
                EditText objeto = null;
                for (int i = 0; fieldList.size() != i; i++) {

                    if (fieldList.get(i).getName().toString().equals(field.getName().toString())) {
                        Field field1 = res.getField(field.getName().toString());
                        int idField = field1.getInt(null);
                        objeto = (EditText) view.findViewById(idField);
                        if (mascara != null) {
                            objeto.addTextChangedListener(Mascara.insert(mascara, objeto));
                        }
                        objeto.setText(valor.toString());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void colocaValorSpinner(final Field field, final View view, final List<String> valor, final Context context, final int posicaoSelecionar) {
        try {

            if (valor != null) {
                List<Field> fieldList = new ArrayList<>(Arrays.asList(R.id.class.getDeclaredFields()));
                Class res = R.id.class;
                Spinner objeto = null;
                for (int i = 0; fieldList.size() != i; i++) {

                    if (fieldList.get(i).getName().toString().equals(field.getName().toString())) {
                        Field field1 = res.getField(field.getName().toString());
                        int idField = field1.getInt(null);
                        objeto = (Spinner) view.findViewById(idField);

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
                                android.R.layout.simple_spinner_item, valor);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        objeto.setAdapter(arrayAdapter);
                        objeto.setSelection(posicaoSelecionar);
                    }
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void colocaValorSpinnerColorido(final Field field, final View view, final List<String> valor, final Context context, final int posicaoSelecionar) {
        try {
            if (valor != null) {
                List<Field> fieldList = new ArrayList<>(Arrays.asList(R.id.class.getDeclaredFields()));
                Class res = R.id.class;
                Spinner objeto = null;
                for (int i = 0; fieldList.size() != i; i++) {
                    if (fieldList.get(i).getName().toString().equals(field.getName().toString())) {
                        Field field1 = res.getField(field.getName().toString());
                        int idField = field1.getInt(null);
                        objeto = (Spinner) view.findViewById(idField);
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
                                android.R.layout.simple_spinner_item, valor){
                            @SuppressLint("ResourceAsColor")
                            @NonNull
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                Long codCliente = 0L;
                                String[] stringCliente;
                                View view = super.getView(position, convertView, parent);
                                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                                Cliente cliente = new Cliente();
                                stringCliente = valor.get(position).split(" -");
                                codCliente = Long.parseLong(stringCliente[0]);
                                Cursor cursor = cliente.retornaClienteFiltradoCursor(getContext(), codCliente);
//                                1 - Otimo
//                                2 - Bom
//                                3 - Regular
//                                4 - Bloqueado
//                                5 - SERASA
//                                6 - Advogados
                                Log.i("COLORIDO", "" + cursor.getLong(cursor.getColumnIndex("posicao")));
                                if (cursor.getLong(cursor.getColumnIndex("posicao")) == 1L){
                                    tv.setTextColor(Color.GREEN);
                                }else if (cursor.getLong(cursor.getColumnIndex("posicao")) == 2L){
                                    tv.setTextColor(Color.rgb(61,255,158));
                                }else if (cursor.getLong(cursor.getColumnIndex("posicao")) == 3L){
                                    tv.setTextColor(Color.rgb(247,164,0));
                                }else{
                                    tv.setTextColor(Color.RED);
                                }
                                return view;
                            }
                        };
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        objeto.setAdapter(arrayAdapter);
                        objeto.setSelection(posicaoSelecionar);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public Object retornaIDCampo(View view, String nomeCampo) {
        Object id = null;
        try {
            if (nomeCampo != null) {
                List<Field> fieldList = new ArrayList<>(Arrays.asList(R.id.class.getDeclaredFields()));
                Class res = R.id.class;
                for (int i = 0; fieldList.size() != i; i++) {
                    if (fieldList.get(i).getName().toString().equals(nomeCampo)) {
                        Field field1 = null;
                        field1 = res.getField(nomeCampo);
                        int idField = field1.getInt(null);
                        id = view.findViewById(idField);
                    }
                }
                return id;
            }
            return 0;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String retornaValorEditText(View view, String nomeCampo) {
        String primeiro = nomeCampo.substring(0, 1);
        primeiro = "tx" + primeiro.toUpperCase();
        String nomeCampo1 = nomeCampo.substring(1, nomeCampo.length());
        primeiro += nomeCampo1;
        EditText valorId = (EditText) retornaIDCampo(view, primeiro);
        if (valorId != null) {
            if (valorId.getText() != null) {
                return valorId.getText().toString();
            }
        }
        return "";
    }

    public String retornaValorSpinner(View view, String nomeCampo) {
        String primeiro = nomeCampo.substring(0, 1);
        primeiro = "sp" + primeiro.toUpperCase();
        String nomeCampo1 = nomeCampo.substring(1, nomeCampo.length());
        primeiro += nomeCampo1;
        Spinner valorId = (Spinner) retornaIDCampo(view, primeiro);
        if (valorId != null) {
            if (valorId.getCount() > 0) {
                String string = valorId.getSelectedItem().toString();
                String valor = "";
                for (int i = 0; string.length() != i; i++) {
                    if (String.valueOf(string.charAt(i + 1)).equals("-")) {
                        return valor;
                    } else {
                        valor += string.charAt(i);
                    }
                }
            }else{
                return "ERRO";
            }
        }
        return "";
    }
}