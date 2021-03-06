package com.example.alexandrzanko.tablet_6vkusov.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandrzanko.tablet_6vkusov.Activities.BasketActivity;
import com.example.alexandrzanko.tablet_6vkusov.Models.ProductItem;
import com.example.alexandrzanko.tablet_6vkusov.Models.Variant;
import com.example.alexandrzanko.tablet_6vkusov.R;
import com.example.alexandrzanko.tablet_6vkusov.Singleton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by alexandrzanko on 07/12/16.
 */

public class BasketAdapter extends BaseAdapter {
    private ArrayList<ProductItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public BasketAdapter(Context context, ArrayList<ProductItem> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        convertView = layoutInflater.inflate(R.layout.product_item_basket_layout, null);
        holder = new ViewHolder();
        holder.productName     = (TextView) convertView.findViewById(R.id.product_name);
        holder.productDescription = (TextView) convertView.findViewById(R.id.product_description);
        holder.productImg      = (ImageView) convertView.findViewById(R.id.product_icon);
        holder.productWidth    = (TextView) convertView.findViewById(R.id.product_width);
        holder.productPrice    = (TextView) convertView.findViewById(R.id.product_price);
        holder.countProducts   = (TextView) convertView.findViewById(R.id.product_count);
        holder.btnPlus         = (Button) convertView.findViewById(R.id.product_btn_plus);
        holder.btnMinus        = (Button) convertView.findViewById(R.id.product_btn_minus);

        holder.btnRemove       = (Button) convertView.findViewById(R.id.product_btn_remove);

        convertView.setTag(holder);

        final ProductItem productItem = listData.get(position);

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productItem.addCount();
                Singleton.currentState().getUser().getBasket().addProductItemOneRegister(productItem);
                holder.countProducts.setText(productItem.get_count() + "");
                holder.productPrice.setText(getTotalPriceVariant(productItem) + " р.");
                ((BasketActivity)context).checkOutUpdateView();
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productItem.minusCount();
                int count = productItem.get_count();
                if (count > 0) {
                    holder.countProducts.setText(count + "");
                    holder.productPrice.setText(getTotalPriceVariant(productItem) + " р.");
                    Singleton.currentState().getUser().getBasket().minusProductItem(productItem);
                }else{
                    listData.remove(productItem);
                    Singleton.currentState().getUser().getBasket().removeProductItem(productItem);
                    notifyDataSetChanged();
                    Singleton.currentState().getUser().getBasket().getDelegateContext().updateBasket(0);
                }
                ((BasketActivity)context).checkOutUpdateView();

            }
        });

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listData.remove(productItem);
                Singleton.currentState().getUser().getBasket().removeProductItem(productItem);
                notifyDataSetChanged();
                Singleton.currentState().getUser().getBasket().getDelegateContext().updateBasket(0);
                ((BasketActivity)context).checkOutUpdateView();
            }
        });

        if(productItem.get_points() > 0){
            holder.productDescription.setText(productItem.get_points() + " баллов");
            holder.btnPlus.setVisibility(View.GONE);
            holder.btnMinus.setVisibility(View.GONE);
            holder.countProducts.setVisibility(View.GONE);
        }else{
            holder.productDescription.setText(productItem.get_description());
        }
        holder.productPrice.setText(getTotalPriceVariant(productItem) + " р.");
        Variant variant = productItem.get_variant();
        String weight = variant.get_weigth();
        String size = variant.get_size();
        if(!weight.equals("null")){
            holder.productWidth.setText("Вес " + weight);
        }
        if(!size.equals("null")){
            holder.productWidth.setText(size);
        }
        holder.countProducts.setText(productItem.get_count() + "");
        holder.productName.setText(productItem.get_name());
        Picasso.with(context)
                .load(productItem.get_icon())
                .placeholder(R.drawable.ic_thumbs_up) //показываем что-то, пока не загрузится указанная картинка
                .error(R.drawable.ic_thumb_down) // показываем что-то, если не удалось скачать картинку
                .into(holder.productImg);
        return convertView;
    }

    private String getTotalPriceVariant(ProductItem item){
        int count = item.get_count();
        double price = item.get_variant().get_price() * count;
        double pr = (double) Math.round(price * 100);
        double prD = pr/100;
        return pr%10 == 0 ? prD + "0" : prD + "";
    }


    static class ViewHolder {
        TextView  productName;
        TextView  productWidth;
        TextView  productPrice;
        TextView  productDescription;
        ImageView productImg;
        TextView  countProducts;
        Button    btnPlus, btnMinus, btnRemove;
    }

}