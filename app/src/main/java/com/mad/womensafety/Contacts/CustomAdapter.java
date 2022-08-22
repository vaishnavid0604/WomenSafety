package com.mad.womensafety.Contacts;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mad.womensafety.MainActivity;
import com.mad.womensafety.R;
import com.mad.womensafety.SmsActivity;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<ContactModel> {

    Context context;
    List<ContactModel> contacts;

    public CustomAdapter(@NonNull Context context, List<ContactModel> contacts) {
        super(context, 0, contacts);
        this.context=context;
        this.contacts=contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //create a database helper object to handle the database manipulations
        DbHelper db=new DbHelper(context);

        // Get the data item for this position
        ContactModel c = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }

        LinearLayout linearLayout=(LinearLayout)convertView.findViewById(R.id.linear);

        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);

        ImageView ivDelete = (ImageView) convertView.findViewById(R.id.ivDelete);

        // Populate the data into the template view using the data object
        tvName.setText(c.getName());
        tvPhone.setText(c.getPhoneNo());


        // adding on click listener for delete button to delete our course.
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Do u really want to delete ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //delete the specified contact from the database
                                db.deleteContact(c);
                                //remove the item from the list
                                contacts.remove(c);
                                //notify the listview that dataset has been changed
                                notifyDataSetChanged();
                                Toast.makeText(context, "Contact removed!", Toast.LENGTH_SHORT).show();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    //this method will update the ListView
    public void refresh(List<ContactModel> list){
        contacts.clear();
        contacts.addAll(list);
        notifyDataSetChanged();
    }
}
