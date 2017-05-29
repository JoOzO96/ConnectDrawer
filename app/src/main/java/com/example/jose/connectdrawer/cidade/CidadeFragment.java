package com.example.jose.connectdrawer.cidade;


import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jose.connectdrawer.R;
import com.example.jose.connectdrawer.uteis.MostraToast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CidadeFragment extends Fragment {

    private ListView listCidade;
    private Cidade cidadeDel;


    public CidadeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cidade, container, false);
        listCidade = (ListView) view.findViewById(R.id.listCidade);
        this.setHasOptionsMenu(true);

        final Cidade[] cidade = {new Cidade()};

        Cursor cursor = cidade[0].retornaCidade(getContext());
        List<Cidade> cidadeList = new ArrayList<>();

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            for (Long cont = 1L; cursor.getCount() != cont; cont++) {
                Cidade cidade1 = new Cidade();
                cidade1.setCodCidade(cursor.getLong(cursor.getColumnIndex("codCidade")));
                cidade1.setNomeCidade(cursor.getString(cursor.getColumnIndex("nomeCidade")));
                cidade1.setUF(cursor.getString(cursor.getColumnIndex("uf")));

                cidadeList.add(cidade1);
                cursor.moveToNext();
            }
            ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, cidadeList);
            listCidade.setAdapter(adapter);
            listCidade.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            Cidade cidade1 = (Cidade) listCidade.getItemAtPosition(position);
                            CidadeDados cidadeDados = new CidadeDados();
                            Bundle bundle = new Bundle();
                            bundle.putLong("codigo", cidade1.getCodCidade());
                            cidadeDados.setArguments(bundle);
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, cidadeDados, cidadeDados.getTag()).commit();
                        }
                    }
            );
            listCidade.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    cidadeDel = (Cidade) listCidade.getItemAtPosition(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle("Confirm");
                    builder.setMessage("Confirma a exclusao da cidade " + cidadeDel.getNomeCidade() + "?");

                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            MostraToast toast = new MostraToast();
                            boolean retorno = cidadeDel.remover(getContext(), cidadeDel);
                            if (retorno == true) {
                                toast.mostraToastShort(getContext(), "Cidade excluida com sucesso");
                                CidadeFragment cidadeFragment = new CidadeFragment();
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container, cidadeFragment, cidadeFragment.getTag()).commit();
                            } else {
                                toast.mostraToastShort(getContext(), "Erro ao deletar cidade");
                            }
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                    return true;
                }
            });

        }


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_adicionar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.adicionar) {
            CidadeDados cidadeDados = new CidadeDados();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, cidadeDados, cidadeDados.getTag()).commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
