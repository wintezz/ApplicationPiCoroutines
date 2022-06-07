package com.alexpetrov.applicationpicoroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexpetrov.applicationpicoroutines.databinding.FragmentABinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.math.BigDecimal

class FragmentA : Fragment() {

    private lateinit var binding: FragmentABinding
    private var resultOne = BigDecimal(3)
    private var resultTwo = BigDecimal(4)
    private var counterOne: Double = 0.0
    private var counterTwo: Double = 0.0
    private var showOne = ""
    private var showTwo = ""
    private var formula: Double = 0.0
    private var countTwo = 0
    private var divider = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentABinding
            .inflate(inflater, container, false)

        main()
        return binding.root
    }

    private fun main() {

        GlobalScope.launch(Dispatchers.IO) {
            oneScreen()
        }
        GlobalScope.launch(Dispatchers.IO) {
            twoScreen()
        }
    }

    private suspend fun oneScreen() = coroutineScope {

        launch {
            while (true) {
                counterOne += 1
                if (counterOne % 2 == 1.0) {
                    formula = counterOne * 2 * (counterOne * 2 + 1) * (counterOne * 2 + 2)
                    resultOne += (BigDecimal(4).divide(BigDecimal(formula), 300, 0))

                } else {
                    formula = counterOne * 2 * (counterOne * 2 + 1) * (counterOne * 2 + 2)
                    resultOne -= (BigDecimal(4).divide(BigDecimal(formula), 300, 0))
                }

                showOne = resultOne.toString()
                if (counterOne % 1000 == 0.0) {
                    binding.textViewA.text = showOne
                }
            }

        }.join()
    }

    private suspend fun twoScreen() = coroutineScope {

        launch {
            while (true) {
                counterTwo += 1
                if (countTwo%2 ==0) {
                    resultTwo -= (BigDecimal(4).divide(BigDecimal(divider+2),300,0))

                } else {
                    resultTwo += (BigDecimal(4).divide(BigDecimal(divider+2),300,0))
                }

                showTwo = resultTwo.toString()
                divider += 2
                if (counterTwo % 1000 == 0.0) {
                    binding.textViewB.text = showTwo
                }
                countTwo++
            }

        }.join()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentA()
    }
}