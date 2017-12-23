package com.chandrakant.abc.crm_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABC on 01/13/2017.
 */

public class AddnonDataAdapter extends RecyclerView.Adapter<AddnonDataAdapter.ContactviewHolder> {

    ArrayList<AddData> addData = new ArrayList<>();
    ArrayList<ListModel> addData1 = new ArrayList<>();
    private List<ListModel> mListDemo;
    private List<ListModel> orig;
Context context ;
    String dail;

    AlertDialog.Builder dlg;
    AlertDialog dialog;
    static int r;
    LinearLayout linearLayout,distributorinfo,ordercollection,stockcollection;
    ImageView deletedata,closee;
    RelativeLayout cardViewrr;

    public AddnonDataAdapter(ArrayList<AddData> addData) {
        this.addData = addData;
    }




    @Override
    public ContactviewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout_not_editable, parent, false);
        ContactviewHolder contactviewHolder = new ContactviewHolder(view);
        cardViewrr = (RelativeLayout) view.findViewById(R.id.cardViewrr);
        linearLayout = (LinearLayout) view.findViewById(R.id.Bottom);


        cardViewrr.setOnClickListener(new View.OnClickListener() {
            int flag = 0;

            @Override
            public void onClick(View view) {

                dlg = new AlertDialog.Builder(parent.getContext());


                View v = View.inflate(parent.getContext(), R.layout.bottom_layoutnoteditable, null);
                    /*dialog = (AlertDialog) new Dialog(parent.getContext(),
                            android.R.style.Theme_Translucent_NoTitleBar);*/

                distributorinfo = (LinearLayout) v.findViewById(R.id.distributorinfo);
                ordercollection = (LinearLayout) v.findViewById(R.id.ordercollection);
                stockcollection = (LinearLayout) v.findViewById(R.id.stockcollection);
                closee = (ImageView) v.findViewById(R.id.closee);
                closee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                distributorinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        parent.getContext().startActivity(new Intent(parent.getContext(), DistributorInfo.class));
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
                        parent.getContext().startActivity(new Intent(parent.getContext(), OrderActivity.class));
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
                        parent.getContext().startActivity(new Intent(parent.getContext(), Stock_Collection.class));
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

                dlg.setView(v);
                dialog = dlg.create();
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                dialog.setCancelable(false);
                window.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.FILL_PARENT);
                dialog.setTitle(null);
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();


            }
        });


        return contactviewHolder;
    }

    @Override
    public void onBindViewHolder(final ContactviewHolder holder, final int position) {



                holder.pjpimage.setImageResource(addData.get(position).getImage_id());
                holder.distributorname.setText(addData.get(position).getDistributorname());
                holder.address.setText(addData.get(position).getAddress());
                holder.contact.setText(addData.get(position).getContact());

              dail=addData.get(position).getContact();

            /*holder.deletedata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
            });*/



    }




    @Override
    public int getItemCount() {
        return addData.size();
    }


    public static class ContactviewHolder extends RecyclerView.ViewHolder{

        ImageView pjpimage,deletedata;
        TextView distributorname,address,contact;
        RelativeLayout cardViewrr;
        RecyclerView.ViewHolder viewHolder;
        public ContactviewHolder(View view) {
           super(view);
            pjpimage=(ImageView)view.findViewById(R.id.pjpimage);
            distributorname=(TextView)view.findViewById(R.id.distributorName);
            address=(TextView)view.findViewById(R.id.address);
            contact=(TextView) view.findViewById(R.id.contact);
           cardViewrr = (RelativeLayout)view.findViewById(R.id.cardViewrr);
            deletedata=(ImageView)view.findViewById(R.id.deletedata);

        }


    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<ListModel> results = new ArrayList<>();
                if (orig == null)
                    orig = mListDemo;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final ListModel g : orig) {
                            if (g.getList().get(0).getDistributorname().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);

                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                mListDemo = (ArrayList<ListModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
