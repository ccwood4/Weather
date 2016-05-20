package com.example.clint.owm_client;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;

public class WeatherActivity extends AppCompatActivity {
    public static String USER = "";
    public OpenWeatherMap owm = new OpenWeatherMap("38dcf5fd18bc0fa49e6d5e8671254c66");
    public CurrentWeather cwd = null;
    public DailyForecast dfd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        USER = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

        MySQLiteHelper db = new MySQLiteHelper(this);

        if(db.getUser(USER).getFavone() != 0) {
            try {
                setWeather((long) db.getUser(USER).getFavone());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if(db.getUser(USER).getFavtwo() != 0) {
            try {
                setWeather((long) db.getUser(USER).getFavtwo());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if(db.getUser(USER).getFavthree() != 0) {
            try {
                setWeather((long) db.getUser(USER).getFavthree());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if(db.getUser(USER).getFavfour() != 0) {
            try {
                setWeather((long) db.getUser(USER).getFavfour());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if(db.getUser(USER).getFavfive() != 0) {
            try {
                setWeather((long) db.getUser(USER).getFavfive());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        MySQLiteHelper db = new MySQLiteHelper(this);
        switch (item.getItemId()) {
            case R.id.action_show_favorite:
                // show action
                PopupMenu popup = new PopupMenu(this, findViewById(R.id.action_show_favorite));
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.context_menu, popup.getMenu());
                MenuItem item1 = popup.getMenu().findItem(R.id.context_item_one);
                MenuItem item2 = popup.getMenu().findItem(R.id.context_item_two);
                MenuItem item3 = popup.getMenu().findItem(R.id.context_item_three);
                MenuItem item4 = popup.getMenu().findItem(R.id.context_item_four);
                MenuItem item5 = popup.getMenu().findItem(R.id.context_item_five);
                if(db.getUser(USER).getFavone() != 0){
                    try {
                        item1.setTitle(owm.currentWeatherByCityCode(db.getUser(USER).getFavone()).getCityName());
                        item1.setVisible(true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(db.getUser(USER).getFavtwo() != 0){
                    try {
                        item2.setTitle(owm.currentWeatherByCityCode(db.getUser(USER).getFavtwo()).getCityName());
                        item2.setVisible(true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(db.getUser(USER).getFavthree() != 0){
                    try {
                        item3.setTitle(owm.currentWeatherByCityCode(db.getUser(USER).getFavthree()).getCityName());
                        item3.setVisible(true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(db.getUser(USER).getFavfour() != 0){
                    try {
                        item4.setTitle(owm.currentWeatherByCityCode(db.getUser(USER).getFavfour()).getCityName());
                        item4.setVisible(true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(db.getUser(USER).getFavfive() != 0){
                    try {
                        item5.setTitle(owm.currentWeatherByCityCode(db.getUser(USER).getFavfive()).getCityName());
                        item5.setVisible(true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.context_item_one:
                                try {
                                    MySQLiteHelper db = new MySQLiteHelper(getApplicationContext());
                                    setWeather((long) db.getUser(USER).getFavone());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            case R.id.context_item_two:
                                try {
                                    MySQLiteHelper db = new MySQLiteHelper(getApplicationContext());
                                    setWeather((long) db.getUser(USER).getFavtwo());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            case R.id.context_item_three:
                                try {
                                    MySQLiteHelper db = new MySQLiteHelper(getApplicationContext());
                                    setWeather((long) db.getUser(USER).getFavthree());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            case R.id.context_item_four:
                                try {
                                    MySQLiteHelper db = new MySQLiteHelper(getApplicationContext());
                                    setWeather((long) db.getUser(USER).getFavfour());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            case R.id.context_item_five:
                                try {
                                    MySQLiteHelper db = new MySQLiteHelper(getApplicationContext());
                                    setWeather((long) db.getUser(USER).getFavfive());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
                return true;
            case R.id.action_add_fav:
                // add action
                if(cwd != null) {
                    if (db.getUser(USER).getFavone() == 0) {
                        UserModel user = db.getUser(USER);
                        user.setFavone((int) cwd.getCityCode());
                        db.updateUser(user);
                        Toast.makeText(getApplicationContext(), "Favorite Added.", Toast.LENGTH_SHORT).show();
                    } else if (db.getUser(USER).getFavtwo() == 0) {
                        UserModel user = db.getUser(USER);
                        user.setFavtwo((int) cwd.getCityCode());
                        db.updateUser(user);
                        Toast.makeText(getApplicationContext(), "Favorite Added.", Toast.LENGTH_SHORT).show();
                    } else if (db.getUser(USER).getFavthree() == 0) {
                        UserModel user = db.getUser(USER);
                        user.setFavthree((int) cwd.getCityCode());
                        db.updateUser(user);
                        Toast.makeText(getApplicationContext(), "Favorite Added.", Toast.LENGTH_SHORT).show();
                    } else if (db.getUser(USER).getFavfour() == 0) {
                        UserModel user = db.getUser(USER);
                        user.setFavfour((int) cwd.getCityCode());
                        db.updateUser(user);
                        Toast.makeText(getApplicationContext(), "Favorite Added.", Toast.LENGTH_SHORT).show();
                    } else if (db.getUser(USER).getFavfive() == 0) {
                        UserModel user = db.getUser(USER);
                        user.setFavfive((int) cwd.getCityCode());
                        db.updateUser(user);
                        Toast.makeText(getApplicationContext(), "Favorite Added.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Favorite List Full.", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            case R.id.action_remove_fav:
                // remove action
                if(cwd != null) {
                    if (db.getUser(USER).getFavone() == (int) cwd.getCityCode()) {
                        UserModel user = db.getUser(USER);
                        user.setFavone(0);
                        db.updateUser(user);
                        Toast.makeText(getApplicationContext(), "Favorite Removed.", Toast.LENGTH_SHORT).show();
                    } else if (db.getUser(USER).getFavtwo() == (int) cwd.getCityCode()) {
                        UserModel user = db.getUser(USER);
                        user.setFavtwo(0);
                        db.updateUser(user);
                        Toast.makeText(getApplicationContext(), "Favorite Removed.", Toast.LENGTH_SHORT).show();
                    } else if (db.getUser(USER).getFavthree() == (int) cwd.getCityCode()) {
                        UserModel user = db.getUser(USER);
                        user.setFavthree(0);
                        db.updateUser(user);
                        Toast.makeText(getApplicationContext(), "Favorite Removed.", Toast.LENGTH_SHORT).show();
                    } else if (db.getUser(USER).getFavfour() == (int) cwd.getCityCode()) {
                        UserModel user = db.getUser(USER);
                        user.setFavfour(0);
                        db.updateUser(user);
                        Toast.makeText(getApplicationContext(), "Favorite Removed.", Toast.LENGTH_SHORT).show();
                    } else if (db.getUser(USER).getFavfive() == (int) cwd.getCityCode()) {
                        UserModel user = db.getUser(USER);
                        user.setFavfive(0);
                        db.updateUser(user);
                        Toast.makeText(getApplicationContext(), "Favorite Removed.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Favorite Not Found.", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Called when the user clicks the Search button */
    public void search(View view) throws IOException, JSONException {
        EditText searchName = (EditText) findViewById(R.id.searchNameText);
        EditText countryCode = (EditText) findViewById(R.id.countryCodeText);

        if (countryCode.getText().toString().equals("")) {
            cwd = owm.currentWeatherByCityName(searchName.getText().toString(), "us");
        }
        else {
            cwd = owm.currentWeatherByCityName(searchName.getText().toString(), countryCode.getText().toString());
        }
        setWeather(cwd.getCityCode());
    }

    public void setWeather(long code) throws IOException, JSONException {
        TextView location = (TextView) findViewById(R.id.locationText);
        TextView currentTemp = (TextView) findViewById(R.id.currentTempText);
        TextView currentHigh = (TextView) findViewById(R.id.currentHighText);
        TextView currentLow = (TextView) findViewById(R.id.currentLowText);
        TextView condition = (TextView) findViewById(R.id.conditionText);
        TextView dayOne = (TextView) findViewById(R.id.dayOneText);
        TextView dayOneDate = (TextView) findViewById(R.id.dayOneDate);
        TextView dayTwo = (TextView) findViewById(R.id.dayTwoText);
        TextView dayTwoDate = (TextView) findViewById(R.id.dayTwoDate);
        TextView dayThree = (TextView) findViewById(R.id.dayThreeText);
        TextView dayThreeDate = (TextView) findViewById(R.id.dayThreeDate);
        TextView dayFour = (TextView) findViewById(R.id.dayFourText);
        TextView dayFourDate = (TextView) findViewById(R.id.dayFourDate);
        TextView dayFive = (TextView) findViewById(R.id.dayFiveText);
        TextView dayFiveDate = (TextView) findViewById(R.id.dayFiveDate);
        ImageView currentIcon = (ImageView) findViewById(R.id.currentIcon);
        ImageView dayOneIcon = (ImageView) findViewById(R.id.dayOneIcon);
        ImageView dayTwoIcon = (ImageView) findViewById(R.id.dayTwoIcon);
        ImageView dayThreeIcon = (ImageView) findViewById(R.id.dayThreeIcon);
        ImageView dayFourIcon = (ImageView) findViewById(R.id.dayFourIcon);
        ImageView dayFiveIcon = (ImageView) findViewById(R.id.dayFiveIcon);
        View seperator = (View) findViewById(R.id.separator);


        cwd = owm.currentWeatherByCityCode( code);
        dfd = owm.dailyForecastByCityCode(code, (byte) 0);


        seperator.setVisibility(View.VISIBLE);
        location.setText(cwd.getCityName());
        currentTemp.setText(Math.round(cwd.getMainInstance().getTemperature()) + " 'F");
        String descrip = dfd.getForecastInstance(0).getWeatherInstance(0).getWeatherDescription();
//        Log.d("descrip", descrip);
        condition.setText(descrip);
        if (descrip.equals("clear sky")) {
            currentIcon.setImageResource(R.mipmap.ic_clear_sky);
        } else if(descrip.equals("few clouds")) {
            currentIcon.setImageResource(R.mipmap.ic_few_clouds);
        } else if(descrip.equals("scattered clouds")) {
            currentIcon.setImageResource(R.mipmap.ic_scattered_clouds);
        } else if(descrip.equals("broken clouds")) {
            currentIcon.setImageResource(R.mipmap.ic_broken_clouds);
        } else if(descrip.equals("shower rain")) {
            currentIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("rain")) {
            currentIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("moderate rain")) {
            currentIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("heavy intensity rain")) {
            currentIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("light rain")) {
            currentIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("thunderstorm")) {
            currentIcon.setImageResource(R.mipmap.ic_thunderstorm);
        } else if(descrip.equals("snow")) {
            currentIcon.setImageResource(R.mipmap.ic_snow);
        } else if(descrip.equals("mist")) {
            currentIcon.setImageResource(R.mipmap.ic_mist);
        }

        currentHigh.setText(Math.round(dfd.getForecastInstance(0).getTemperatureInstance().getMaximumTemperature()) + " 'F");
        currentLow.setText(Math.round(dfd.getForecastInstance(0).getTemperatureInstance().getMinimumTemperature()) + " 'F");
        dayOneDate.setText(dfd.getForecastInstance(1).getDateTime().toString().substring(0, 3));
        descrip = dfd.getForecastInstance(1).getWeatherInstance(0).getWeatherDescription();
        if (descrip.equals("clear sky")) {
            dayOneIcon.setImageResource(R.mipmap.ic_clear_sky);
        } else if(descrip.equals("few clouds")) {
            dayOneIcon.setImageResource(R.mipmap.ic_few_clouds);
        } else if(descrip.equals("scattered clouds")) {
            dayOneIcon.setImageResource(R.mipmap.ic_scattered_clouds);
        } else if(descrip.equals("broken clouds")) {
            dayOneIcon.setImageResource(R.mipmap.ic_broken_clouds);
        } else if(descrip.equals("shower rain")) {
            dayOneIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("rain")) {
            dayOneIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("moderate rain")) {
            dayOneIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("heavy intensity rain")) {
            dayOneIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("light rain")) {
            dayOneIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("thunderstorm")) {
            dayOneIcon.setImageResource(R.mipmap.ic_thunderstorm);
        } else if(descrip.equals("snow")) {
            dayOneIcon.setImageResource(R.mipmap.ic_snow);
        } else if(descrip.equals("mist")) {
            dayOneIcon.setImageResource(R.mipmap.ic_mist);
        }
        dayOne.setText(Math.round(dfd.getForecastInstance(1).getTemperatureInstance().getMaximumTemperature()) + "/" + Math.round(dfd.getForecastInstance(1).getTemperatureInstance().getMinimumTemperature()));
        dayTwoDate.setText(dfd.getForecastInstance(2).getDateTime().toString().substring(0, 3));
        descrip = dfd.getForecastInstance(2).getWeatherInstance(0).getWeatherDescription();
        if (descrip.equals("clear sky")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_clear_sky);
        } else if(descrip.equals("few clouds")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_few_clouds);
        } else if(descrip.equals("scattered clouds")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_scattered_clouds);
        } else if(descrip.equals("broken clouds")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_broken_clouds);
        } else if(descrip.equals("shower rain")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("rain")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("moderate rain")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("heavy intensity rain")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("light rain")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("thunderstorm")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_thunderstorm);
        } else if(descrip.equals("snow")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_snow);
        } else if(descrip.equals("mist")) {
            dayTwoIcon.setImageResource(R.mipmap.ic_mist);
        }
        dayTwo.setText(Math.round(dfd.getForecastInstance(2).getTemperatureInstance().getMaximumTemperature()) + "/" + Math.round(dfd.getForecastInstance(2).getTemperatureInstance().getMinimumTemperature()));
        dayThreeDate.setText(dfd.getForecastInstance(3).getDateTime().toString().substring(0, 3));
        descrip = dfd.getForecastInstance(3).getWeatherInstance(0).getWeatherDescription();
        if (descrip.equals("clear sky")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_clear_sky);
        } else if(descrip.equals("few clouds")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_few_clouds);
        } else if(descrip.equals("scattered clouds")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_scattered_clouds);
        } else if(descrip.equals("broken clouds")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_broken_clouds);
        } else if(descrip.equals("shower rain")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("rain")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("light rain")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("moderate rain")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("heavy intensity rain")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("thunderstorm")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_thunderstorm);
        } else if(descrip.equals("snow")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_snow);
        } else if(descrip.equals("mist")) {
            dayThreeIcon.setImageResource(R.mipmap.ic_mist);
        }
        dayThree.setText(Math.round(dfd.getForecastInstance(3).getTemperatureInstance().getMaximumTemperature()) + "/" + Math.round(dfd.getForecastInstance(3).getTemperatureInstance().getMinimumTemperature()));
        dayFourDate.setText(dfd.getForecastInstance(4).getDateTime().toString().substring(0, 3));
        descrip = dfd.getForecastInstance(4).getWeatherInstance(0).getWeatherDescription();
        if (descrip.equals("clear sky")) {
            dayFourIcon.setImageResource(R.mipmap.ic_clear_sky);
        } else if(descrip.equals("few clouds")) {
            dayFourIcon.setImageResource(R.mipmap.ic_few_clouds);
        } else if(descrip.equals("scattered clouds")) {
            dayFourIcon.setImageResource(R.mipmap.ic_scattered_clouds);
        } else if(descrip.equals("broken clouds")) {
            dayFourIcon.setImageResource(R.mipmap.ic_broken_clouds);
        } else if(descrip.equals("shower rain")) {
            dayFourIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("rain")) {
            dayFourIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("light rain")) {
            dayFourIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("moderate rain")) {
            dayFourIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("heavy intensity rain")) {
            dayFourIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("thunderstorm")) {
            dayFourIcon.setImageResource(R.mipmap.ic_thunderstorm);
        } else if(descrip.equals("snow")) {
            dayFourIcon.setImageResource(R.mipmap.ic_snow);
        } else if(descrip.equals("mist")) {
            dayFourIcon.setImageResource(R.mipmap.ic_mist);
        }
        dayFour.setText(Math.round(dfd.getForecastInstance(4).getTemperatureInstance().getMaximumTemperature()) + "/" + Math.round(dfd.getForecastInstance(4).getTemperatureInstance().getMinimumTemperature()));
        dayFiveDate.setText(dfd.getForecastInstance(5).getDateTime().toString().substring(0, 3));
        descrip = dfd.getForecastInstance(5).getWeatherInstance(0).getWeatherDescription();
        if (descrip.equals("clear sky")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_clear_sky);
        } else if(descrip.equals("few clouds")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_few_clouds);
        } else if(descrip.equals("scattered clouds")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_scattered_clouds);
        } else if(descrip.equals("broken clouds")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_broken_clouds);
        } else if(descrip.equals("shower rain")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("rain")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("light rain")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("moderate rain")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("heavy intensity rain")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_shower_rain);
        } else if(descrip.equals("thunderstorm")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_thunderstorm);
        } else if(descrip.equals("snow")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_snow);
        } else if(descrip.equals("mist")) {
            dayFiveIcon.setImageResource(R.mipmap.ic_mist);
        }
        dayFive.setText(Math.round(dfd.getForecastInstance(5).getTemperatureInstance().getMaximumTemperature()) + "/" + Math.round(dfd.getForecastInstance(5).getTemperatureInstance().getMinimumTemperature()));
    }
}
