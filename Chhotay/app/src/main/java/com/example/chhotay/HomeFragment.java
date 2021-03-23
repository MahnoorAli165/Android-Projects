package com.example.chhotay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View mContentView;
    List<String> titles;
    List<Integer> images;
    private static final String TAG = "ToolbarFragment";
    private static final int STANDARD_APPBAR = 0;
    private static final int SEARCH_APPBAR = 1;
    private int mAppBarState;

    private AppBarLayout viewContactsBar, searchBar;


    @SuppressLint("WrongThread")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        SearchBarFragment myFragment = getChildFragmentManager().findFragmentById(R.id.search_fragment);


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewContactsBar = (AppBarLayout) view.findViewById(R.id.Toolbar);
        searchBar = (AppBarLayout) view.findViewById(R.id.searchToolbar);

        Log.d(TAG, "onCreateView: started");

        setAppBaeState(STANDARD_APPBAR);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pList);

        // 2. set layoutManger
//        GridLayoutManager gridLayoutManager=  new GridLayoutManager(this,2, VERTICAL,false);
//        pList.setLayoutManager(gridLayoutManager);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));

        // this is data fro recycler view
        titles = new ArrayList<>();
        images = new ArrayList<>();
//
        titles.add("First Item");
        titles.add("Second Item");
//        titles.add("Third Item");
//        titles.add("Fourth Item");
//        titles.add("Fifih Item");
//        titles.add("First Item");
//        titles.add("Second Item");

        images.add(R.drawable.itemone);
        images.add(R.drawable.itemone);
//        images.add(R.drawable.itemone);
//        images.add(R.drawable.itemone);
//        images.add(R.drawable.itemone);
//        images.add(R.drawable.itemone);
//        images.add(R.drawable.itemone);

        Adapter adapter = new Adapter(getActivity(), titles, images);
        // 4. set adapter
        recyclerView.setAdapter(adapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        ImageView ivSearchContact = (ImageView) view.findViewById(R.id.ivSearchIcon);
        ivSearchContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked searched icon");
                toggleToolBarState();
            }
        });

        ImageView ivBackArrow = (ImageView) view.findViewById(R.id.ivBackArrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked back arrow.");
                toggleToolBarState();

            }
        });

        return view;

//        int spanCount = 3; // 3 columns
//        int spacing = 50; // 50px
//        boolean includeEdge = true;
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));


    }

    // Initiate toggle (it means when you click the search icon it pops up the editText and clicking the back button goes to the search icon again)
    private void toggleToolBarState() {
        Log.d(TAG, "toggleToolBarState: toggling AppBarState.");
        if (mAppBarState == STANDARD_APPBAR) {
            setAppBaeState(SEARCH_APPBAR);
        } else {
            setAppBaeState(STANDARD_APPBAR);
        }
    }

    // Sets the appbar state for either search mode or standard mode.
    private void setAppBaeState(int state) {

        Log.d(TAG, "setAppBaeState: changing app bar state to: " + state);

        mAppBarState = state;
        if (mAppBarState == STANDARD_APPBAR) {
            searchBar.setVisibility(View.GONE);
            viewContactsBar.setVisibility(View.VISIBLE);

            View view = getView();
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            try {
                im.hideSoftInputFromWindow(view.getWindowToken(), 0); // make keyboard hide
            } catch (NullPointerException e) {
                Log.d(TAG, "setAppBaeState: NullPointerException: " + e);
            }
        } else if (mAppBarState == SEARCH_APPBAR) {
            viewContactsBar.setVisibility(View.GONE);
            searchBar.setVisibility(View.VISIBLE);
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0); // make keyboard popup

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setAppBaeState(STANDARD_APPBAR);
    }

}
//@Override
//public void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setHasOptionsMenu(true); // It's important here
//}
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        // Associate searchable configuration with the SearchView
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//        Log.d("Tab", "SearchManager: " + searchManager + " : " + searchView);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//    }

//        @Override
//        public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true); // It's important here
//        }


