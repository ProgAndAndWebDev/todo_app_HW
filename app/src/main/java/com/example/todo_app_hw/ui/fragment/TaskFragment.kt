package com.example.todo_app_hw.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todo_app_hw.databinding.FragmentTaskBinding
import com.example.todo_app_hw.util.Constracter.STATUS_ADD
import com.example.todo_app_hw.util.Constracter.STATUS_UPDATE
import com.example.todo_app_hw.viewModel.TaskFragViewModel


class TaskFragment : Fragment() {

    lateinit var binding : FragmentTaskBinding
    val viewModel: TaskFragViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTaskBinding.inflate(layoutInflater)
        binding.taskFragModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            viewModel.statusBtn.value = STATUS_UPDATE
            val arg= Bundle(arguments)
            val theNote = arg.get("NOTE_CURRENT")
            viewModel.getCurrentNote(theNote)
        } else {
            viewModel.statusBtn.value = STATUS_ADD
        }

    }

}