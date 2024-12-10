package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnTime, btnDate;
    TextView tvSelectedDateTime;  // TextView to display the selected date and time

    int selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//hi
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons and TextView
        btnTime = findViewById(R.id.btnTime);
        btnDate = findViewById(R.id.btnDate);
        tvSelectedDateTime = findViewById(R.id.tvSelectedDateTime);

        // Get current date and time
        final Calendar calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);
        selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
        selectedHour = calendar.get(Calendar.HOUR_OF_DAY);
        selectedMinute = calendar.get(Calendar.MINUTE);

        // Set OnClickListener for Time button
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                                selectedHour = hourOfDay;
                                selectedMinute = minute;

                                // Display the selected time in a Toast
                                String time = formatTime(selectedHour, selectedMinute);
                                Toast.makeText(MainActivity.this, "Selected Time: " + time, Toast.LENGTH_SHORT).show();

                                // Update TextView with selected date and time
                                updateSelectedDateTime();
                            }
                        },
                        selectedHour, selectedMinute, true
                );//ani melech
                timePickerDialog.show();
            }
        });

        // Set OnClickListener for Date button
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                selectedYear = year;
                                selectedMonth = monthOfYear;
                                selectedDay = dayOfMonth;

                                // Display the selected date in a Toast
                                String date = formatDate(selectedDay, selectedMonth, selectedYear);
                                Toast.makeText(MainActivity.this, "Selected Date: " + date, Toast.LENGTH_SHORT).show();

                                // Update TextView with selected date and time
                                updateSelectedDateTime();
                            }
                        },
                        selectedYear, selectedMonth, selectedDay
                );
                datePickerDialog.show();
            }
        });
    }

    // Method to update the TextView with the selected date and time
    private void updateSelectedDateTime() {
        // Format the time and date
        String time = formatTime(selectedHour, selectedMinute);
        String date = formatDate(selectedDay, selectedMonth, selectedYear);

        // Combine both and set to the TextView
        String dateTime = "Selected Date and Time: " + date + " " + time;
        tvSelectedDateTime.setText(dateTime);
    }

    // Method to format the time as HH:mm (leading zero if needed)
    private String formatTime(int hour, int minute) {
        String formattedMinute = (minute < 10) ? "0" + minute : String.valueOf(minute);
        return hour + ":" + formattedMinute;
    }

    // Method to format the date as dd/MM/yyyy (leading zero if needed)
    private String formatDate(int day, int month, int year) {
        String formattedDay = (day < 10) ? "0" + day : String.valueOf(day);
        String formattedMonth = (month + 1 < 10) ? "0" + (month + 1) : String.valueOf(month + 1);
        return formattedDay + "/" + formattedMonth + "/" + year;
    }
}
