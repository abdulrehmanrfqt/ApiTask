package com.abdulrehman.apitask;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdulrehman.apitask.Adapters.Models.HorizontalModel;
import com.abdulrehman.apitask.Adapters.Models.VerticalModel;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TabLayoutToolbarActivity extends AppCompatActivity {

//    public static final String TITLE = "title";
    public static final String TAG = "TAG";
    ArrayList<VerticalModel> arrayListVertical;
    private RequestQueue mRequestQueue;
    List<String> titlesList = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_tablayout);

        arrayListVertical = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        setData();

    }

    private void setData() {

        String url = "https://app.tapmad.com/api/getFeaturedHomePageDetail/V1/en/android";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response);
                        try {
                            JSONArray Tabs = response.getJSONArray("Tabs");
                            for (int tabIndex = 0; tabIndex < Tabs.length(); tabIndex++) {
                                JSONObject tab = Tabs.getJSONObject(tabIndex);
                                titlesList.add(tab.getString("TabName"));
                            }
                            JSONObject TabsData = Tabs.getJSONObject(0);
                            JSONArray Sections = TabsData.getJSONArray("Sections");

                            Log.d(TAG, "onResponse: " + Sections);

                            for (int i = 0; i < Sections.length(); i++) {
                                JSONObject SectionsData = Sections.getJSONObject(i);
                                String SectionName = SectionsData.getString("SectionName");
                                VerticalModel mVerticalModel = new VerticalModel();
                                mVerticalModel.setTitle(SectionName);

                                ArrayList<HorizontalModel> horizontalArrayList = new ArrayList<>();

//                                JSONArray videosArray = SectionsData.getJSONArray("Videos");

                                if (SectionsData.has("Videos")) {
                                    JSONArray videosArray = SectionsData.getJSONArray("Videos");

                                    for (int j = 0; j < videosArray.length(); j++) {
                                        JSONObject videoObject = videosArray.getJSONObject(j);
                                        HorizontalModel mhorizontalModel = new HorizontalModel(
                                                videoObject.getString("NewChannelThumbnailPath")
                                        );

                                        mhorizontalModel.setDescription("Description :" +
                                                (videoObject.has("VideoDescription") ?
                                                        videoObject.getString("VideoDescription")
                                                        : "No description found"));
                                        mhorizontalModel.setName("Name :" +
                                                (videoObject.has("VideoName") ?
                                                        videoObject.getString("VideoName")
                                                        : "No name found"));
                                        horizontalArrayList.add(mhorizontalModel);
                                    }
                                }

                                if (SectionsData.has("Categories")) {
                                    JSONArray categoriesArray = SectionsData.getJSONArray("Categories");

                                    for (int j = 0; j < categoriesArray.length(); j++) {
                                        JSONObject videoObject = categoriesArray.getJSONObject(j);
                                        HorizontalModel mhorizontalModel = new HorizontalModel(
                                                videoObject.getString("NewCategoryImage")
                                        );

                                        mhorizontalModel.setDescription("Description :" +
                                                (videoObject.has("VideoName") ?
                                                        videoObject.getString("VideoName")
                                                        : "No description found"));
                                        mhorizontalModel.setName("Name :" +
                                                (videoObject.has("CategoryName") ?
                                                        videoObject.getString("CategoryName")
                                                        : "No name found"));
                                        horizontalArrayList.add(mhorizontalModel);
                                    }
                                }
//                                for (int j = 0; j < videosArray.length(); j++) {
//                                    JSONObject videoObject = videosArray.getJSONObject(j);
//                                    HorizontalModel mhorizontalModel = new HorizontalModel(
//                                            videoObject.getString("NewChannelThumbnailPath")
//                                    );
//                                    mhorizontalModel.setDescription("Description :" +
//                                            (videoObject.has("VideoDescription") ?
//                                                    videoObject.getString("VideoDescription")
//                                                    : "No description found"));
//                                    mhorizontalModel.setName("Name :" +
//                                            (videoObject.has("VideoName") ?
//                                                    videoObject.getString("VideoName")
//                                                    : "No name found"));
//                                    horizontalArrayList.add(mhorizontalModel);
//                                }

                                mVerticalModel.setArrayList(horizontalArrayList);
                                arrayListVertical.add(mVerticalModel);

                            }

                            setupViewPager();

                        } catch (JSONException e) {
                            Toast.makeText(TabLayoutToolbarActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });


        mRequestQueue.add(request);

        request.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void setupViewPager() {

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        SimpleFragmentAdapter adapter = new SimpleFragmentAdapter(getSupportFragmentManager(), titlesList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    class SimpleFragmentAdapter extends FragmentPagerAdapter {


        private final List<String> titlesList;

        public SimpleFragmentAdapter(FragmentManager fm, List<String> titlesList) {
            super(fm);
            this.titlesList = titlesList;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titlesList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MainFragment.getInstance(arrayListVertical);
                default:
                    return new SimpleFragment();

                case 1:
                    return ErosFragment.getInstance(arrayListVertical);
//                default:
//                    return new SimpleFragment();
            }
        }

        @Override
        public int getCount() {
            return titlesList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }


}
