package com.code.jamie.smartfarm.activities.homepage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.adapters.MarketAdapter
import com.code.jamie.smartfarm.databinding.FragmentMarketBinding
import com.code.jamie.smartfarm.models.Product

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MarketFragment : Fragment() {
    lateinit var binding:FragmentMarketBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketBinding.inflate(inflater,container,false)
        val productsList = ArrayList<Product>()
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        productsList.add(Product("Inoculant","This inoculation drug is used to vaccinate against NewCastle Disease Virus","1500",R.drawable.android_apps.toString()))
        val marketAdapter = MarketAdapter(productsList,null)
        binding.productsListMarket.apply {
            clipToPadding = false
            adapter = marketAdapter
        }
        marketAdapter.setOnItemClickListener(object :MarketAdapter.OnItemClick{
            override fun onItemClick(position: Int) {
                Toast.makeText(requireContext(),"Item at position ${productsList[position]} was clicked",Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MarketFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}