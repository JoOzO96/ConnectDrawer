package com.example.jose.connectdrawer.uteis;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jose.connectdrawer.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jose on 06/06/2017.
 */

public class GetSetDinamicoTelas extends Fragment {

    public void teste(Object classe) {
        List<Field> fieldList = new ArrayList<>(Arrays.asList(classe.getClass().getDeclaredFields()));
        for (int i = 0; fieldList.size() != i; i++) {
            Log.e("VIEW", fieldList.get(i).getName());
        }
    }

    public void colocaValorEditText(Field field, View view, Object valor, @Nullable String mascara) {
        try {
            if (valor != null) {
                List<Field> fieldList = new ArrayList<>(Arrays.asList(R.id.class.getDeclaredFields()));
                Class res = R.id.class;
                EditText objeto = null;
                for (int i = 0; fieldList.size() != i; i++) {

                    if (fieldList.get(i).getName().toString().equals(field.getName().toString())) {
                        Field field1 = res.getField(field.getName().toString());
                        int idField = field1.getInt(null);
                        objeto = (EditText) view.findViewById(idField);
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

    public void colocaValorSpinner(Field field, View view, List<String> valor) {
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
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_item, valor);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        objeto.setAdapter(arrayAdapter);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
