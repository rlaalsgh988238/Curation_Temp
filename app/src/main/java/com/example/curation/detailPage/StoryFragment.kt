package com.example.curation.detailPage
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.curation.Data.Data
import com.example.curation.databinding.StoryFragmentBinding

//this class's refernece have to be moified
class StoryFragment(index: Int) : Fragment() {
    lateinit var binding: StoryFragmentBinding
    val data = Data.dataSearch(index)

    // create fragment's view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    // modify fragment's view
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set Story
        binding.storyView.setText(data.content)
    }
}