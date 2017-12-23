package com.chandrakant.abc.crm_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by ABC on 01/13/2017.
 */

public class AddDataAdapter extends RecyclerView.Adapter<AddDataAdapter.ContactviewHolder> {

    ArrayList<AddData> addData = new ArrayList<>();
    LinearLayout linearLayout,distributorinfo,ordercollection,stockcollection;
   String dail,deleter;
    ImageView deletedata,closee;
    RelativeLayout cardViewrr1;
    AlertDialog.Builder dlg;
     AlertDialog dialog;
    static int r;
    TelephonyManager tm;
Context context;


    ListView list;
private static final String URL = "http://www.gosalesplus.com/service.asmx";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String SOAP_ACTION = "http://tempuri.org/deletepjp";
    private static final String METHOD_NAME = "deletepjp";

    public AddDataAdapter(Context  context, ArrayList<AddData> addData) {
        this.context = context;
        this.addData = addData;
    }




    @Override
    public ContactviewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);
     View viewd = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_todays_pjp,parent,false);
        final ContactviewHolder contactviewHolder = new ContactviewHolder(view);
        deletedata=(ImageView)view.findViewById(R.id.deletedata);
//        Toast.makeText(parent.getContext(),""+viewType,Toast.LENGTH_SHORT).show();
       final TextView contact=(TextView)view.findViewById(R.id.contact);
                   deletedata=(ImageView)view.findViewById(R.id.deletedata);

       Animation  an= AnimationUtils.loadAnimation(parent.getContext(),R.anim.slide_up_dialog);
        final Animation an1= AnimationUtils.loadAnimation(parent.getContext(),R.anim.slide_out_down);
        cardViewrr1=(RelativeLayout)view.findViewById(R.id.cardViewrr1);
        linearLayout=(LinearLayout)viewd.findViewById(R.id.Bottom);



        return contactviewHolder;
    }

    @Override
    public void onBindViewHolder(final ContactviewHolder holder, final int position) {



                holder.pjpimage.setImageResource(addData.get(position).getImage_id());
                holder.distributorname.setText(addData.get(position).getDistributorname());
                holder.address.setText(addData.get(position).getAddress());
                holder.contact.setText(addData.get(position).getContact());
                holder.id.setText(addData.get(position).getId());



         holder.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {



              MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(view.getContext())
                        .setTitle("Call")
                        .setIcon(android.R.drawable.ic_menu_call)
                        .setDescription("Do you want to Call? \n")
                        .withIconAnimation(true)
                        .setPositiveText("Call")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Log.d("MaterialStyledDialogs", "Do something!");
                                dail=addData.get(position).getContact();
                            String    dname=addData.get(position).getDistributorname();
                                Intent callIntent = new Intent(context.getApplicationContext(),CallActivity.class);


            callIntent.putExtra("numb", dail);
            callIntent.putExtra("dname", dname);
            callIntent.putExtra("status", 0);
            context.startActivity(callIntent);





                            }
                        }).setNegativeText("Cancel").onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Log.d("MaterialStyledDialogs", "Do something!");
                                dialog.dismiss();

                            }
                      })
                      .show();
            }
         });




        cardViewrr1.setOnClickListener(new View.OnClickListener() {
            int flag=0;
            @Override
            public void onClick(View view) {

                dlg = new AlertDialog.Builder(view.getContext());

                View v = View.inflate(view.getContext(), R.layout.bottom_layout, null);
                    /*dialog = (AlertDialog) new Dialog(parent.getContext(),
                            android.R.style.Theme_Translucent_NoTitleBar);*/

                distributorinfo=(LinearLayout)v.findViewById(R.id.distributorinfo);
                ordercollection=(LinearLayout)v.findViewById(R.id.ordercollection);
                stockcollection=(LinearLayout)v.findViewById(R.id.stockcollection);
                closee=(ImageView) v.findViewById(R.id.closee);
                closee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                distributorinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view.getContext().startActivity(new Intent(view.getContext(),DistributorInfo.class));
                        ListModel.list1.clear();
                        ListModel.list2.clear();
                        ListModel.list3.clear();
                        ListModel.list4.clear();
                        ListModel.list5.clear();
                        ListModel.list6.clear();
                        ListModel.list7.clear();
                        ListModel.listback1.clear();
                        ListModel.listback2.clear();
                        ListModel.listback3.clear();
                        ListModel.listback4.clear();
                        ListModel.listback5.clear();
                        ListModel.listback6.clear();
                        ListModel.listback7.clear();
                        ListModel.list.clear();
                        dialog.dismiss();
                    }
                });

                ordercollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view.getContext().startActivity(new Intent(view.getContext(),OrderActivity.class));
                        ListModel.list1.clear();
                        ListModel.list2.clear();
                        ListModel.list3.clear();
                        ListModel.list4.clear();
                        ListModel.list5.clear();
                        ListModel.list6.clear();
                        ListModel.list7.clear();
                        ListModel.listback1.clear();
                        ListModel.listback2.clear();
                        ListModel.listback3.clear();
                        ListModel.listback4.clear();
                        ListModel.listback5.clear();
                        ListModel.listback6.clear();
                        ListModel.listback7.clear();
                        ListModel.list.clear();
                        dialog.dismiss();
                    }
                });
                stockcollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view.getContext().startActivity(new Intent(view.getContext(),Stock_Collection.class));

                        dialog.dismiss();
                    }
                });

                dlg.setView(v);
                dialog=dlg.create();
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                dialog.setCancelable(false);
                window.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
                dialog.setTitle(null);
                dialog.getWindow().getAttributes().windowAnimations=R.style.PauseDialogAnimation;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();





            }
        });



            holder.deletedata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(view.getContext())
                            .setTitle("Delete")
                            .setIcon(android.R.drawable.ic_delete)
                            .setDescription("Are you Sure ? \n")
                            .withIconAnimation(true)
                            .setPositiveText("Delete")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    Log.d("MaterialStyledDialogs", "Do something!");
                                    deleter=addData.get(position).getId();
                                    new Asynkdelete().execute();
                                    if(ListModel.list1.size()>0) {
                                        ListModel.getList1().remove(position);
                                    }
                                    else   if(ListModel.list2.size()>0) {
                                        ListModel.getList2().remove(position);
                                    }
                                    else   if(ListModel.list3.size()>0) {
                                        ListModel.getList3().remove(position);
                                    }
                                    else   if(ListModel.list4.size()>0) {
                                        ListModel.getList4().remove(position);
                                    }
                                    else   if(ListModel.list5.size()>0) {
                                        ListModel.getList5().remove(position);
                                    }
                                    else   if(ListModel.list6.size()>0) {
                                        ListModel.getList6().remove(position);
                                    }
                                    else   if(ListModel.list7.size()>0) {
                                        ListModel.getList7().remove(position);
                                    }


                                    ListModel.getList().remove(position);

                                    notifyDataSetChanged();

                                }
                            }).setNegativeText("Cancel").onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    Log.d("MaterialStyledDialogs", "Do something!");
                                    dialog.dismiss();

                                }
                            })
                            .show();

                }
            });

    }




    @Override
    public int getItemCount() {
        return addData.size();
    }




    public static class ContactviewHolder extends RecyclerView.ViewHolder{

        ImageView pjpimage,deletedata;
        TextView distributorname,address,contact,id;
        RelativeLayout cardViewrr;
        RecyclerView.ViewHolder viewHolder;
        LinearLayout linearLayout;
        EditText getnumber;
        Button call;
        ListView list;
        public ContactviewHolder(View view) {
           super(view);

            pjpimage=(ImageView)view.findViewById(R.id.pjpimage);
            distributorname=(TextView)view.findViewById(R.id.distributorName);
            address=(TextView)view.findViewById(R.id.address);
            contact=(TextView) view.findViewById(R.id.contact);
            id=(TextView) view.findViewById(R.id.id);
           cardViewrr = (RelativeLayout)view.findViewById(R.id.cardViewrr);
            deletedata=(ImageView)view.findViewById(R.id.deletedata);
            linearLayout=(LinearLayout)view.findViewById(R.id.Bottom);
            getnumber = (EditText)view. findViewById(R.id.getnumber);
            call = (Button) view.findViewById(R.id.calld);
            list = (ListView) view.findViewById(R.id.list);

        }


    }




    public class Asynkdelete extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);

                request.addProperty("id",deleter);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

            try {
                httpTransportSE.call(SOAP_ACTION,envelope);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


}
