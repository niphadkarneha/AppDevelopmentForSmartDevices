package com.example.nehaniphadkar.contacts;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by neha on 02-Mar-18.
 */

public class DescriptionFragment extends Fragment {

    private View view;
    private ListView listcontact;
    private TextView name1,number1;
    private byte[] bytes;
    private ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_description,
                container, false);
        listcontact = (ListView) view.findViewById(R.id.list);
        name1=(TextView)view.findViewById(R.id.name);
        number1=(TextView)view.findViewById(R.id.number);
        image=(ImageView)view.findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            }
        });




        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString("name");
            String number = bundle.getString("number");
            bytes=bundle.getByteArray("image");

            if (bytes.length==12){
                image.setBackground(getActivity().getResources().getDrawable(R.drawable.gallery));
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 1);
                    }
                });
            }else{
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(),ImageShowActivity.class);
                        intent.putExtra("bytes",bytes);
                        startActivity(intent);
                    }
                });
            }
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            image.setImageBitmap(bitmap);
            System.out.println("initial"+bitmap);
            name1.setText(name);
            number1.setText(number);

            String json = bundle.getString("json");
            try {
                JSONArray jsonArray=new JSONArray(json);
                Type listType = new TypeToken<List<Contact>>() {
                }.getType();
                List<Contact> yourList = new Gson().fromJson(json, listType);
                RelationShipAdapter relationShipAdapter=new RelationShipAdapter(getActivity(),yourList);
                listcontact.setAdapter(relationShipAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Override this method in the activity that hosts the Fragment and call super
        // in order to receive the result inside onActivityResult from the fragment.
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            DBHelper helper = new DBHelper(getActivity());
            Contact contact=helper.getContact(name1.getText().toString());
            String name=contact.getName();
            String number= contact.getNumber();
            String json= contact.getJson();
            final byte[] bytes=byteArray;
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(),ImageShowActivity.class);
                    intent.putExtra("bytes",bytes);
                    startActivity(intent);
                }
            });
            Contact contact1=new Contact();
            contact1.setName(name);
            contact1.setJson(json);
            contact1.setNumber(number);
            contact1.setBlob(bytes);
            if(helper.update(name,contact1)){
                Toast.makeText(getActivity(), "Profile Photo updated Sucessfully", Toast.LENGTH_SHORT).show();
            }
            image.setImageBitmap(photo);
        }
    }
}
