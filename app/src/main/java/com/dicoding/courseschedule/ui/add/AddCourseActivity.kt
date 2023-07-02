package com.dicoding.courseschedule.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.TimePickerFragment
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class AddCourseActivity : AppCompatActivity() , TimePickerFragment.DialogTimeListener {

    private lateinit var viewModel: AddCourseViewModel
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        val factory = AddViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]

        viewModel.saved.observe(this) { event ->
            event.getContentIfNotHandled()?.let { saved ->
                if (saved) {
                    onBackPressed()
                } else {
                    val messageToast = getString(R.string.input_empty_message)
                    Toast.makeText(this, messageToast, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_insert -> {
                val courseName = findViewById<TextInputEditText>(R.id.ed_course_name).text.toString().trim()
                val courseDay = findViewById<Spinner>(R.id.spinner_day).selectedItemPosition
                val courseStartTime = findViewById<TextView>(R.id.tv_start_time_value).text.toString().trim()
                val courseEndTime = findViewById<TextView>(R.id.tv_end_time_value).text.toString().trim()
                val courseLecturer = findViewById<TextInputEditText>(R.id.ed_lecturer).text.toString().trim()
                val courseNote = findViewById<TextInputEditText>(R.id.ed_note).text.toString().trim()

                viewModel.insertCourse(courseName, courseDay,
                    courseStartTime, courseEndTime, courseLecturer, courseNote)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun timePicker(view: View) {
        val fragment = TimePickerFragment()
        fragment.show(supportFragmentManager, " timePicker")
        this.view = view
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calender = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }

        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        val textViewId = when (view.id) {
            R.id.ib_start_time -> R.id.tv_start_time_value
            R.id.ib_end_time-> R.id.tv_end_time_value
            else -> null
        }

        textViewId?.let {
            findViewById<TextView>(it).text = timeFormat.format(calender.time)
        }
    }
}
