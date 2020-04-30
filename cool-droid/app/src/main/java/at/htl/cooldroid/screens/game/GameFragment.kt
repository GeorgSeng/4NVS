package at.htl.cooldroid.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import at.htl.cooldroid.R
import at.htl.cooldroid.databinding.GameFragmentBinding
import timber.log.Timber

class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: GameFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("OnCreateView")
        binding = DataBindingUtil.inflate(
            inflater, R.layout.game_fragment, container, false)
        binding.lifecycleOwner = this;

        binding.ivCoolDroid.setOnClickListener {
            viewModel.addNewClick()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.i("OnActivityCreated")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
    }
}
