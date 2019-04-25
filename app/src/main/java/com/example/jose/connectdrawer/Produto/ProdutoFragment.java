package com.example.jose.connectdrawer.Produto;


import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jose.connectdrawer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutoFragment extends Fragment {
    private ListView listaProduto;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    List<Produto> produtoList = new ArrayList<>();

    public ProdutoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_produto, container, false);
        listaProduto = (ListView) view.findViewById(R.id.listProduto);
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarProdutos);
        //view.
        this.setHasOptionsMenu(true);

        Produto produto = new Produto();

        Cursor cursor = produto.retornaProduto(getContext());

//        searchView = (MaterialSearchView) view.findViewById(R.id.search_view);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            for (Long cont = 0L; cursor.getCount() != cont; cont++) {

                produto = new Produto();
                produto.setCodproduto(cursor.getString(cursor.getColumnIndex("codproduto")));
                produto.setMercadoria(cursor.getString(cursor.getColumnIndex("mercadoria")));
                produto.setValorprazo(cursor.getDouble(cursor.getColumnIndex("valorprazo")));
                cursor.moveToNext();
                produtoList.add(produto);
            }

            cursor.close();
            ArrayAdapter<Produto> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, produtoList);
            listaProduto.setAdapter(adapter);
            listaProduto.setEmptyView(view.findViewById(R.id.semdados));

//            listaProduto.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }


//        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//
//                //If closed Search View , lstView will return default
//                ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, produtoList);
//                listaProduto.setAdapter(adapter);
//
//            }
//        });
//
//        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (newText != null && !newText.isEmpty()) {
//                    List<Produto> lstFound = new ArrayList<>();
//                    for (Produto item : produtoList) {
//                        if (item.getMercadoria().toLowerCase().contains(newText.toLowerCase()))
//                            lstFound.add(item);
//                    }
//
//                    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, lstFound);
//                    listaProduto.setAdapter(adapter);
//                } else {
//                    //if search text is null
//                    //return default
//                    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, produtoList);
//                    listaProduto.setAdapter(adapter);
//                }
//                return true;
//            }
//
//        });


//        listaProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position , long l) {
//                Produto produto1 = (Produto) listaProduto.getItemAtPosition(position);
//                ProdutoDados pedidoDados = new ProdutoDados();
//                Bundle bundle = new Bundle();
//                bundle.putString("codigo", produto1.getCodproduto());
//                pedidoDados.setArguments(bundle);
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, pedidoDados, pedidoDados.getTag()).commit();
//            }
//        });

        return view;

    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem mSearchMenuItem = menu.findItem(R.menu.menu_item_pesquisar);
//        SearchView searchView = (SearchView) mSearchMenuItem.getActionView();
//    }

    //    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        //menu.clear();
//        inflater.inflate(R.menu.menu_item_pesquisar, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
////        SearchManager searchManager = (SearchManager)getContext().getSystemService(getContext().SEARCH_SERVICE);
////        SearchView searchView =
////                (SearchView) menu.findItem(R.id.action_search).getActionView();
////        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
////        MenuItem item = menu.findItem(R.id.action_search);
////        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
////        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
////        MenuItemCompat.setActionView(item, searchView);
//    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item_pesquisar, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    try {


                    if (newText != null && !newText.isEmpty()) {
                        List<Produto> lstFound = new ArrayList<>();
                        for (Produto item : produtoList) {

                            if (item.getMercadoria() != null && item.getMercadoria().toLowerCase().contains(newText.toLowerCase()))
                                lstFound.add(item);
                        }

                        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, lstFound);
                        listaProduto.setAdapter(adapter);
                    } else {
                        //if search text is null
                        //return default
                        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, produtoList);
                        listaProduto.setAdapter(adapter);
                    }
                    }catch (Exception e){
                        Log.e("ERRO", e.getMessage());
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener((android.support.v7.widget.SearchView.OnQueryTextListener) queryTextListener);
        return super.onOptionsItemSelected(item);
    }
}
