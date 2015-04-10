package cz.uruba.airportcodes.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.uruba.airportcodes.R;

/**
 * Created by Vaclav on 18.1.2015.
 */
public class AirportDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_airport_detail, container, false);


        return rootView;
    }
}
