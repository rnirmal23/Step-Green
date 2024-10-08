package com.vipin.stepgreen.trees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import com.vipin.stepgreen.R
import com.vipin.stepgreen.databinding.FragmentGreenBinding
import kotlin.random.Random

class GreenFragment : Fragment() {

    private val viewModel: GreenViewModel by viewModels { GreenViewModel.Factory }

    private lateinit var binding: FragmentGreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.trees.collect { updateUserInterface(it) }
            }
        }
    }

    private fun updateUserInterface(greenState: GreenState) {
        val treeCount = greenState.treeCount
        binding.apply {
            textTreesCollected.text = treeCount.toString()
            textTreesCollectedLabel.text = resources.getQuantityString(R.plurals.trees, treeCount)
        }
        generateTrees(greenState.treeCount)
    }

    private fun generateTrees(treeCount: Int) {
        val parentLayout = binding.constraintLayoutTrees
        parentLayout.removeAllViews()
        val gapCount = treeCount + 1
        repeat(treeCount) {
            val fixedPosition = (it + 1.0) / gapCount
            val randomOffset = (Random.nextDouble() - 0.5) / 5
            val horizontalPosition = fixedPosition + randomOffset
            createTree(parentLayout, horizontalPosition)
        }
    }

    private fun createTree(parentLayout: ConstraintLayout, horizontalPosition: Double) {
        val treeImageView = ImageView(context)
        treeImageView.setImageResource(R.drawable.tree_collected)
        parentLayout.addView(treeImageView)
        treeImageView.updateLayoutParams<ConstraintLayout.LayoutParams> {
            startToStart = parentLayout.id
            endToEnd = parentLayout.id
            bottomToBottom = parentLayout.id
            horizontalBias = horizontalPosition.toFloat()
        }
    }
}