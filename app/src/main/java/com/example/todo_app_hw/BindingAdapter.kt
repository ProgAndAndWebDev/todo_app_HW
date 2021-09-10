package com.example.todo_app_hw

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.res.ColorStateList
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app_hw.adapter.BaseAdapter
import com.example.todo_app_hw.viewModel.TaskFragViewModel
import androidx.cardview.widget.CardView
import androidx.lifecycle.*
import com.example.todo_app_hw.data.Priority
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.security.AccessController.getContext
import java.text.DateFormat
import java.util.*


@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    if (items != null) {
        (view.adapter as BaseAdapter<T>?)?.setItem(items)
    } else {
        (view.adapter as BaseAdapter<T>?)?.setItem(emptyList())
    }
}

@BindingAdapter(value = ["app:status" , "app:viewModel"])
fun actionBtn(
    view: FloatingActionButton, status: Int, viewModel: ViewModel ){
    view.setOnClickListener { view ->
        when(status){
            1 -> (viewModel as TaskFragViewModel).updateNote()
            0 -> (viewModel as TaskFragViewModel).addNotes()
        }
        Navigation.findNavController(view).popBackStack()
    }
}

@BindingAdapter(value = ["app:viewModelSpinner"])
fun  selectedValue(view: Spinner , viewModelSpinner: MutableLiveData<Int>) {
    view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
            viewModelSpinner.value = position
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}

@BindingAdapter(value = ["app:parsePriorityColorCard"])
fun parsePriorityColorCard(cardView: CardView, priority: Priority){
    when(priority){
        Priority.HIGH -> { cardView.setCardBackgroundColor(cardView.context.getColor(R.color.orange)) }
        Priority.MEDIUM -> { cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green)) }
        Priority.LOW -> { cardView.setCardBackgroundColor(cardView.context.getColor(R.color.blue)) }
    }
}

@BindingAdapter(value = ["app:parsePriorityColorChecked"])
fun parsePriorityColorChecked(check: CheckBox, priority: Priority){
    when(priority){
        Priority.HIGH -> { check.setButtonTintList(ColorStateList.valueOf(check.context.getColor(R.color.orange))) }
        Priority.MEDIUM -> { check.setButtonTintList(ColorStateList.valueOf(check.context.getColor(R.color.green))) }
        Priority.LOW -> { check.setButtonTintList(ColorStateList.valueOf(check.context.getColor(R.color.blue))) }
    }
}

@BindingAdapter(value = ["viewModelDate"])
fun setDateCalander(viewBtn:ImageView , viewModelDate: MutableLiveData<String> ){

    val cal: Calendar = Calendar.getInstance()
    var dayCal =  cal.get(Calendar.DAY_OF_WEEK)
    var monthCal = cal.get(Calendar.MONTH)
    var yearCal = cal.get(Calendar.YEAR)

    viewBtn.setOnClickListener { v ->

        DatePickerDialog(viewBtn.context, { view, year, monthOfYear, dayOfMonth ->
            cal.set(year, monthOfYear, dayOfMonth)
            yearCal = year
            monthCal = monthOfYear
            dayCal = dayOfMonth

            val date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(cal.time)
            viewModelDate.value = date

        },yearCal,monthCal ,dayCal).show()
    }

    val date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(cal.time)
    viewModelDate.value = date
}

@BindingAdapter(value = ["viewModelTime"])
fun setTimeCalander(viewBtn:ImageView , viewModelTime: MutableLiveData<String> ){
    val cal: Calendar = Calendar.getInstance()
    var hourCal = cal.get(Calendar.HOUR_OF_DAY)
    var minuteCal = cal.get(Calendar.MINUTE)

    viewBtn.setOnClickListener { v ->

        TimePickerDialog(viewBtn.context,
            { view, hourOfDay, minute ->
                hourCal = hourOfDay
                minuteCal = minute

                cal.set(Calendar.HOUR_OF_DAY , hourCal)
                cal.set(Calendar.MINUTE , minuteCal)

                val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(cal.time)

                viewModelTime.value = time
            },
            hourCal , minuteCal ,false).show()

    }
    val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(cal.time)
    viewModelTime.value = time
}






