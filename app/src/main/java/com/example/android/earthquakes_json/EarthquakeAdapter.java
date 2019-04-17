package com.example.android.earthquakes_json;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public static class ViewHolder {
        public TextView magnitudeViewHolder, offsetViewHolder, primaryViewHolder, dateViewHolder, timeViewHolder;
    }

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context     The current context. Used to inflate the layout file.
     * @param earthquakes A List of Word objects to display in a list
     */
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        String offsetLocation, primaryLocation;

        //I check if the view is being reused otherwise inflate the view
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
            ViewHolder holder = new ViewHolder();

            holder.magnitudeViewHolder = listView.findViewById(R.id.magnitude);
            holder.offsetViewHolder = listView.findViewById(R.id.offset_location);
            holder.primaryViewHolder = listView.findViewById(R.id.primary_location);
            holder.dateViewHolder = listView.findViewById(R.id.date);
            holder.timeViewHolder = listView.findViewById(R.id.time);
            listView.setTag(holder);
        }

        //I get the current attraction
        Earthquake currentEarthquake = getItem(position);

        ViewHolder holder = (ViewHolder) listView.getTag();

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) holder.magnitudeViewHolder.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String formattedMagnitude = formatMagnitude(currentEarthquake.getmMagnitude());
        holder.magnitudeViewHolder.setText(formattedMagnitude);

        String fullLocation = currentEarthquake.getmLocation();
        String[] splitLocation = fullLocation.split(LOCATION_SEPARATOR);

        switch (splitLocation.length) {
            case 1:
                offsetLocation = "Near of";
                primaryLocation = splitLocation[0];
                break;
            case 2:
                offsetLocation = splitLocation[0];
                primaryLocation = splitLocation[1];
                break;
            default:
                offsetLocation = "Error";
                primaryLocation = "Error";
                break;
        }

        holder.offsetViewHolder.setText(offsetLocation);
        holder.primaryViewHolder.setText(primaryLocation);

        Date date = new Date(currentEarthquake.getmTimeInMilliseconds());
        String formatDate = formatDate(date);
        String formatTime = formatTime(date);

        holder.dateViewHolder.setText(formatDate);
        holder.timeViewHolder.setText(formatTime);

        return listView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(float magnitude) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }

    private int getMagnitudeColor(float magnitude) {
        int magnitudeColorResourceId;

        switch ((int) magnitude) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
