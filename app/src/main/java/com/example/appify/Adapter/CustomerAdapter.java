package com.example.appify.Adapter;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appify.Customer;
import com.example.appify.R;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> implements View.OnCapturedPointerListener {

    private List<Customer> customerList;

    public CustomerAdapter(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, int position) {
        Customer customer = customerList.get(position);

        holder.nameTextView.setText(customer.getName());
        holder.emailTextView.setText(customer.getEmail());
        holder.phoneTextView.setText(customer.getPhoneNumber());
        holder.adressTextView.setText(customer.getAdress());
        holder.cityTextView.setText(customer.getCity());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    @Override
    public boolean onCapturedPointer(View view, MotionEvent event) {
        return false;
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView, phoneTextView,cityTextView,adressTextView;

        public CustomerViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.customerName);
            emailTextView = itemView.findViewById(R.id.customerEmail);
            phoneTextView = itemView.findViewById(R.id.customerPhone);
            cityTextView = itemView.findViewById(R.id.customerCity);
            adressTextView=itemView.findViewById(R.id.customerAdress);

        }
    }
}

