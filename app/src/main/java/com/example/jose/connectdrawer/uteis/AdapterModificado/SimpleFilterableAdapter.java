package com.example.jose.connectdrawer.uteis.AdapterModificado;

import android.content.Context;

import java.util.List;

public class SimpleFilterableAdapter<ObjectType> extends FilterableAdapter<ObjectType, String> {

    public SimpleFilterableAdapter(Context context, int resourceId, int textResourceId, List<ObjectType> objects) {
        super(context, resourceId, textResourceId, objects);
    }

    public SimpleFilterableAdapter(Context context, int resourceId,
                                   List<ObjectType> objects) {
        super(context, resourceId, objects);
    }

    public SimpleFilterableAdapter(Context context, int resourceId) {
        super(context, resourceId);
    }

    public SimpleFilterableAdapter(Context context, List<ObjectType> objects) {
        super(context, objects);
    }

    public SimpleFilterableAdapter(Context context) {
        super(context);
    }

    @Override
    protected String prepareFilter(CharSequence seq) {
        return seq.toString().toLowerCase();
    }

    @Override
    protected boolean passesFilter(ObjectType object, String constraint) {
        String repr = object.toString().toLowerCase();

        if (repr.startsWith(constraint))
            return true;

        else {
            final String[] words = repr.split(" ");
            final int wordCount = words.length;

            for (int i = 0; i < wordCount; i++) {
                if (words[i].startsWith(constraint.trim()))
                    return true;
            }

            if (object.toString().toLowerCase().contains(constraint.toLowerCase())){
                return true;
            }
        }

        return false;
    }
}