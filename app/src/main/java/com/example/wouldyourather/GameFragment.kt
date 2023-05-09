package com.example.wouldyourather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.wouldyourather.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this)[GameViewModel::class.java]
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.option1.observe(viewLifecycleOwner, Observer {
            binding.optionOneButton.text = it.toString()
        })

        viewModel.option2.observe(viewLifecycleOwner, Observer {
            binding.optionTwoButton.text = it.toString()
        })

        binding.optionOneButton.setOnClickListener {
            if(viewModel.clickState.value == true){
                viewModel.onSkip()
            }else{
                viewModel.onOption1Clicked()
            }
        }

        binding.optionTwoButton.setOnClickListener {
            if(viewModel.clickState.value == true){
                viewModel.onSkip()
            }else{
                viewModel.onOption2Clicked()
            }
        }

        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        return binding.root
    }
}