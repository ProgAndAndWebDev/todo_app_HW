package com.example.todo_app_hw.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app_hw.R
import com.example.todo_app_hw.adapter.MovieAdapter
import com.example.todo_app_hw.databinding.FragmentHomeBinding
import com.example.todo_app_hw.viewModel.HomeFragViewModel
import androidx.navigation.Navigation
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    val viewModel: HomeFragViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.homeFragModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MovieAdapter(mutableListOf(), viewModel)
        binding.recycler.adapter = adapter

        binding.floatingTask.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.taskFragment)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {

            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var theNote = adapter.getItem(viewHolder.adapterPosition)!!
                viewModel.deleteNotes(theNote)

                val snackBar = Snackbar.make(requireView(), "Note Deleted", Snackbar.LENGTH_LONG)
                    .addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {

                    override fun onShown(transientBottomBar: Snackbar?) {
                        transientBottomBar?.setAction("UNDO") {
                            viewModel.addNotes(theNote)
                        }
                        super.onShown(transientBottomBar)
                    }
                }).apply {
                    animationMode = Snackbar.ANIMATION_MODE_SLIDE
                }
                snackBar.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.yello))
                snackBar.show()
            }

        }).attachToRecyclerView(binding.recycler)
    }

}