package com.example.jemmycalak.thisismymarket.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jemmycalak.thisismymarket.Adapter.GridViewAdapter;
import com.example.jemmycalak.thisismymarket.R;
import com.example.jemmycalak.thisismymarket.Model.object_product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LaptopFragment extends Fragment {
    public static final String url = "http://192.168.43.117/db_m_market_localhost/action/select_picture.php";
    GridView gridView;

    ArrayList<object_product> arrayProduk= new ArrayList<>();
    object_product ob;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.laptop_gridview, container, false);
        gridView = (GridView) view.findViewById(R.id.gv);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swip);

        getData();

        return view;
    }

    private void getData() {

        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Please wait...","Fetching data...",false,false);

        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();
                        //Displaying our grid
                        showGrid(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setMessage("Data tidak dapat si proses.");
                    }
                }
        );

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    private void showGrid(JSONArray jsonArray) {
        //clear dulu
        arrayProduk.clear();
        object_product ob;

        //Looping through all the elements of json array
        for (int i = 0; i < jsonArray.length(); i++) {

            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);

                //ambil data from mysql
                int id = obj.getInt("product_id");
                String name = obj.getString("product_nm");
                int hrg = obj.getInt("product_price");
                String descrip = obj.getString("product_desc");
                double brt = obj.getDouble("product_weight");
                int jmlh = obj.getInt("product_count");
                String clr = obj.getString("product_color");
                String imageUrl = obj.getString("product_img");

                //masukan ke file object_product
                ob=new object_product();

                ob.setId(id);
                ob.setNama(name);
                ob.setDesc(descrip);
                ob.setHrg(hrg);
                ob.setImgUrl(imageUrl);
                ob.setBrt(brt);
                ob.setJmlh(jmlh);
                ob.setColor(clr);

                //add to arraylist
                arrayProduk.add(ob);

                //just to show data
                //Toast.makeText(getContext(), "" + imageUrl, Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Creating GridViewAdapter Object
        //GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(), R.layout.laptop_item_gridview, arrayProduk);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(), arrayProduk);

        //Adding adapter to gridview
        //gridView.setAdapter(new GridViewAdapter(this.getActivity(), arrayProduk));

        gridView.setAdapter(gridViewAdapter);

    }
}
