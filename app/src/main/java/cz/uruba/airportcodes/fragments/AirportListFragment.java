package cz.uruba.airportcodes.fragments;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cz.uruba.airportcodes.R;
import cz.uruba.airportcodes.constants.MiscConstants;
import cz.uruba.airportcodes.constants.ContentProviderContracts;
import cz.uruba.airportcodes.interfaces.IContentProviderQuery;
import cz.uruba.airportcodes.models.Airport;

/**
 * Created by Vaclav on 21.12.2014.
 */
public class AirportListFragment extends Fragment {
    private RecyclerView airportList;
    private AirportAdapter airportAdapter;
    private final IContentProviderQuery queryAirportSummary = ContentProviderContracts
            .AirportsContract
            .QueryAirportSummaries.INSTANCE;

    public interface OnAirportSelectedListener {
        public void onAirportSelected(int position);
    }
    private OnAirportSelectedListener airportSelectedCallback;


    public AirportListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_airport_list, container, false);

        try {
            airportSelectedCallback = (OnAirportSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnAirportSelectedListener");
        }

        airportList = (RecyclerView) rootView.findViewById(R.id.airport_list);
        airportList.setHasFixedSize(true);
        airportList.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadAirportData();

        return rootView;
    }

    private void loadAirportData() {
        getLoaderManager().initLoader(0, null, new AirportLoaderCallbacks());
    }

    private void showAirportDetail(Airport airport) {
        Bundle b = new Bundle();
        b.putParcelable(MiscConstants.AIRPORT_BUNDLE_LABEL, airport);
    }

    private class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.viewHolder> {
        private Cursor cursor;
        private ContentObserver mDataChangedObserver;
        private DataSetObserver mDataSetObserver;
        private boolean mDataValid;

        private AirportAdapter() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        public AirportAdapter(Cursor cursor) {
            super();
            this.cursor = cursor;

            mDataChangedObserver = new ContentObserver(new Handler()) {
                @Override
                public boolean deliverSelfNotifications() {
                    return true;
                }

                @Override
                public void onChange(boolean selfChange) {
                    onContentChanged();
                }
            };

            mDataSetObserver = new DataSetObserver() {
                @Override
                public void onChanged() {
                    mDataValid = true;
                    notifyDataSetChanged();
                }

                @Override
                public void onInvalidated() {
                    mDataValid = false;
                    notifyItemRangeRemoved(0, getItemCount());
                }
            };

            if (cursor != null) {
                cursor.registerContentObserver(mDataChangedObserver);
                cursor.registerDataSetObserver(mDataSetObserver);
            }
        }

        @Override
        public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater
                                .from(parent.getContext())
                                .inflate(R.layout.list_item_airport, parent, false);
            return new viewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(viewHolder holder, int position) {
            cursor.moveToPosition(position);

            Airport airport = queryAirportSummary
                                .getAirportFromCursor(cursor);
            holder.airport_id.setText(String.valueOf(airport.getId()));
            holder.airport_name.setText(airport.getName());
        }

        @Override
        public int getItemCount() {
            if (cursor != null) {
                return cursor.getCount();
            }

            return 0;
        }

        private void onContentChanged() {

        }

        protected class viewHolder extends RecyclerView.ViewHolder {
            protected TextView airport_id, airport_name;

            public viewHolder(View itemView) {
                super(itemView);
                airport_id = (TextView) itemView.findViewById(R.id.airport_id);
                airport_name = (TextView) itemView.findViewById(R.id.airport_name);
            }
        }
    }

    private class AirportLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            String[] projection = queryAirportSummary
                                    .getProjection();

            return new CursorLoader(getActivity(), ContentProviderContracts.AirportsContract.CONTENT_URI, projection, null, null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            airportAdapter = new AirportAdapter(data);
            airportList.setAdapter(airportAdapter);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }

        private String columnName(String columnAlias) {
            return ContentProviderContracts.AirportsContract.DB_AIRPORTS.getColumnName(columnAlias);
        }
    }
}
