//package com.example.jose.connectdrawer.A7;
//
//import java.io.IOException;
//import java.util.Set;
//
//import com.example.jose.connectdrawer.A7.BarCode;
//import com.example.jose.connectdrawer.A7.BarI25;
//import com.example.jose.connectdrawer.A7.Bluetooth;
//import com.example.jose.connectdrawer.A7.ESCP;
//import android.os.Bundle;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.bluetooth.BluetoothDevice;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.graphics.Bitmap;
//import android.graphics.Bitmap.Config;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.DashPathEffect;
//import android.graphics.Paint;
//import android.graphics.Paint.Style;
//import android.graphics.Rect;
//import android.graphics.Typeface;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.ScrollView;
//import android.widget.Toast;
//
//import com.example.jose.connectdrawer.R;
//import com.example.jose.connectdrawer.main.ConnectMain;
//
//import java.io.IOException;
//
//public class ImpressaoA7 {
//
//    Bluetooth mBth = new Bluetooth();
//    DrawView mDrawing;
//    Bitmap mBitmap = null;
//    static Bitmap mBitmapLogo = null;
//    static Bitmap mBitmapBanco = null;
//    Integer mDensity = 8;
//
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////
////        try {
////            mBitmapLogo = BitmapFactory.decodeStream(getAssets().open("rtsys.png"));
////            mBitmapBanco = BitmapFactory.decodeStream(getAssets().open("santander.png"));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////        mBitmap = createSample();
////
////        mDrawing = new DrawView(this);
////        ScrollView.LayoutParams lp = new ScrollView.LayoutParams(1000, 1000);
////        setContentView(mDrawing, lp);
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item)
////    {
////        switch (item.getItemId())
////        {
////            case R.id.action_imprimir:
////            {
////                if (!checkBth())
////                    return false;
////                if (mBitmap!=null)
////                {
////                    Toast.makeText(this, "Imprimindo...", Toast.LENGTH_LONG).show();
////                    ESCP.ImageToEsc(mBitmap, mBth.Ostream, 8, mDensity);
////                }
////                closeBth();
////                return true;
////            }
////        }
////        return super.onOptionsItemSelected(item);
////    }
////
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.main, menu);
////        return true;
////    }
////
////
////    public boolean closeBth()
////    {
////        if (mBth.isConnected())
////        {
////            return mBth.Close();
////        }
////        return false;
////    }
//
//    public boolean checkBth(Context context)
//    {
//        if (!mBth.isConnected())
//        {
//            if (!mBth.Enable())
//            {
//                Alert("Nao foi possivel abilitar bluetooth, tente abilitar manualmente e tente novamente.", context);
//                return false;
//            }
//            String mac=null;
//            Set<BluetoothDevice> devices = mBth.GetBondedDevices();
//            for (BluetoothDevice device : devices)
//            {
//                if ("MPT-III".equals(device.getName()))
//                {
//                    mac = device.getAddress();
//                }
//            }
//            if (mac==null)
//            {
//                Alert("Nao foi encontrada MPT-III\n\nFaça o pareamento com o disposivo e tente novamente.", context);
//                return false;
//            }
//            if (!mBth.Open(mac))
//            {
//                Alert("Nao foi possivel conectar ao dispositivo ["+mac+"]\n\nLigue ou conecte o dispositivo e tente novamente.", context);
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public void Alert(String message,Context context)
//    {
//        AlertDialog ad = new AlertDialog.Builder(context).create();
//        ad.setCancelable(false);
//        ad.setMessage(message);
//        ad.setButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        ad.show();
//    }
//
//
//    public static Bitmap createSample()
//    {
//        int x=0, y=0, w=576, h=w*5;
//        int size_text=32, size_legend=22;
//
//        Paint fontTitleBold = new Paint(Color.BLACK);
//        fontTitleBold.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
//        fontTitleBold.setTextSize((int)(size_text*1.2));
//
//        Paint fontText = new Paint(Color.BLACK);
//        fontText.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
//        fontText.setTextSize((int)(size_text));
//
//        Paint fontTextBold = new Paint(Color.BLACK);
//        fontTextBold.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
//        fontTextBold.setTextSize((int)(size_text));
//
//        Paint fontLegend = new Paint(Color.BLACK);
//        fontLegend.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.NORMAL));
//        fontLegend.setTextSize(size_legend);
//
//        Paint fontLegendBold = new Paint(Color.BLACK);
//        fontLegendBold.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD));
//        fontLegendBold.setTextSize(size_legend);
//
//        Paint p = new Paint(Color.RED);
//        p.setStyle(Style.STROKE);
//        p.setStrokeWidth(2);
//
//
//        //------------------------------------------------
//        //Bitmap não rotacionado usado para DANFE
//        //------------------------------------------------
//        Bitmap BitmapDanfe = Bitmap.createBitmap(w, h, Config.RGB_565);
//        Canvas g = new Canvas(BitmapDanfe);
//        g.drawColor(Color.WHITE);
//
//        g.drawRect(x, y, w, y+90, p);
//
////        if (mBitmapLogo!=null)
////        {
////            x+=10;
////            y+=10;
////            g.drawBitmap(mBitmapLogo, x, y, p);
////        }
////
////        x += mBitmapLogo.getWidth()+10;
////        y += size_text;
////        g.drawText("EXEMPLO GRAFICO", x, y, fontTitleBold );
//
//        y+= size_text;
//        g.drawText("LEOPARDO A7 ESC/P", x, y, fontTextBold);
//
//        x=0;
//        y=90;
//        g.drawRect(x, y, w, y+size_legend+size_text, p);
//        x=10;
//        y+=size_legend;
//        g.drawText("Exemplo de CHAVE DE ACESSO", x, y, fontLegendBold);
//        y+=size_legend;
//
//        String chave = "00123789456963258741147852369987456321015973";
//        g.drawText(chave, x, y, fontLegend);
//
//        y+=20;
//        BarCode.drawBarCode128C(g,chave,x, y, w, 80);
//
//        x=0;
//        y+=105;
//        p.setPathEffect(new DashPathEffect(new float[] {10,20}, 0));
//        g.drawLine(0, y, w, y, p);
//        p.setPathEffect(null);
//        y+=70;
//        x=0;
//
//        int ay = y;
//        //------------------------------------------------
//        //Bitmap rotacionado usado para Boletos
//        //------------------------------------------------
//        int W=1200, H=w;
//        Bitmap BitmapBoleto = Bitmap.createBitmap(H, W, Config.RGB_565);
//        Canvas g2 = new Canvas(BitmapBoleto);
//        g2.drawColor(Color.WHITE);
//
//        g2.rotate(90,H/2,H/2);
//
//        String linhaDigitavel = "03399.64355 86600.000003 08288.001012 1 61950000000123";
//        String codigoBarras = "03391619500000001239643586600000000828800101";
//
//        x=0; y=0;
//        if (mBitmapBanco!=null)
//        {
//            g2.drawBitmap(mBitmapBanco, x, y, p);
//        }
//
//        g2.drawLine(x, y+50, W, y+50, p);
//        g2.drawRect(x, y+50, W, H-100, p);
//
//        g2.drawText(linhaDigitavel, 250, 40, fontTextBold);
//
//        g2.drawText("EXEMPLO DE ROTACIONAMENTO PARA IMPRESSÃO DE BOLETO.", 20, H/2, fontTextBold);
//        BarI25 b25 = new BarI25();
//        Bitmap i25 = b25.createI25(codigoBarras);
//        g2.drawBitmap(i25, 20, H-97, p);
//
//        //------------------------------------------------
//        x=0; y=ay;
//        g.drawBitmap(BitmapBoleto, x, y, p);
//
//        //------------------------------------------------
//
//        x=0; y+=1300;
//
//        h = (int)(y / 64);
//        h = ((h+1) * 64);
//
//        Bitmap BitmapReturn = Bitmap.createBitmap(BitmapDanfe.getWidth(), h, Config.RGB_565);
//        Canvas g3 = new Canvas(BitmapReturn);
//
//        g3.drawBitmap(BitmapDanfe, 0, 0, p);
//
//        return BitmapReturn;
//
//    }
//
//
//    private class DrawView extends View
//    {
//        private boolean move=false;
//        private int X=0, Y=0, iX=0, iY=0;
//
//        public DrawView(Context context) {
//            super(context);
//            this.setBackgroundResource(R.color.green);
//        }
//        @Override
//
//        public boolean onTouchEvent(final MotionEvent event)
//        {
//            boolean handled = false;
//            int xTouch;
//            int yTouch;
//            int pointerId;
//            int actionIndex = event.getActionIndex();
//
//            switch (event.getActionMasked()) {
//                case MotionEvent.ACTION_DOWN:
//                    xTouch = (int) event.getX(0);
//                    yTouch = (int) event.getY(0);
//
//                    iX=(xTouch-X);
//                    iY=(yTouch-Y);
//
//                    invalidate();
//                    handled = true;
//                    move = true;
//                    break;
//
//                case MotionEvent.ACTION_POINTER_DOWN:
//                    pointerId = event.getPointerId(actionIndex);
//
//                    xTouch = (int) event.getX(actionIndex);
//                    yTouch = (int) event.getY(actionIndex);
//
//                    iX=(xTouch-X);
//                    iY=(yTouch-Y);
//
//                    invalidate();
//                    handled = true;
//                    move=true;
//                    break;
//
//                case MotionEvent.ACTION_MOVE:
//                    final int pointerCount = event.getPointerCount();
//
//                    for (actionIndex = 0; actionIndex < pointerCount; actionIndex++)
//                    {
//                        pointerId = event.getPointerId(actionIndex);
//
//                        xTouch = (int) event.getX(actionIndex);
//                        yTouch = (int) event.getY(actionIndex);
//
//                        if (move) {
//                            X = (xTouch - iX );
//                            Y = (yTouch - iY);
//                        }
//                    }
//                    invalidate();
//                    handled = true;
//                    break;
//
//                case MotionEvent.ACTION_UP:
//                    move=false;
//                    invalidate();
//                    handled = true;
//                    break;
//
//                case MotionEvent.ACTION_POINTER_UP:
//                    move=false;
//                    pointerId = event.getPointerId(actionIndex);
//                    invalidate();
//                    handled = true;
//                    break;
//
//                case MotionEvent.ACTION_CANCEL:
//                    move=false;
//                    handled = true;
//                    break;
//
//                default:
//                    break;
//            }
//
//            return super.onTouchEvent(event) || handled;
//        }
//
//        protected void onDraw(Canvas canvas)
//        {
//            if (mBitmap!=null)
//            {
//                Paint myPaint = new Paint();
//                myPaint.setColor(Color.BLACK);
//
//                boolean resize=false;
//                if (!resize){
//                    canvas.drawBitmap(mBitmap, X, Y, myPaint);
//                }
//                else
//                {
//                    int ih = mBitmap.getHeight();
//                    int iw = mBitmap.getWidth();
//                    int mh = getHeight();
//                    float fat=( ih / mh);
//                    int mw = (int)((iw * mh)/ih);
//                    canvas.drawBitmap(mBitmap,
//                            new Rect(0,0,iw,ih),
//                            new Rect(0,0,mw,mh),
//                            myPaint);
//                }
//            }
//
//        }
//    }
//
//
//}
